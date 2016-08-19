package ua.nure.hrynyshyn.controllers.servlets.adminServlets.stationSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;

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
 * Created by GrynyshynRoman on 09.08.2016.
 */
@WebServlet(name = "addStation", urlPatterns = "/addStation")
public class addStation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Station station = new Station();
        station.setName(request.getParameter("name"));
        station.setCity(request.getParameter("city"));
        station.setState(request.getParameter("state"));
        station.setCountry(request.getParameter("country"));

        ConnectionPool cp=(ConnectionPool)getServletContext().getAttribute("DBConnection");
        Connection connection=cp.getConnection();
        DAOFactory.getStationDAO(connection).insert(station);

        HttpSession session = request.getSession();
        session.setAttribute("stations", DAOFactory.getStationDAO(connection).getAll());

        cp.freeConnection(connection);
        RequestDispatcher dispatcher = request.getRequestDispatcher("stationsEdit.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
