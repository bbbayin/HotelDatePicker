package com.wz.caldroid.util;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2017/10/19.
 ****************************************/

public class Utils {
    private static String date_MM_dd = "MM-dd";
    private static String date_yyyy_MM = "yyyy-MM";
    private static String date_yyyy_MM_dd = "yyyy-MM-dd";
    private static SimpleDateFormat sMM_dd_format;
    private static SimpleDateFormat sMM_yyyy_format;
    private static SimpleDateFormat sMM_default_format;

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

    public static String formatDateToMMYYYY(Date date) {
        if (sMM_yyyy_format == null)
            sMM_yyyy_format = new SimpleDateFormat(date_yyyy_MM, Locale.CHINA);
        String format = sMM_yyyy_format.format(date);
        Log.d("---Utils---step1", format);
        return format;
    }

    public static String getDefaultDate(Date date) {
        if (sMM_default_format == null)
            sMM_default_format = new SimpleDateFormat(date_yyyy_MM_dd, Locale.CHINA);
        Log.d("默认格式时间：", sMM_default_format.format(date));
        return sMM_default_format.format(date);
    }

    public static String getCurrentDate() {
        return getDefaultDate(new Date());
    }

    public static Long getTimeStemp(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0l;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(date_yyyy_MM_dd,Locale.CHINA);
        Date d = null;
        try {
            d = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d.getTime();
    }

    public static List< Date > findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }
}
