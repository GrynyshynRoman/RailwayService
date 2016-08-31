package ua.nure.hrynyshyn.controllers.servlets.userServlets;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service.RoleDAO;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service.UserDAO;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.service.Role;
import ua.nure.hrynyshyn.core.entities.service.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Simple logging in process.
 * Simply checks equality of inputted password and password of this user in database.
 * By result redirects to login error page, or sets up user's session attributes, like user instance and user role.
 * And, finally, redirects to page from which logging process was caused
 */
@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        UserDAO userDAO = DAOFactory.getUserDAO(connection);
        User user = userDAO.getByLogin(login);

        RoleDAO roleDAO = DAOFactory.getRoleDAO(connection);
        Role role = roleDAO.getByLogin(login);


        if (user == null) {
            response.sendRedirect("loginError.jsp");
        } else {
            cp.freeConnection(connection);

            if (user.getPassword().equals(password)) {
                user.setLogged(true);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", role);
                String referer=request.getHeader("referer");
                response.sendRedirect(referer);
            } else {
                response.sendRedirect("loginError.jsp");
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
