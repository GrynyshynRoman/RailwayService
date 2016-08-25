package ua.nure.hrynyshyn.core.supportClasses;


import java.util.concurrent.TimeUnit;

/**
 * Created by GrynyshynRoman on 25.08.2016.
 */
public class TimeFormatter {
    String time;
    private long total;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setTotal(long total) {
        this.total = total;
       long hours= TimeUnit.MILLISECONDS.toHours(total);
       long minutes=TimeUnit.HOURS.toMinutes(hours)-TimeUnit.MILLISECONDS.toMinutes(total);
        time= String.format("%02d : %02d", hours,minutes);
    }
}
