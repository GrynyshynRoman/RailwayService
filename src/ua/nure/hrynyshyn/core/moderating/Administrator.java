package ua.nure.hrynyshyn.core.moderating;

import ua.nure.hrynyshyn.core.DBSupport.DAOs.DAOFactory;
import ua.nure.hrynyshyn.core.DBSupport.DAOs.entitesDAO.StationDAO;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;

import java.util.List;

/**
 * Created by HrynyshynRoman on 04.08.2016.
 */
public class Administrator {
    private StationDAO dao=DAOFactory.getStationDAO();
    public void addStation(Station station){
        dao.insert(station);
    }
    public void deleteStation(Station station){
        dao.delete(station);

    }
    public void editStation(Station station){
        dao.update(station);
    }
    public List<Station> getAllStations(){
        return dao.getAll();
    }

    public void createRoute(){}
}
