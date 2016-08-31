package ua.nure.hrynyshyn.controllers.servlets.adminServlets.carriages;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.railway.CarriageDAO;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Carriage;

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
 * Carriage deleting process.
 */
@WebServlet(name = "deleteCarriage", urlPatterns = "/deleteCarriage")
public class DeleteCarriage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Carriage carriage = new Carriage();
        carriage.setCarriage_ID(Integer.parseInt(request.getParameter("carriage_ID")));

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        CarriageDAO carriageDAO = DAOFactory.getCarriageDAO(connection);
        carriageDAO.delete(carriage);
        cp.freeConnection(connection);

        response.sendRedirect("trainsEdit.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
