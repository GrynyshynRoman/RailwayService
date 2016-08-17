package ua.nure.hrynyshyn.controllers.servlets.adminServlets.stationSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.moderating.Administrator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by GrynyshynRoman on 12.08.2016.
 */
@WebServlet(name = "deleteStationServlet", urlPatterns = "/deleteStation")
public class deleteStationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Station station = new Station();
        station.setStation_ID(Integer.parseInt(request.getParameter("id")));

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        DAOFactory.getStationDAO(connection).delete(station);
        HttpSession session = request.getSession();
        session.setAttribute("stations", DAOFactory.getStationDAO(connection).getAll());

        cp.freeConnection(connection
        );
        RequestDispatcher dispatcher = request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
