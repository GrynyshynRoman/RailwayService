package ua.nure.hrynyshyn.controllers.servlets.adminServlets.supportServlets;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;

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
 * Created by GrynyshynRoman on 17.08.2016.
 */
@WebServlet(name = "routesEdit",urlPatterns = "/routesEdit")
public class routesEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();


        HttpSession session = request.getSession();
        session.setAttribute("routes", DAOFactory.getRouteDAO(connection).getAll());
        session.setAttribute("wayStations", DAOFactory.getWayStationDAO(connection).getAll());
        cp.freeConnection(connection);

        RequestDispatcher dispatcher = request.getRequestDispatcher("routesEdit.jsp");
        dispatcher.forward(request, response);
    }
}
