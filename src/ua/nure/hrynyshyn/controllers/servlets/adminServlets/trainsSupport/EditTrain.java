package ua.nure.hrynyshyn.controllers.servlets.adminServlets.trainsSupport;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Train;

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
 * Changing train route.
 */
@WebServlet(name = "editTrain", urlPatterns = "/editTrain")
public class EditTrain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Train train = new Train();
        train.setTrain_ID(Integer.parseInt(request.getParameter("train_ID")));
        train.setRoute_ID(Integer.parseInt(request.getParameter("route_ID")));

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();
        DAOFactory.getTrainDAO(connection).update(train);

        cp.freeConnection(connection);

        response.sendRedirect("trainsEdit.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
