package ua.nure.hrynyshyn.controllers.servlets.userServlets;

import ua.nure.hrynyshyn.core.DBSupport.searchEngine.SearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Selects one search result by specified id.
 */
@WebServlet(name = "selectTrain", urlPatterns = "/selectTrain")
public class SelectTrain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<SearchResult> resultList = (ArrayList<SearchResult>) session.getAttribute("searchResults");

        int resultID = Integer.parseInt(request.getParameter("result_ID"));

        SearchResult result = null;
        for (SearchResult searchResult : resultList) {
            if (searchResult.getResult_ID() == resultID) {
                result = searchResult;
                break;
            }
        }
        session.setAttribute("selectedResult", result);
        response.sendRedirect("buyTicket.jsp");
    }
}
