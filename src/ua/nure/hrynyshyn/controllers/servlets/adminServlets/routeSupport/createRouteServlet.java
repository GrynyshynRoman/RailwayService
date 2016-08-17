package ua.nure.hrynyshyn.controllers.servlets.adminServlets.routeSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static ua.nure.hrynyshyn.core.supportClasses.dateTimeSupport.parseDate;
import static ua.nure.hrynyshyn.core.supportClasses.dateTimeSupport.parseTime;

/**
 * Created by GrynyshynRoman on 15.08.2016.
 */
@WebServlet(name = "createRouteServlet", urlPatterns = "/createRoute")
public class createRouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Route route = new Route();
        route.setDepartStation_ID(Integer.parseInt(request.getParameter("deptStationID")));
        long deptDate = parseDate(request.getParameter("deptDate"));
        long deptTime = parseTime(request.getParameter("deptTime"));
        long destDate = parseDate(request.getParameter("destDate"));
        long destTime = parseTime(request.getParameter("destTime"));


        route.setDepartTime(deptTime + deptDate);
        route.setDestStation_ID(Integer.parseInt(request.getParameter("destStationID")));
        route.setDestTime(destDate + destTime);

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        DAOFactory.getRouteDAO(connection).insert(route);
        HttpSession session = request.getSession();
        session.setAttribute("routes", DAOFactory.getRouteDAO(connection).getAll());
        cp.freeConnection(connection);

        RequestDispatcher dispatcher = request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
