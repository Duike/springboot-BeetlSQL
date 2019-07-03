package com.zile.beetlsql.common.utils;

import java.io.Serializable;

/**
 * 统一调用响应格式
 * Created by zileShi on 2019/7/3 0003.
 **/
public class JSONResult implements Serializable {


    //响应状态
    private String status;

    //响应消息
    private String message;

    //响应数据
    private Object data;


    public JSONResult(String status ,String message){
        this.status = status;
        this.message = message;
    }

    public JSONResult(String status ,String message,Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }


    /**
     * 成功
     *
     * @param message   成功信息
     * @return
     */
    public static JSONResult success(String message){
        return new JSONResult("success",message);
    }

    /**
     * 成功
     *
     * @param message   成功信息
     * @param data      成功数据
     * @return
     */
    public static JSONResult success(String message,Object data){
        return new JSONResult("success",message,data);
    }

    /**
     * 失败
     *
     * @param message   失败信息
     * @return
     */
    public static JSONResult fail(String message){
        return new JSONResult("fail",message);
    }

    /**
     * 失败
     * @param message   失败信息
     * @param data      失败数据
     * @return
     */
    public static JSONResult fail(String message,Object data){
        return new JSONResult("fail",message,data);
    }

    /**
     * 异常报错
     * @param message   异常信息
     * @return
     */
    public static JSONResult exception(String message) {
        return new JSONResult("异常报错",message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
