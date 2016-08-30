package ua.nure.hrynyshyn.core.customTags;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by GrynyshynRoman on 29.08.2016.
 */
public class stationTag extends SimpleTagSupport {

    private int ID;

    public void setID(int ID) {
        this.ID = ID;
    }

    private String getStationName(int id){
        ConnectionPool cp=ConnectionPool.getInstance();
        Connection connection=cp.getConnection();
        String name= DAOFactory.getStationDAO(connection).getName(id);
        return name;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out=getJspContext().getOut();
        String name=getStationName(ID);
        out.println(name+" ("+ ID +")");
    }
}
