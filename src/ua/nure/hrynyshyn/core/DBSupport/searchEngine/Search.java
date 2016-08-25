package ua.nure.hrynyshyn.core.DBSupport.searchEngine;


import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;

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
 * Created by GrynyshynRoman on 22.08.2016.
 */
public class Search {
    private Station departStation;
    private Station destStation;
    private Date departDate;
    private Connection connection;
    private List<SearchResult> searchResults = new ArrayList<>();


    public List<SearchResult> search() {
        List<Train> trains = searchTrains();
        List<SearchResult> searchResults = new ArrayList<>();
        for (Train train : trains) {
            SearchResult searchResult = new SearchResult();
            searchResult.setTrain_ID(train.getTrain_ID());
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
     * Searching for suitable trains on founded routes.
     *
     * @return list of trains
     */
    private List<Train> searchTrains() {
        List<Train> trains = new ArrayList<>();
        List<Integer> routes = searchRoute_IDs();
        for (Integer route_ID : routes) {
            trains.addAll(DAOFactory.getTrainDAO(connection).getByRouteID(route_ID));
        }
        String sql = "SELECT *\n" +
                "FROM trains JOIN carriages ON trains.train_ID = carriages.train_ID\n" +
                "WHERE trains.train_ID=? AND carriages.reservedSeats < carriages.totalSeats";
        List<Train> result = new ArrayList<>();
        for (Train train : trains) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, train.getTrain_ID());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Train t = new Train();
                    t.setTrain_ID(rs.getInt(1));
                    t.setRoute_ID(rs.getInt(2));
                    result.add(train);
                }

            } catch (SQLException e) {
                //// TODO: 23.08.2016 logging
            }
        }
        return result;
    }

    /**
     * Searching for suitable routes by specified depart and destination stations in specified date.
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
            //// TODO: 23.08.2016
        }
        return routes_IDs;
    }

    public Search(Station departStation, Station destStation, Date departDate, Connection connection) {
        this.departStation = departStation;
        this.destStation = destStation;
        this.departDate = departDate;
        this.connection = connection;
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
