package com.itcoder.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertUtils {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static String getStringDate(Date date){
        try {
            return FORMAT.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
