package ua.nure.hrynyshyn.core.customTags;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Connection;

/**
 * Simple custom tag. Used for displaying station name by it's id-number.
 * Have additional attribute "showID". It's true by default. Defines show or not station id after name.
 */
public class stationTag extends SimpleTagSupport {
    /**
     * Station id.
     */
    private int ID;
    /**
     * Definer, set station id visible in output, or hide it.
     */
    private boolean showID = true;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setShowID(boolean showID) {
        this.showID = showID;
    }

    private String getStationName(int id) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        return DAOFactory.getStationDAO(connection).getName(id);
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String name = getStationName(ID);
        if (showID) {
            out.println(name + " (" + ID + ")");
        } else {
            out.println(name);
        }

    }
}
