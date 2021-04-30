package org.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getCurrentDatePath()throws Exception{
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }

}
