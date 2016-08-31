package ua.nure.hrynyshyn.controllers.servlets.adminServlets.stationSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

/**
 * Deleting station from database by specified station id.
 */
@WebServlet(name = "deleteStation", urlPatterns = "/deleteStation")
public class DeleteStation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Station station = new Station();
        station.setStation_ID(Integer.parseInt(request.getParameter("id")));

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        boolean isDeleted = DAOFactory.getStationDAO(connection).delete(station);
        if (!isDeleted){
            request.getSession().setAttribute("isStationDeleted",isDeleted);
        }
        cp.freeConnection(connection);
        response.sendRedirect("stationsEdit.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
