package com.zile.beetlsql.service;

import com.zile.beetlsql.common.utils.AES;
import com.zile.beetlsql.common.utils.MD5Utils;
import com.zile.beetlsql.controller.login.LoginController;
import com.zile.beetlsql.mapper.UserDao;
import com.zile.beetlsql.model.User;
import com.zile.beetlsql.service.base.BaseServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
*
* UserService
* Created on 2019年07月12日 10:02:36
**/
@Service
public class UserService extends BaseServiceImpl<User> {
    private final static Log log = (Log) LogFactory.getLog(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    protected SQLManager sqlManager;

    public User login(User user){
        String username = user.getUsername().trim();
        String password = user.getPassword().trim();
        try {
            password = AES.aesDecrypt(user.getPassword(),AES.KEY);
            password = MD5Utils.Encrypt(password,true);
        } catch (Exception e) {
            log.error("AES解密异常"+e);
            e.printStackTrace();
        }


        Map<String,Object> map = new HashMap();
        map.put("username",username);
        map.put("password",password);

        User userResult = sqlManager.selectSingle("user.login", map, User.class);

        return userResult;
    }

}