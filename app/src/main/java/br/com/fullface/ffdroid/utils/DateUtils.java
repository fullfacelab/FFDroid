package br.com.fullface.ffdroid.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("kk:mm");
        return timeFormatter.format(date);
    }
}
