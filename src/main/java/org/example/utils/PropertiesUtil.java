package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static String getValue(String key){
        Properties prop=new Properties();
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("spider.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);

    }





}
