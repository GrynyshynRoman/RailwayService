package ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.railway;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.AbstractDAO;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrynyshynRoman on 04.08.2016.
 */
public class WayStationDAO extends AbstractDAO<WayStation> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO RAILWAY.WAY_STATIONS (route_ID,station_ID,arrival_Time,depart_Time, waiting_Time)"
                + "VALUES(?,?,?,?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM RAILWAY.WAY_STATIONS WHERE wayStation_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE RAILWAY.WAY_STATIONS SET route_ID=?,station_ID=?,arrival_Time=?,depart_Time=?, waiting_Time=? WHERE wayStation_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM RAILWAY.WAY_STATIONS WHERE wayStation_ID=?";
    }
    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM RAILWAY.WAY_STATIONS";
    }

    @Override
    protected List<WayStation> parseResultSet(ResultSet resultSet) throws SQLException {
        List<WayStation> stations = new ArrayList<>();
        while (resultSet.next()) {
            WayStation station = new WayStation();
            station.setWayStation_ID(resultSet.getInt(1));
            station.setRoute_ID(resultSet.getInt(2));
            station.setStation_ID(resultSet.getInt(3));
            station.setArrivalTime(resultSet.getTimestamp(4).getTime());
            station.setDepartTime(resultSet.getTimestamp(5).getTime());
            station.setWaitingTime(resultSet.getTime(6).getTime());
            stations.add(station);
        }
        return stations;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, WayStation object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
        statement.setInt(2, object.getStation_ID());
        statement.setTimestamp(3, new Timestamp(object.getArrivalTime()));
        statement.setTimestamp(4, new Timestamp(object.getDepartTime()));
        statement.setTime(5, new Time(object.getWaitingTime()));
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, WayStation object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
        statement.setInt(2, object.getStation_ID());
        statement.setTimestamp(3, new Timestamp(object.getArrivalTime()));
        statement.setTimestamp(4, new Timestamp(object.getDepartTime()));
        statement.setTime(5, new Time(object.getWaitingTime()));
        statement.setInt(6, object.getWayStation_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, WayStation object) throws SQLException {
        statement.setInt(1, object.getWayStation_ID());
    }
    public WayStationDAO(Connection connection) {
        super.connection=connection;
    }
}
