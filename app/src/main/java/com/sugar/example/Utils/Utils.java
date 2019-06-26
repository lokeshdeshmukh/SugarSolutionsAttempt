package com.sugar.example.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Utils {

    public static String formatDate(String dateString) {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd,yyyy");
            return outputFormat.format(sdf.parse(dateString));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";

    }
}
