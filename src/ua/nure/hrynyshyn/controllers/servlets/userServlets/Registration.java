package ua.nure.hrynyshyn.controllers.servlets.userServlets;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service.RoleDAO;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service.UserDAO;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.service.Role;
import ua.nure.hrynyshyn.core.entities.service.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by GrynyshynRoman on 18.08.2016.
 */
@WebServlet(name = "registration", urlPatterns = "/registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPassword(request.getParameter("password"));
        String confirmPassword = request.getParameter("confirmPassword");

        Role role = new Role();
        role.setLogin(request.getParameter("login"));
        role.setRole("user");

        if (!confirmPassword.equals(user.getPassword())) {
            response.sendRedirect("registrationError.jsp");
        } else {
            ConnectionPool cp = (ConnectionPool) request.getServletContext().getAttribute("DBConnection");
            Connection connection = cp.getConnection();

            UserDAO userDAO = DAOFactory.getUserDAO(connection);

            List<String> logins = userDAO.getLogins();
            if (!logins.contains(user.getLogin())) {
                userDAO.insert(user);

                RoleDAO roleDAO = DAOFactory.getRoleDao(connection);
                roleDAO.insert(role);


                cp.freeConnection(connection);

                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("registrationError.jsp");
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
