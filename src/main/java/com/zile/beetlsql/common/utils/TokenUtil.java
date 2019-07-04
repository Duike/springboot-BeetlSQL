package com.zile.beetlsql.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Token工具类
 * Created by zileShi on 2019/7/4 0004.
 **/
public class TokenUtil {


    /**
     * 生成token
     *
     * @param userId   用户Id
     * @return
     */
    public static String getToken(String userId,Date nowDate,Date expireDate) {

        String token = "";
        token = JWT.create()
                .withAudience(userId)
                .withIssuedAt(nowDate) //生成签名的时间
                .withExpiresAt(expireDate)//签名过期的时间
                .sign(Algorithm.HMAC256(userId));
        return token;
    }

    /**
     * 返回一定时间后的日期
     * @param date      开始计时的时间
     * @param year      增加的年
     * @param month     增加的月
     * @param day       增加的日
     * @param hour      增加的小时
     * @param minute    增加的分钟
     * @param second    增加的秒
     * @return
     */
    public static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }

}
