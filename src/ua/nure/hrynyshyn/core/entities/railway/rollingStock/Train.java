package ua.nure.hrynyshyn.core.entities.railway.rollingStock;


import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;

import java.util.List;

/**
 * Created by HrynyshynRoman on 03.08.2016.
 */
public class Train {
    private int train_ID;
    private int route_ID;

    public int getTrain_ID() {
        return train_ID;
    }

    public void setTrain_ID(int train_ID) {
        this.train_ID = train_ID;
    }

    public int getRoute_ID() {
        return route_ID;
    }

    public void setRoute_ID(int route_ID) {
        this.route_ID = route_ID;
    }
}
