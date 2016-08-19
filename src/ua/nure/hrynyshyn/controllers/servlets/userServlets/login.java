package ua.nure.hrynyshyn.controllers.servlets.userServlets;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service.UserDAO;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
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
 * Created by GrynyshynRoman on 16.08.2016.
 */
@WebServlet(name = "login", urlPatterns = "/login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login=request.getParameter("login");
        String password=request.getParameter("password");

        ConnectionPool cp=(ConnectionPool)getServletContext().getAttribute("DBConnection");
        Connection connection=cp.getConnection();

        UserDAO dao= DAOFactory.getUserDAO(connection);
        User user=dao.getByLogin(login);

        cp.freeConnection(connection);

        if (user.getPassword().equals(password)){
            user.setLogged(true);
            HttpSession session=request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("index.jsp");
        }else {
            response.sendRedirect("loginError.html");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
