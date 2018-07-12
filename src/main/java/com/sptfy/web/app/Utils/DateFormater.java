package com.sptfy.web.app.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {

    public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
