package ua.nure.hrynyshyn.controllers.servlets.adminServlets.routeSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;

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
@WebServlet(name = "deleteRouteServlet", urlPatterns = "/deleteRoute")
public class deleteRouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Route route=new Route();
        route.setRoute_ID(Integer.parseInt(request.getParameter("routeID")));
        DAOFactory.getRouteDAO().delete(route);
        request.setAttribute("routes", DAOFactory.getRouteDAO().getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("administrator.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
