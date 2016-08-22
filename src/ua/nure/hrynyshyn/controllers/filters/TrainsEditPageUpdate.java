package ua.nure.hrynyshyn.controllers.filters;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by GrynyshynRoman on 22.08.2016.
 */
@WebFilter(filterName = "trainsEditPageUpdate")
public class TrainsEditPageUpdate implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        ConnectionPool cp=(ConnectionPool)request.getServletContext().getAttribute("DBConnection");
        Connection connection=cp.getConnection();
        HttpSession session=request.getSession();
        session.setAttribute("trains", DAOFactory.getTrainDAO(connection).getAll());
        session.setAttribute("carriages", DAOFactory.getCarriageDAO(connection).getAll());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
