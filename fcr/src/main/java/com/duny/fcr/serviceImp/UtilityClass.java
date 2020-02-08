package com.duny.fcr.serviceImp;

import java.util.Base64;

public class UtilityClass {

    public String base64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String getMonthName(int i) {
        String[] month = new String[12];
        month[0] = "January";
        month[1] = "February";
        month[2] = "March";
        month[3] = "April";
        month[4] = "May";
        month[5] = "June";
        month[6] = "July";
        month[7] = "August";
        month[8] = "September";
        month[9] = "October";
        month[10] = "November";
        month[11] = "December";
        return month[i];
    }

}
