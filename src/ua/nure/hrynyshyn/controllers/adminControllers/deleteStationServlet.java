package ua.nure.hrynyshyn.controllers.adminControllers;

import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.moderating.Administrator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GrynyshynRoman on 12.08.2016.
 */
@WebServlet(name = "deleteStationServlet", urlPatterns = "/deleteStation")
public class deleteStationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Station station=new Station();
        station.setStation_ID(Integer.parseInt(request.getParameter("id")));
        Administrator administrator=new Administrator();
        administrator.deleteStation(station);
        request.setAttribute("stations",administrator.getAllStations());
        RequestDispatcher dispatcher=request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
