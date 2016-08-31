package ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.railway;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.AbstractDAO;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static ua.nure.hrynyshyn.core.supportClasses.DateTimeSupport.parseDateTime;

/**
 * Data access object for way stations on routes.
 */
public class WayStationDAO extends AbstractDAO<WayStation> {
    /**
     * Returns all stations on specified route.
     *
     * @param id route id.
     * @return stations list.
     */
    public List<WayStation> getByRouteID(int id) {
        List<WayStation> stations = null;
        String sql = "SELECT *\n" +
                "FROM way_stations WHERE route_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            stations = parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Way stations getting by id fail", e);
        }
        return stations;
    }

    /**
     * Returns destination time for specified station on specified route.
     * @param route_ID route.
     * @param station_ID station.
     * @return time.
     */
    public long getDestTime(int route_ID, int station_ID) {
        long date = 0;
        String sql = "SELECT arrival_Time\n" +
                "FROM way_stations WHERE route_ID=? AND station_ID=?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, route_ID);
            statement.setInt(2, station_ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                date = parseDateTime(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error("Getting destination time failure", e);
        }
        return date;
    }
    /**
     * Returns department time for specified station on specified route.
     * @param route_ID route.
     * @param station_ID station.
     * @return time.
     */
    public long getDepartTime(int route_ID, int station_ID) {
        long date = 0;
        String sql = "SELECT depart_Time\n" +
                "FROM way_stations WHERE route_ID=? AND station_ID=?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, route_ID);
            statement.setInt(2, station_ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                date = parseDateTime(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error("Getting depart time failure", e);
        }
        return date;
    }

    /**
     * Removes all stations for route.
     * @param id route id.
     * @return true if operation success.
     */
    public boolean deleteByRouteID(int id) {
        String sql = "DELETE FROM way_stations WHERE route_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.info("Can't delete stations", e);
            return false;
        }
        return true;
    }

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
            station.setWaitingTime(resultSet.getInt(6));
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
        statement.setInt(5, object.getWaitingTime());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, WayStation object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
        statement.setInt(2, object.getStation_ID());
        statement.setTimestamp(3, new Timestamp(object.getArrivalTime()));
        statement.setTimestamp(4, new Timestamp(object.getDepartTime()));
        statement.setInt(5, object.getWaitingTime());
        statement.setInt(6, object.getWayStation_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, WayStation object) throws SQLException {
        statement.setInt(1, object.getWayStation_ID());
    }
    /**
     * Simple constructor.
     *
     * @param connection connection with database
     */
    public WayStationDAO(Connection connection) {
        super.connection = connection;
    }
}
