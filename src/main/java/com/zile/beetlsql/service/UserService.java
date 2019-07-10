package com.zile.beetlsql.service;

import com.zile.beetlsql.mapper.UserDao;
import com.zile.beetlsql.model.User;
import com.zile.beetlsql.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by zileShi on 2019/7/1 0001.
 **/
@Service
public class UserService extends BaseServiceImpl<User> {

    @Autowired
    private UserDao userDao;

}
