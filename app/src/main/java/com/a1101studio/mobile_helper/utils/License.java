package com.a1101studio.mobile_helper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andruy94 on 3/12/2017.
 */

public class License {
    public static boolean noLimited = false;
    private static final String startDate = "3/12/2017 00:00:00";
    public static Date getDateEndData(){
        Date dateEndData=null;
        try {

            dateEndData = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateEndData;

    }
    private static final String endDate = "3/30/2017 00:00:00";
    public static Date getDateStartDate(){
        Date dateEndData=null;
        try {

            dateEndData = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateEndData;

    }
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
}
