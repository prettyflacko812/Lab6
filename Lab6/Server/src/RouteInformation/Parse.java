package RouteInformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

public class Parse {
    public static boolean isEmpty, isInteger, isDouble, isLong, isFloat, isDate, isDateTime;
    public static String str;
    public static Integer integ;
    public static Double doub;
    public static Long lon;
    public static Float fl;
    public static Date datei;
    public static ZonedDateTime zdt;

    public static void Convert(String line) {
        isEmpty = (line == null) || (line.length() == 0);
        integ = 0;
        lon = (long) 0;
        fl = 0f;
        datei = null;
        zdt = null;
        if (isEmpty) {
            isInteger = isLong = isFloat = isDateTime = isDate = false;
        } else {
            str = line;
            try {
                fl = Float.valueOf(str);
                isFloat = true;
            } catch (RuntimeException e) {
                isFloat = false;
            }
            try {
                doub = Double.valueOf(str);
                isDouble = true;
            } catch (RuntimeException e) {
                isDouble = false;
            }
            try {
                integ = Integer.valueOf(str);
                isInteger = true;
            } catch (RuntimeException e) {
                isInteger = false;
            }
            try {
                lon = Long.valueOf(str);
                isLong = true;
            } catch (RuntimeException e) {
                isLong = false;
            }
            try {
                datei = new SimpleDateFormat("dd.MM.yyyy").parse(str);
                isDate = true;
            } catch (ParseException ex) {
                isDate = false;
            }
            try {
                zdt = java.time.ZonedDateTime.parse(str);
                isDateTime = true;
            } catch (RuntimeException e) {
                isDateTime = false;
            }
        }
    }

    public static boolean IsValue(Check с) {
        if (с.ostStr.length() > 0) {
            int i = с.ostStr.indexOf(",");
            if (i >= 0) {
                с.val = с.ostStr.substring(0, i);
                с.val = с.val.trim();
                if (с.ostStr.length() > i + 1) {
                    с.ostStr = с.ostStr.substring(i + 1);
                } else {
                    с.ostStr = "";
                }
                String s = с.val.trim();
                if (с.val.isEmpty()) {
                    return false;
                }
            } else {
                с.val = с.ostStr;
            }
            Convert(с.val);
            return true;
        }
        return false;
    }
}

