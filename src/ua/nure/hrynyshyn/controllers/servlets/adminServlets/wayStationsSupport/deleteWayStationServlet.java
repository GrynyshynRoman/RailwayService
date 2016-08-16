package ua.nure.hrynyshyn.controllers.servlets.adminServlets.wayStationsSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GrynyshynRoman on 16.08.2016.
 */
@WebServlet(name = "deleteWayStationServlet", urlPatterns = "/deleteWayStation")
public class deleteWayStationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WayStation station=new WayStation();
        station.setWayStation_ID(Integer.parseInt(request.getParameter("wayStationID")));
        DAOFactory.getWayStationDAO().delete(station);
        request.setAttribute("wayStations", DAOFactory.getWayStationDAO().getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
