package ua.nure.hrynyshyn.controllers.servlets.adminServlets.wayStationsSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.nure.hrynyshyn.core.supportClasses.dateTimeSupport.parseDate;
import static ua.nure.hrynyshyn.core.supportClasses.dateTimeSupport.parseTime;

/**
 * Created by GrynyshynRoman on 16.08.2016.
 */
@WebServlet(name = "addWayStationServlet", urlPatterns = "/addWayStation")
public class addWayStationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WayStation station = new WayStation();
        station.setStation_ID(Integer.parseInt(request.getParameter("stationID")));
        station.setRoute_ID(Integer.parseInt(request.getParameter("routeID")));
        station.setArrivalTime(parseDate(request.getParameter("arrivDate"))
                + parseTime(request.getParameter("arrivTime")));
        station.setDepartTime(parseDate(request.getParameter("deptDate"))
                + parseTime(request.getParameter("deptTime")));
        station.setWaitingTime(parseTime(request.getParameter("waitingTime")));
        DAOFactory.getWayStationDAO().insert(station);
        request.setAttribute("wayStations", DAOFactory.getWayStationDAO().getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
