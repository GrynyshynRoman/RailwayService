package ua.nure.hrynyshyn.core.supportClasses;

import org.apache.log4j.Logger;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.railway.CarriageDAO;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service.TicketDAO;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Carriage;
import ua.nure.hrynyshyn.core.entities.service.Ticket;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by GrynyshynRoman on 30.08.2016.
 */
public class BuyingProcess {
    private static final Logger log = Logger.getLogger(BuyingProcess.class.getName());

    public static synchronized boolean buy(Ticket ticket) {
        boolean isDone = false;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        try {
            connection.setAutoCommit(false);

            TicketDAO ticketDAO = DAOFactory.getTicketDAO(connection);
            ticketDAO.insert(ticket);

            CarriageDAO carriageDAO = DAOFactory.getCarriageDAO(connection);
            Carriage carriage = carriageDAO.getByNumber(ticket.getTrain_ID(), ticket.getCarriageNumber());
            if (carriage.getTotalSeats() > carriage.getReservedSeats()) {
                carriageDAO.reserveSeat(ticket.getTrain_ID(), ticket.getCarriageNumber());
                connection.commit();
                isDone = true;
                log.info("Ticket buying transaction successful");

            } else {
                isDone = false;
                connection.rollback();
                log.info("Ticket buying transaction aborted");
            }


        } catch (SQLException e) {
            log.error("Ticket buying operation failure");
            try {
                connection.rollback();
                log.info("Ticket buying transaction aborted");
            } catch (SQLException ex) {
                log.error("Rollback failure");
            }
        } finally {
            cp.freeConnection(connection);
        }
        return isDone;
    }
}
