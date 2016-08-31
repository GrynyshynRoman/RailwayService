package ua.nure.hrynyshyn.controllers.servlets.userServlets;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.DBSupport.searchEngine.Search;
import ua.nure.hrynyshyn.core.DBSupport.searchEngine.SearchResult;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Train;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import static ua.nure.hrynyshyn.core.supportClasses.DateTimeSupport.parseDate;

/**
 * Search controller. Produses search by specified stations and date.
 * Redirects to page with founded results or to error-page if there no suitable routes.
 */
@WebServlet(name = "Search", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        String departStationName = request.getParameter("departStation");
        String destStationName = request.getParameter("destStation");

        List<String> stationNames = DAOFactory.getStationDAO(connection).getAllNames();
        /*
        Check is inputted station names are available in data base
         */
        if (stationNames.contains(departStationName) && stationNames.contains(destStationName)) {

            Station departStation = DAOFactory.getStationDAO(connection).getByName(departStationName);
            Station destStation = DAOFactory.getStationDAO(connection).getByName(destStationName);

            Date date = new Date(parseDate(request.getParameter("date")));

            Search search = new Search(departStation, destStation, date);

            List<SearchResult> results = search.search();
            /*
            Check is search found any routes.
             */
            if (results.size() != 0) {
                HttpSession session = request.getSession();
                cp.freeConnection(connection);
                session.setAttribute("searchResults", results);
                response.sendRedirect("searchResults.jsp");
            }else {
                response.sendRedirect("searchError.jsp");
            }
        } else {
            response.sendRedirect("searchError.jsp");
        }

    }
}
