package ua.nure.hrynyshyn.core.DBSupport.searchEngine;


import org.apache.log4j.Logger;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;


import ua.nure.hrynyshyn.core.DBSupport.connectionPool.ConnectionPool;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Contains all methods for searching process between two stations on selected date.
 */
public class Search {

    private static final Logger log = Logger.getLogger(Search.class.getName());

    private Station departStation;
    private Station destStation;
    private Date departDate;
    private Connection connection;

    /**
     * Last step of searching. Collects data to searchResults for displaying to user.
     *
     * @return list of search results.
     */
    public List<SearchResult> search() {

        List<Train> trains = searchTrains();

        List<SearchResult> searchResults = new ArrayList<>();
        int resultNumber = 1;
        for (Train train : trains) {
            SearchResult searchResult = new SearchResult();
            searchResult.setResult_ID(resultNumber++);
            searchResult.setTrain(train);
            searchResult.setDepartStation(departStation);
            searchResult.setDestStation(destStation);
            long departTime = determineDepartTime(train.getRoute_ID(), departStation.getStation_ID());
            long destTime = determineArriveTime(train.getRoute_ID(), destStation.getStation_ID());
            long wayTime = destTime - departTime;
            searchResult.setDepartTime(determineDepartTime(train.getRoute_ID(), departStation.getStation_ID()));
            searchResult.setWayTime(wayTime);
            searchResult.setDestTime(destTime);
            searchResult.setCarriages(DAOFactory.getCarriageDAO(connection).getNotFullCarriages(train.getTrain_ID()));
            searchResults.add(searchResult);

        }
        return searchResults;
    }

    /**
     * Second step.
     * Searching for suitable trains on founded routes.
     *
     * @return list of trains
     */
    private List<Train> searchTrains() {

        List<Train> potentialTrains = new ArrayList<>();

        List<Integer> routes = searchRoute_IDs();
        /*
         collecting all trains from founded routes.
         */
        for (Integer route_ID : routes) {
            potentialTrains.addAll(DAOFactory.getTrainDAO(connection).getByRouteID(route_ID));
        }
        /*
        Query for getting trains with available seats.
        Returns duplicate trains data for each not full carriage.
        Need to consider this feature.
         */
        String sql = "SELECT *\n" +
                "FROM trains JOIN carriages ON trains.train_ID = carriages.train_ID\n" +
                "WHERE trains.train_ID=? AND carriages.reservedSeats < carriages.totalSeats";
        List<Train> result = new ArrayList<>();
        List<Integer> selectedTrainsIDs = new ArrayList<>();
        for (Train potentialTrain : potentialTrains) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, potentialTrain.getTrain_ID());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Train t = new Train();
                    t.setTrain_ID(rs.getInt(1));
                    t.setRoute_ID(rs.getInt(2));
                    /*
                    Check, if founded train already exist in result list. If true, don't add it.
                     */
                    if (!selectedTrainsIDs.contains(t.getTrain_ID())) {
                        result.add(potentialTrain);
                        selectedTrainsIDs.add(potentialTrain.getTrain_ID());
                    }
                }
            } catch (SQLException e) {
                log.error("Train search failure", e);
            }
        }
        return result;
    }

    /**
     * First step of searching.
     * Searching for suitable routes by specified depart and destination stations in specified date.
     * <p>
     * !!Cannot find routes by two way stations. Needs more work on searching query.
     *
     * @return ID's of suitable routes.
     */
    private List<Integer> searchRoute_IDs() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String sqlDate = df.format(departDate);
        List<Integer> routes_IDs = new ArrayList<>();
        String sql = "SELECT routes.route_ID\n" +
                "FROM routes\n" +
                "  JOIN way_stations ON routes.route_ID = way_stations.route_ID\n" +
                "WHERE (departStation_ID = ? OR station_ID = ?) AND (destStation_ID = ? OR station_ID = ?) AND\n" +
                "      ((DATE(departTime) BETWEEN ? AND (? + hour(23) + minute(59))) OR\n" +
                "       DATE((arrival_Time) BETWEEN ? AND (? + hour(23) + minute(59))))";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departStation.getStation_ID());
            statement.setInt(2, departStation.getStation_ID());
            statement.setInt(3, destStation.getStation_ID());
            statement.setInt(4, destStation.getStation_ID());
            statement.setString(5, sqlDate);
            statement.setString(6, sqlDate);
            statement.setString(7, sqlDate);
            statement.setString(8, sqlDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                if (!routes_IDs.contains(id)) {
                    routes_IDs.add(id);
                }
            }
        } catch (SQLException e) {
            log.error("Route search failure", e);
        }
        return routes_IDs;
    }

    public Search(Station departStation, Station destStation, Date departDate) {
        this.departStation = departStation;
        this.destStation = destStation;
        this.departDate = departDate;
        connection = ConnectionPool.getInstance().getConnection();
    }


    private long determineDepartTime(int route_ID, int station_ID) {
        long date = DAOFactory.getRouteDAO(connection).getDepartTime(route_ID, station_ID);
        if (date == 0) {
            date = DAOFactory.getWayStationDAO(connection).getDepartTime(route_ID, station_ID);
        }
        return date;
    }

    private long determineArriveTime(int route_ID, int station_ID) {
        long date = DAOFactory.getRouteDAO(connection).getDestTime(route_ID, station_ID);
        if (date == 0) {
            date = DAOFactory.getWayStationDAO(connection).getDestTime(route_ID, station_ID);
        }
        return date;
    }
}
