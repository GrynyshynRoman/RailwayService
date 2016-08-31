package ua.nure.hrynyshyn.core.entities.railway.realEstate;

/**
 * Container for data about way stations.
 */
public class WayStation{
    private int wayStation_ID;
    private int route_ID;
    private int station_ID;
    private long arrivalTime;
    private long departTime;
    private int waitingTime;

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

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
}
