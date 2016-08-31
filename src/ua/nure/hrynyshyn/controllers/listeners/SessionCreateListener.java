package ua.nure.hrynyshyn.controllers.listeners; /**
 * Created by GrynyshynRoman on 28.08.2016.
 */

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

/**
 * Invokes each time, when new session is created(new user uses the application).
 * Sets default pages language and session life time.
 */
@WebListener()
public class SessionCreateListener implements
        HttpSessionListener {
    /**
     * Seconds in minute.
     */
    private static final int SEC_IN_MIN=60;

    public void sessionCreated(HttpSessionEvent se) {

        HttpSession session=se.getSession();
        session.setMaxInactiveInterval(10*SEC_IN_MIN);
        String defaultLanguage="en";
        session.setAttribute("language",defaultLanguage);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }


}
