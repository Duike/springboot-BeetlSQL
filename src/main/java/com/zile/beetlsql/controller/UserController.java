package com.zile.beetlsql.controller;

import com.zile.beetlsql.common.annotations.UserLoginToken;
import com.zile.beetlsql.model.User;
import com.zile.beetlsql.controller.base.BaseController;
import com.zile.beetlsql.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* UserController
* Created on 2019年07月12日 10:02:36
**/
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    @UserLoginToken
    @GetMapping(value = "/all")
    public long test(){
        return userService.allCount();
    }

}