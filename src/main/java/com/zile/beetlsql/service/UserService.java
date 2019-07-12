package com.zile.beetlsql.service;

import com.zile.beetlsql.mapper.UserDao;
import com.zile.beetlsql.model.User;
import com.zile.beetlsql.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
* UserService
* Created on 2019年07月12日 10:02:36
**/
@Service
public class UserService extends BaseServiceImpl<User> {

    @Autowired
    private UserDao userDao;

}