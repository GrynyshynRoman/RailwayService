package ua.nure.hrynyshyn.core.DBSupport.searchEngine;

import ua.nure.hrynyshyn.core.entities.railway.realEstate.Station;
import ua.nure.hrynyshyn.core.entities.railway.rollingStock.Carriage;

import java.util.Date;
import java.util.List;

/**
 * Created by GrynyshynRoman on 24.08.2016.
 */
public class SearchResult {

    private int train_ID;
    private Station departStation;
    private long departTime;
    private long wayTime;
    private Station destStation;
    private long destTime;
    List<Carriage> carriages;
    private double price;

    public int getTrain_ID() {
        return train_ID;
    }

    public void setTrain_ID(int train_ID) {
        this.train_ID = train_ID;
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
}
