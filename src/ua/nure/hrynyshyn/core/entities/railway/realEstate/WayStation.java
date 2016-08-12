package ua.nure.hrynyshyn.core.entities.railway.realEstate;

import java.sql.Date;

/**
 * Created by GrynyshynRoman on 04.08.2016.
 */
public class WayStation{
    private int wayStation_ID;
    private int route_ID;
    private int station_ID;
    private long arrivalTime;
    private long departTime;
    private long waitingTime;

    public int getWayStation_ID() {
        return wayStation_ID;
    }

    public void setWayStation_ID(int wayStation_ID) {
        this.wayStation_ID = wayStation_ID;
    }

    public int getRoute_ID() {
        return route_ID;
    }

    public void setRoute_ID(int route_ID) {
        this.route_ID = route_ID;
    }

    public int getStation_ID() {
        return station_ID;
    }

    public void setStation_ID(int station_ID) {
        this.station_ID = station_ID;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDepartTime() {
        return departTime;
    }

    public void setDepartTime(long departTime) {
        this.departTime = departTime;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }
}
