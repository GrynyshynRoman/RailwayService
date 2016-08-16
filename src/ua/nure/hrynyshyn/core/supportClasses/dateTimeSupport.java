package ua.nure.hrynyshyn.core.supportClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by GrynyshynRoman on 16.08.2016.
 */
public final class dateTimeSupport {
    public static long parseDate(String date){
        long result=0;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try{
            result=dateFormat.parse(date).getTime();
        }catch (ParseException e){
            System.err.println(e);
            //TODO
        }
        return result;
    }
    public static long parseTime(String time){
        long result=0;
        SimpleDateFormat dateFormat=new SimpleDateFormat("H:mm");
        try{
            result=dateFormat.parse(time).getTime();
        }catch (ParseException e){
            System.err.println(e);
            //TODO
        }
        return result;
    }
}
