package lk.grb.ceylonPottersPaletteLayered.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    public static String dateNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static String dateNowFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        return format.format(new Date());
    }

    public static String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss"); //In 24hr Format
        return dateFormat.format(new Date()) ;
    }
}
