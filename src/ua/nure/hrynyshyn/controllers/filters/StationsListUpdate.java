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
 *  Simple filter. Runs each time when stationsEdit.jsp page is requested. Updates stations table content.
 */
@WebFilter(filterName = "StationsListUpdate")
public class StationsListUpdate implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        ConnectionPool cp=(ConnectionPool)request.getServletContext().getAttribute("DBConnection");
        Connection connection=cp.getConnection();
        HttpSession session=request.getSession();
        session.setAttribute("stations", DAOFactory.getStationDAO(connection).getAll());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
