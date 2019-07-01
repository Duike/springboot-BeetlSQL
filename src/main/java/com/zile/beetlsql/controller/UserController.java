package com.zile.beetlsql.controller;

import com.zile.beetlsql.model.User;
import com.zile.beetlsql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zileShi on 2019/7/1 0001.
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAll")
    public String getAll(){

        return null;
    }

}
