package ua.nure.hrynyshyn.core.moderating;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.RouteDAO;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.StationDAO;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;

import java.util.List;

/**
 * Created by HrynyshynRoman on 04.08.2016.
 */
public class Administrator {

    public static void addStation(Station station){
        DAOFactory.getStationDAO().insert(station);
    }
    public static void deleteStation(Station station){
        DAOFactory.getStationDAO().delete(station);

    }
    public static void editStation(Station station){
        DAOFactory.getStationDAO().update(station);
    }
    public static List<Station> getAllStations(){
        return DAOFactory.getStationDAO().getAll();
    }

    public static void createRoute(Route route){
        DAOFactory.getRouteDAO().insert(route);
    }
    public static void addWayStation(WayStation station){
        DAOFactory.getWayStationDAO().insert(station);
    }
}
