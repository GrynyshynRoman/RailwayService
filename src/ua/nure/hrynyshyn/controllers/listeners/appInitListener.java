package ua.nure.hrynyshyn.controllers.listeners; /**
 * Created by GrynyshynRoman on 17.08.2016.
 */

import org.apache.log4j.LogManager;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class appInitListener implements ServletContextListener {

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        ServletContext sc = sce.getServletContext();
        String driver = sc.getInitParameter("DB_Driver");
        String URL = sc.getInitParameter("DB_URL");
        String user = sc.getInitParameter("DB_User");
        String password = sc.getInitParameter("DB_Password");
        int maxConn = Integer.parseInt(sc.getInitParameter("Max_Conn"));
        ConnectionPool connectionPool = ConnectionPool.getInstance(driver, URL, user, password, maxConn);
        sc.setAttribute("DBConnection", connectionPool);

        sc.setInitParameter("language", "en");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        ConnectionPool connectionPool = (ConnectionPool) sce.getServletContext().getAttribute("DBConnection");
        connectionPool.release();

        LogManager.shutdown();
    }

}
