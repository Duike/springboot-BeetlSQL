package com.zile.beetlsql.controller.base;


import com.zile.beetlsql.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用Controller
 * Created by zileShi on 2019/7/3 0003.
 **/
public abstract class BaseController<T> {

    @Autowired
    private BaseServiceImpl<T> baseService;

    public BaseServiceImpl<T> getBaseService(){
        return this.baseService;
    }





}
