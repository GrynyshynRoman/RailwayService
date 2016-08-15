package ua.nure.hrynyshyn.core.moderating;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.RouteDAO;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.StationDAO;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;

import java.util.List;

/**
 * Created by HrynyshynRoman on 04.08.2016.
 */
public class Administrator {
    private StationDAO stationDAO =DAOFactory.getStationDAO();
    private RouteDAO routeDAO=DAOFactory.getRouteDAO();
    public void addStation(Station station){
        stationDAO.insert(station);
    }
    public void deleteStation(Station station){
        stationDAO.delete(station);

    }
    public void editStation(Station station){
        stationDAO.update(station);
    }
    public List<Station> getAllStations(){
        return stationDAO.getAll();
    }

    public void createRoute(Route route){
        routeDAO.insert(route);
    }
}
