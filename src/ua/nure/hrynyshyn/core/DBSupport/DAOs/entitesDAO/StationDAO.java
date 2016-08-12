package ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.AbstractDAO;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrynyshynRoman on 04.08.2016.
 */
public class StationDAO extends AbstractDAO<Station> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO RAILWAY.STATIONS(name, city, state, country) VALUES (?,?,?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM RAILWAY.STATIONS WHERE station_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE RAILWAY.STATIONS SET name=?, city=?, state=?, country=? WHERE station_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM RAILWAY.STATIONS WHERE station_ID=?";
    }
    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM RAILWAY.STATIONS";
    }

    @Override
    protected List<Station> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Station> stations=new ArrayList<>();
        while (resultSet.next()){
            Station station=new Station();
            station.setStation_ID(resultSet.getInt(1));
            station.setName(resultSet.getString(2));
            station.setCity(resultSet.getString(3));
            station.setState(resultSet.getString(4));
            station.setCountry(resultSet.getString(5));
            stations.add(station);
        }
        return stations;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Station object) throws SQLException {
        statement.setString(1,object.getName());
        statement.setString(2,object.getCity());
        statement.setString(3,object.getState());
        statement.setString(4,object.getCountry());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Station object) throws SQLException {
        statement.setString(1,object.getName());
        statement.setString(2,object.getCity());
        statement.setString(3,object.getState());
        statement.setString(4,object.getCountry());
        statement.setInt(5,object.getStation_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Station object) throws SQLException {
        statement.setInt(1,object.getStation_ID());
    }

    public StationDAO(Connection connection) {
        super.connection=connection;
    }
}
