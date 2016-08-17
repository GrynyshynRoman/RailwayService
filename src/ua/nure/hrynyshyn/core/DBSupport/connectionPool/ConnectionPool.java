package ua.nure.hrynyshyn.core.DBSupport.connectionPool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by GrynyshynRoman on 08.08.2016.
 */
public class ConnectionPool {
    private static Logger log = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool instance;


    private String driver, URL, user, password;
    private int maxConn;

    private ArrayList<Connection> freeConnections = new ArrayList<>();

    private ConnectionPool(String driver, String URL, String user, String password, int maxConn) {
        this.driver = driver;
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;

        loadDrivers();
    }


    private void loadDrivers() {
        try {
            Class.forName(driver);
            log.info("Registered JDBC driver ");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Can't register JDBC driver ", e);
        }
    }

    static synchronized public ConnectionPool
    getInstance(String driver, String URL, String user, String password, int maxConn) {
        if (instance == null) {
            instance = new ConnectionPool(driver, URL, user, password, maxConn);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection con;
        if (!freeConnections.isEmpty()) {
            con = freeConnections.get(freeConnections.size() - 1);
            freeConnections.remove(con);
            try {
                if (con.isClosed()) {
                    log.info("Removed bad connection ");
                    // Try again recursively
                    con = getConnection();
                }
            } catch (SQLException e) {
                log.log(Level.INFO, "Removed bad connection ", e);
                // Try again recursively
                con = getConnection();
            } catch (Exception e) {
                log.log(Level.INFO, "Removed bad connection ", e);
                // Try again recursively
                con = getConnection();
            }
        } else {
            con = newConnection();
        }
        return con;
    }

    private Connection newConnection() {
        Connection con = null;
        try {
            if (user == null) {
                con = DriverManager.getConnection(URL);
            } else {
                con = DriverManager.getConnection(URL, user, password);
            }
            log.info("Created a new connection in pool ");
        } catch (SQLException e) {
            log.log(Level.INFO, "Can't create a new connection for ", e);

        }
        return con;
    }

    public synchronized void freeConnection(Connection con) {
        // Put the connection at the end of the List
        if ((con != null) && (freeConnections.size() <= maxConn)) {
            freeConnections.add(con);
            log.log(Level.INFO, "Connection successfully free");
        }else {
            try {
                con.close();
                freeConnections.add(con);
                log.log(Level.INFO, "Connection closed");
            }catch (SQLException e){
                log.log(Level.SEVERE, "Can't close connection", e);
            }

        }
    }

    public synchronized void release() {
        Iterator allConnections = freeConnections.iterator();
        while (allConnections.hasNext()) {
            Connection con = (Connection) allConnections.next();
            try {
                con.close();
                log.info("Closed connection for pool ");
            } catch (SQLException e) {
                log.log(Level.SEVERE, "Can't close connection for pool ", e);
            }
        }
        freeConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {

        super.finalize();
        release();
    }
}
