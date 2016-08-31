package ua.nure.hrynyshyn.core.DBSupport.connectionPool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Primitive implementation of connection pool for reducing time for creating new connection on each request.
 * Based on array list. Implements singleton pattern. One instance of connection poll for all application.
 */
public class ConnectionPool {

    private static final Logger log = Logger.getLogger(ConnectionPool.class.getName());
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
            log.error("Can't register JDBC driver ", e);
        }
    }

    /**
     * Returns instance of connection pool.
     *
     * @param driver   jdbc driver class
     * @param URL      db locator
     * @param user     db user
     * @param password user password.
     * @param maxConn  max amount of connections.
     * @return connection pool instance
     */
    static synchronized public ConnectionPool
    getInstance(String driver, String URL, String user, String password, int maxConn) {
        if (instance == null) {
            instance = new ConnectionPool(driver, URL, user, password, maxConn);
        }
        return instance;
    }

    /**
     * Overloaded getConnection method. Used only after connection pool initialized.
     *
     * @return connection pool instance.
     */
    static synchronized public ConnectionPool getInstance() {
        return instance;
    }

    /**
     * Returns active connection from connections list. Removes all invalid connections.
     *
     * @return connection instance.
     */
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
            } catch (Exception e) {
                log.info("Removed bad connection ", e);
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
            log.error("Can't create a new connection for ", e);

        }
        return con;
    }

    /**
     * Returns connection to pool, or closes it, if pool is full.
     *
     * @param con connection for releasing.
     */
    public synchronized void freeConnection(Connection con) {
        // Put the connection at the end of the List
        if ((con != null) && (freeConnections.size() <= maxConn)) {
            freeConnections.add(con);
            log.info("Connection successfully free");
        } else {
            try {
                con.close();
                log.info("Connection closed");
            } catch (SQLException e) {
                log.error("Can't close connection", e);
            }

        }
    }

    /**
     * Closes all connections.
     */
    public synchronized void release() {
        Iterator allConnections = freeConnections.iterator();
        while (allConnections.hasNext()) {
            Connection con = (Connection) allConnections.next();
            try {
                con.close();
                log.info("Closed connection for pool ");
            } catch (SQLException e) {
                log.error("Can't close connection for pool ", e);
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
