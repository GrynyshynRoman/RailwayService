package ua.nure.hrynyshyn.core.entities.service;

import java.util.Date;

/**
 * Created by GrynyshynRoman on 03.08.2016.
 */
public class Ticket {
    private int ticket_ID;
    private int user_ID;
    private int train_ID;
    private int deptStation_ID;
    private Date deptTime;
    private int destStation_ID;
    private Date destTime;

    public int getTicket_ID() {
        return ticket_ID;
    }

    public void setTicket_ID(int ticket_ID) {
        this.ticket_ID = ticket_ID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public int getTrain_ID() {
        return train_ID;
    }

    public void setTrain_ID(int train_ID) {
        this.train_ID = train_ID;
    }

    public int getDeptStation_ID() {
        return deptStation_ID;
    }

    public void setDeptStation_ID(int deptStation_ID) {
        this.deptStation_ID = deptStation_ID;
    }

    public Date getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(Date deptTime) {
        this.deptTime = deptTime;
    }

    public int getDestStation_ID() {
        return destStation_ID;
    }

    public void setDestStation_ID(int destStation_ID) {
        this.destStation_ID = destStation_ID;
    }

    public Date getDestTime() {
        return destTime;
    }

    public void setDestTime(Date destTime) {
        this.destTime = destTime;
    }
}
