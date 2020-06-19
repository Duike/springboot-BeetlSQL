package com.zile.beetlsql.common.utils;

/**
 * 常数整合
 * Created by zileShi on 2020/6/18 0018.
 **/
public class Constant {

    /**
     * redis常数整合
     */
    public interface Redis {
        String USERID = "userId:";

        Integer EXPIRE_TIME_MINUTE = 60;// 过期时间, 60s, 一分钟
        Integer EXPIRE_TIME_TWO_MINUTE = 60 * 2;// 过期时间, 60s, 两分钟
        Integer EXPIRE_TIME_HOUR = 60 * 60;// 过期时间, 一小时
        Integer EXPIRE_TIME_TWO_HOUR = 60 * 60 * 2;// 过期时间, 两小时
        Integer EXPIRE_TIME_DAY = 60 * 60 * 24;// 过期时间, 一天
        Integer EXPIRE_TIME_MONTH = 60 * 60 * 24 * 15;// 过期时间, 一月

    }

    /**
     * 登录常数整合
     */
    public interface Login{
        String FAIL_PASSWORD = "登陆失败:用户名或密码错误";
        String SUCCESS_LOGIN = "登录成功";

        String SUCCESS_LOGIN_OUT = "退出成功";
        String FAIL_LOGIN_OUT = "退出失败";
    }

}
