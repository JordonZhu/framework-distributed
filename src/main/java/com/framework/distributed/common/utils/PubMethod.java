package com.framework.distributed.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created with: jingyan.
 * Date: 2016/9/16  23:03
 * Description:
 */
public class PubMethod {


    /**
     * Created with: jingyan.
     * Date: 2016/9/25  18:41
     * Description: 各种非空判断
     */
    public static boolean isEmpty(String Value) {

        return (Value == null || Value.trim().equals(""));
    }

    public static boolean isEmpty(StringBuffer Value) {

        return (Value == null || (Value.toString().trim()).equals(""));
    }

    public static boolean isEmpty(List list) {
        if (list == null || list.size() == 0)
            return true;
        else
            return false;
    }

    public static boolean isEmpty(Set set) {
        if (set == null || set.size() == 0)
            return true;
        else
            return false;
    }

    public static boolean isEmpty(Map map) {
        if (map == null || map.size() == 0)
            return true;
        else
            return false;
    }

    public static boolean isEmpty(Object Value) {
        if (Value == null)
            return true;
        else
            return false;
    }

    public static boolean isEmpty(Double value) {
        if (value == null || value.doubleValue() == 0.0)
            return true;
        else
            return false;
    }

    public static boolean isEmpty(Long obj) {
        if (obj == null || obj.longValue() == 0)
            return true;
        else
            return false;
    }

    public static boolean isEmpty(Object[] Value) {
        if (Value == null || Value.length == 0)
            return true;
        else
            return false;
    }


    /**
     * Created with: jingyan.
     * Date: 2016/9/25  19:11
     * Description: str to date
     */
    public static Date str2Date(String strDate, String strFormat) {
        if (isEmpty(strFormat)) {
            strFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Created with: jingyan.
     * Date: 2016/10/25  14:43
     * Description: date to str
     */
    public static String date2Str(Date date, String formatStr) {
        if (PubMethod.isEmpty(date)) {
            date = new Date();
        }
        if (PubMethod.isEmpty(formatStr)) {
            formatStr = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String formatDate = sdf.format(date);
        return formatDate;
    }

}
