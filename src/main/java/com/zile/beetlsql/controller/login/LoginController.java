package com.zile.beetlsql.controller.login;

import com.zile.beetlsql.common.annotations.PassToken;
import com.zile.beetlsql.common.utils.*;
import com.zile.beetlsql.common.utils.redis.CommonCacheTime;
import com.zile.beetlsql.common.utils.redis.RedisUtils;
import com.zile.beetlsql.model.User;
import com.zile.beetlsql.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




/**
 * Created by zileShi on 2019/7/1 0001.
 **/
@RestController
@Api(tags = "登录")
public class LoginController {

    private final static Log log = (Log) LogFactory.getLog(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 登录
     *
     * @param user 实体对象
     * @return 返回验证信息
     */
    @PostMapping(value = "/login")
    @ApiOperation("登录")
    @ResponseBody
    public JSONResult login(@RequestBody User user) {
        User userResult = userService.login(user);

        if (EmptyUtil.isEmpty(userResult)) {
            return JSONResult.fail(Constant.Login.FAIL_PASSWORD);
        } else {
            //创建token
            String token = TokenUtil.getToken(String.valueOf(userResult.getId()));
            //把新的token保存到redis中
            redisUtils.set(Constant.Redis.USERID + userResult.getId(), token, CommonCacheTime.TWO_HOUR);
            //把密码置空
            userResult.setPassword("");
            //存放token
            userResult.setToken(token);
            return JSONResult.success(Constant.Login.SUCCESS_LOGIN,userResult);
        }
    }


    /**
     * 退出登录
     * @return
     */
    @PostMapping(value = "/loginOut")
    @ApiOperation("登出")
    @ResponseBody
    public JSONResult loginOut(String userId) {
        try {
            redisUtils.del(Constant.Redis.USERID + userId);
        }catch (Exception e){
            return JSONResult.fail(Constant.Login.FAIL_LOGIN_OUT);
        }
        return JSONResult.success(Constant.Login.SUCCESS_LOGIN_OUT);
    }



    @PassToken  //关闭token验证
    @GetMapping("/getMessage")
    @ApiOperation("测试增加@PassToken注解可以跳过token验证")
    public JSONResult getMessage() {
        return JSONResult.success("你已通过验证");
    }

}
