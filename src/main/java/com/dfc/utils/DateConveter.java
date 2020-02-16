package com.dfc.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zsh
 * @Date:16:59 2018/5/6
 * @Description:
 */
public class DateConveter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {

        try {
            if(null != source){
                DateFormat df = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                return df.parse(source);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    public static String convert(Date rtn) {
        try {
            if(null != rtn){
                DateFormat df = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                return df.format(rtn);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

}
