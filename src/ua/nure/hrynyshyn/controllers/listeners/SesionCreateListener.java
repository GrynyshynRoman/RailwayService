package ua.nure.hrynyshyn.controllers.listeners; /**
 * Created by GrynyshynRoman on 28.08.2016.
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener()
public class SesionCreateListener implements
        HttpSessionListener {

    private static final int SEC_IN_MIN=60;

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
        HttpSession session=se.getSession();
        session.setMaxInactiveInterval(10*SEC_IN_MIN);
        String defaultLanguage="en";
        session.setAttribute("language",defaultLanguage);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }


}
