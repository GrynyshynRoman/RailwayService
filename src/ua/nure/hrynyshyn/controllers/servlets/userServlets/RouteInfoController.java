package ua.nure.hrynyshyn.controllers.servlets.userServlets;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.DBSupport.searchEngine.RouteInfo;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Collecting data for constructing info about selected route.
 */
@WebServlet(name = "routeInfo", urlPatterns = "/routeInfo")
public class RouteInfoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        int route_ID = Integer.parseInt(request.getParameter("route_ID"));

        Route route = DAOFactory.getRouteDAO(connection).read(route_ID);

        Station departStation = DAOFactory.getStationDAO(connection).read(route.getDepartStation_ID());
        Station destStation = DAOFactory.getStationDAO(connection).read(route.getDestStation_ID());
/*
Getting Station instances for route
 */
        List<WayStation> wayStations = DAOFactory.getWayStationDAO(connection).getByRouteID(route_ID);
        List<Station> stations = new ArrayList<>();
        for (WayStation wayStation : wayStations) {
            Station station = DAOFactory.getStationDAO(connection).read(wayStation.getStation_ID());
            stations.add(station);
        }

        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setRoute(route);
        routeInfo.setDepartStation(departStation);
        routeInfo.setDestStation(destStation);
        routeInfo.setStations(stations);
        routeInfo.setWayStations(wayStations);
        request.setAttribute("routeInfo", routeInfo);

        cp.freeConnection(connection);

        RequestDispatcher dispatcher = request.getRequestDispatcher("routeInfo.jsp");
        dispatcher.forward(request, response);
    }
}
