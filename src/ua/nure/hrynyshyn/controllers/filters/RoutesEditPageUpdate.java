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
 * Simple filter. Runs each time when routesEdit.jsp page is requested. Updates routes and way stations tables content.
 */
@WebFilter(filterName = "RoutesEditPageUpdate")
public class RoutesEditPageUpdate implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        ConnectionPool cp = (ConnectionPool) request.getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();
        HttpSession session = request.getSession();
        session.setAttribute("routes", DAOFactory.getRouteDAO(connection).getAll());
        session.setAttribute("wayStations", DAOFactory.getWayStationDAO(connection).getAll());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
