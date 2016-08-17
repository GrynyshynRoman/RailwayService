package ua.nure.hrynyshyn.controllers.servlets.adminServlets.wayStationsSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

import static ua.nure.hrynyshyn.core.supportClasses.dateTimeSupport.parseDate;
import static ua.nure.hrynyshyn.core.supportClasses.dateTimeSupport.parseTime;

/**
 * Created by GrynyshynRoman on 16.08.2016.
 */
@WebServlet(name = "editWayStationServlet", urlPatterns = "/editWayStation")
public class editWayStationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WayStation station = new WayStation();
        station.setWayStation_ID(Integer.parseInt(request.getParameter("wayStationID")));
        station.setStation_ID(Integer.parseInt(request.getParameter("stationID")));
        station.setRoute_ID(Integer.parseInt(request.getParameter("routeID")));
        station.setArrivalTime(parseDate(request.getParameter("arrivDate"))
                + parseTime(request.getParameter("arrivTime")));
        station.setDepartTime(parseDate(request.getParameter("deptDate"))
                + parseTime(request.getParameter("deptTime")));
        station.setWaitingTime(parseTime(request.getParameter("waitingTime")));

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        DAOFactory.getWayStationDAO(connection).update(station);
        HttpSession session = request.getSession();
        session.setAttribute("wayStations", DAOFactory.getWayStationDAO(connection).getAll());

        cp.freeConnection(connection);

        RequestDispatcher dispatcher = request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
