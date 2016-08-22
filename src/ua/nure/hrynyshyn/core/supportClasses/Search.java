package ua.nure.hrynyshyn.core.supportClasses;

import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by GrynyshynRoman on 22.08.2016.
 */
public class Search {
    private static Connection connection;

    public static void setConnection(Connection conn) {
        connection = conn;
    }

    public static List<Train> searchTrain(String departStation, String arriveStation, Date date) {
        List<Train> result = new ArrayList<>();
        String sql = "SELECT routes.route_ID\n" +
                "FROM trains\n" +
                "  JOIN routes ON trains.route_ID = routes.route_ID\n" +
                "   JOIN way_stations ON routes.route_ID = way_stations.route_ID\n" +
                "WHERE ((departStation_ID = ? AND departTime = ?) OR (station_ID=? AND departTime=? ))AND (destStation_ID = ? OR station_ID = ?)";
        try (PreparedStatement statement=connection.prepareStatement(sql)){
            ResultSet rs=statement.executeQuery();
            int route_ID=rs.getInt(1);
        }catch (SQLException e){

        }
        return result;
    }
    private static List<Route> searchRoutes(String departCity, String arriveCity, Date date){
return null;
        //// TODO: 22.08.2016
    }
}
