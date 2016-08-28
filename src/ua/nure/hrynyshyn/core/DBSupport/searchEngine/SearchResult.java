package ua.nure.hrynyshyn.core.DBSupport.searchEngine;

import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Carriage;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Train;

import java.util.List;

/**
 * Created by GrynyshynRoman on 24.08.2016.
 */
public class SearchResult {

    private Train train;
    private Station departStation;
    private long departTime;
    private long wayTime;
    private Station destStation;
    private long destTime;
    List<Carriage> carriages;
    private double price;
    private RouteInfo routeInfo;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getDepartStation() {
        return departStation;
    }

    public void setDepartStation(Station departStation) {
        this.departStation = departStation;
    }

    public long getDepartTime() {
        return departTime;
    }

    public void setDepartTime(long departTime) {
        this.departTime = departTime;
    }

    public long getWayTime() {
        return wayTime;
    }

    public void setWayTime(long wayTime) {
        this.wayTime = wayTime;
    }

    public Station getDestStation() {
        return destStation;
    }

    public void setDestStation(Station destStation) {
        this.destStation = destStation;
    }

    public long getDestTime() {
        return destTime;
    }

    public void setDestTime(long destTime) {
        this.destTime = destTime;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(RouteInfo routeInfo) {
        this.routeInfo = routeInfo;
    }
}
