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


    public static RouteDAO getRouteDAO(Connection connection) {
        return new RouteDAO(connection);
    }

    public static StationDAO getStationDAO(Connection connection) {
        return new StationDAO(connection);
    }

    public static WayStationDAO getWayStationDAO(Connection connection) {
        return new WayStationDAO(connection);
    }

    public static CarriageDAO getCarriageDAO(Connection connection) {
        return new CarriageDAO(connection);
    }

    public static TrainDAO getTrainDAO(Connection connection) {
        return new TrainDAO(connection);
    }
}
