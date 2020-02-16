package com.dfc.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: zsh
 * @Date:10:45 2018/5/19
 * @Description:: 获取今天星期几的工具类
 */
public class WhatToday {
    public static String whatToday(){
        String[] weekDays = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar=Calendar.getInstance();
        return weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }

    public static int getWeek(String str) throws Exception{
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date =sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //今天是本年第几周
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return week;
    }
}
