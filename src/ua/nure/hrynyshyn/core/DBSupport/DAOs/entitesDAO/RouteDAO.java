package ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.AbstractDAO;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrynyshynRoman on 04.08.2016.
 */
public class RouteDAO extends AbstractDAO<Route> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO RAILWAY.ROUTES (departStation, departTime, destStation, destTime) VALUES(?,?,?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM RAILWAY.ROUTES WHERE route_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE RAILWAY.ROUTE SET departStation=?, departTime=?, destStation=?, destTime=? WHERE route_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM RAILWAY.ROUTES WHERE route_ID=?";
    }
    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM RAILWAY.ROUTES";
    }


    @Override
    protected List<Route> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Route> routes = new ArrayList<>();
        while (resultSet.next()) {
            Route route = new Route();
            route.setRoute_ID(resultSet.getInt(1));
            route.setDepartStation_ID(resultSet.getInt(2));
            route.setDepartTime(resultSet.getTimestamp(3).getTime());
            route.setDestStation_ID(resultSet.getInt(4));
            route.setDestTime(resultSet.getTimestamp(5).getTime());
            routes.add(route);
        }
        return routes;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Route object) throws SQLException {
        statement.setInt(1, object.getDepartStation_ID());
        statement.setTimestamp(2, new Timestamp(object.getDepartTime()));
        statement.setInt(3, object.getDestStation_ID());
        statement.setTimestamp(4, new Timestamp(object.getDestTime()));
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Route object) throws SQLException {
        statement.setInt(1, object.getDepartStation_ID());
        statement.setTimestamp(2, new Timestamp(object.getDepartTime()));
        statement.setInt(3, object.getDestStation_ID());
        statement.setTimestamp(4, new Timestamp(object.getDestTime()));
        statement.setInt(5, object.getRoute_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Route object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
    }

    public RouteDAO(Connection connection) {
        super.connection = connection;
    }
}
