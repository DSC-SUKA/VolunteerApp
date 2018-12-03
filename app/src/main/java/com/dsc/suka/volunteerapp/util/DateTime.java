package com.dsc.suka.volunteerapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    public static String dateTimeParser(String oldDate, String oldFormatPattern, String newFormatPattern){
        SimpleDateFormat sdf = new SimpleDateFormat(oldFormatPattern);
        Date tempDate = null;
        try {
            tempDate = sdf.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat(newFormatPattern);
        String newDate = newFormat.format(tempDate);

        return newDate;
    }
}
