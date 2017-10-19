package com.wz.caldroid.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2017/10/19.
 ****************************************/

public class Utils {
    private static String date_MM_dd = "MM-dd";
    private static String date_yyyy_MM = "yyyy-MM";
    private static SimpleDateFormat sMM_dd_format;
    private static SimpleDateFormat sMM_yyyy_format;

    /**
     * 格式化日期 10月28日
     *
     * @param date
     * @return
     */
    public static String formatDateToMMdd(Date date) {
        if (sMM_dd_format == null)
            sMM_dd_format = new SimpleDateFormat(date_MM_dd, Locale.CHINA);
        String format = sMM_dd_format.format(date);
        String step1 = format.replace("-", "月");
        Log.d("---Utils---step1", step1);
        String result = step1 + "日";
        Log.d("---Utils---result", result);
        return result;
    }

    public static String formatDateToMMYYYY(Date date){
        if (sMM_yyyy_format == null)
            sMM_yyyy_format = new SimpleDateFormat(date_yyyy_MM, Locale.CHINA);
        String format = sMM_yyyy_format.format(date);
        Log.d("---Utils---step1", format);
        return format;
    }
}
