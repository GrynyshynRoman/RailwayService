package ua.nure.hrynyshyn.controllers.servlets.adminServlets.routeSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;

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
 * Created by GrynyshynRoman on 16.08.2016.
 */
@WebServlet(name = "deleteRouteServlet", urlPatterns = "/deleteRoute")
public class deleteRouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Route route = new Route();
        route.setRoute_ID(Integer.parseInt(request.getParameter("routeID")));
        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();
        DAOFactory.getRouteDAO(connection).delete(route);
        HttpSession session = request.getSession();
        session.setAttribute("routes", DAOFactory.getRouteDAO(connection).getAll());
        cp.freeConnection(connection);
        RequestDispatcher dispatcher = request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
