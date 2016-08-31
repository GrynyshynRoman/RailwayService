package ua.nure.hrynyshyn.core.DBSupport.searchEngine;

import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.entities.railway.realEstate.WayStation;

import java.util.List;

/**
 * Container of necessary data for displaying all information about train route.
 */
public class RouteInfo {
    private Route route;
    private Station departStation;
    private Station destStation;
    private List<WayStation> wayStations;
    private List<Station> stations;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<WayStation> getWayStations() {
        return wayStations;
    }

    public void setWayStations(List<WayStation> wayStations) {
        this.wayStations = wayStations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public Station getDepartStation() {
        return departStation;
    }

    public void setDepartStation(Station departStation) {
        this.departStation = departStation;
    }

    public Station getDestStation() {
        return destStation;
    }

    public void setDestStation(Station destStation) {
        this.destStation = destStation;
    }
}
