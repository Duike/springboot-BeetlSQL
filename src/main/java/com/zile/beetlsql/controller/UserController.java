package com.zile.beetlsql.controller;

import com.zile.beetlsql.common.utils.JSONResult;
import com.zile.beetlsql.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* UserController
* Created on 2019年07月12日 10:02:36
**/
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户管理")
public class UserController{

    @Autowired
    private UserService userService;


    /**
     * http://localhost:8080/duike/swagger-ui.html#/
     * @return
     */
    @PostMapping(value = "/test")
    @ResponseBody
    @ApiOperation("查询用户条数")
    @ApiImplicitParams({
            //@ApiImplicitParam(name = "id", value = "指定id", defaultValue = ""),
            //@ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    }
    )
    public JSONResult test(){
        long result = userService.allCount();
        return JSONResult.success(result);
    }

}