package ua.nure.hrynyshyn.core.DBSupport.DAOs;


import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.railway.*;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service.RoleDAO;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.service.UserDAO;

import java.sql.Connection;

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

    public static UserDAO getUserDAO(Connection connection) {
        return new UserDAO(connection);
    }

    public static RoleDAO getRoleDao(Connection connection){ return new RoleDAO(connection);}
}
