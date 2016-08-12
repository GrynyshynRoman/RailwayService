package ua.nure.hrynyshyn.core.DBSupport.DAOs;


import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.*;
import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by GrynyshynRoman on 18.07.2016.
 */
public class DAOFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/RAILWAY";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static ConnectionPool dbcp = ConnectionPool.getInstance(URL, USER, PASSWORD, 20);

    public static RouteDAO getRouteDAO() {
        return new RouteDAO(dbcp.getConnection());
    }

    public static StationDAO getStationDAO() {
        return new StationDAO(dbcp.getConnection());
    }

    public static WayStationDAO getWayStationDAO() {
        return new WayStationDAO(dbcp.getConnection());
    }

    public static CarriageDAO getCarriageDAO() {
        return new CarriageDAO(dbcp.getConnection());
    }

    public static TrainDAO getTrainDAO() {
        return new TrainDAO(dbcp.getConnection());
    }
}
