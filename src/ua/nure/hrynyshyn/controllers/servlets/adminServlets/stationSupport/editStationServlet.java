package ua.nure.hrynyshyn.controllers.servlets.adminServlets.stationSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
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
 * Created by GrynyshynRoman on 15.08.2016.
 */
@WebServlet(name = "editStationServlet", urlPatterns = "/editStation")
public class editStationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Station station=new Station();
        station.setStation_ID(Integer.parseInt(request.getParameter("id")));
        station.setName(request.getParameter("name"));
        station.setCity(request.getParameter("city"));
        station.setState(request.getParameter("state"));
        station.setCountry(request.getParameter("country"));
        DAOFactory.getStationDAO().update(station);
        request.setAttribute("stations",DAOFactory.getStationDAO().getAll());
        RequestDispatcher dispatcher=request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
