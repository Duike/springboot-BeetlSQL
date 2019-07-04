package com.zile.beetlsql.controller;

import com.zile.beetlsql.controller.base.BaseController;
import com.zile.beetlsql.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zileShi on 2019/7/4 0004.
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<User> {
}
