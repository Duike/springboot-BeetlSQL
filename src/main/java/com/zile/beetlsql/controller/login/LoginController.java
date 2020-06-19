package com.zile.beetlsql.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.zile.beetlsql.common.annotations.UserLoginToken;
import com.zile.beetlsql.common.utils.*;
import com.zile.beetlsql.common.utils.redis.CommonCacheTime;
import com.zile.beetlsql.common.utils.redis.RedisUtils;
import com.zile.beetlsql.model.User;
import com.zile.beetlsql.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




/**
 * Created by zileShi on 2019/7/1 0001.
 **/
@RestController
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
    @ResponseBody
    public JSONObject login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        User userResult = userService.login(user);

        if (EmptyUtil.isEmpty(userResult)) {
            jsonObject.put("status", "fail");
            jsonObject.put("message", "登陆失败:用户名或密码错误");
            return jsonObject;
        } else {
            //创建token
            String token = TokenUtil.getToken(String.valueOf(userResult.getId()));
            //把新的token保存到redis中
            redisUtils.set(Constant.Redis.USERID + userResult.getId(), token, CommonCacheTime.TWO_HOUR);
            //把密码置空
            userResult.setPassword("");
            jsonObject.put("status", "success");
            jsonObject.put("message", "登录成功");
            jsonObject.put("user", userResult);
            jsonObject.put("token", token);
            return jsonObject;
        }
    }


    /**
     * 退出登录
     * @return
     */
    @PostMapping(value = "/loginOut")
    @ResponseBody
    public JSONObject loginOut(String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            redisUtils.del(Constant.Redis.USERID + userId);
        }catch (Exception e){
            jsonObject.put("status", "fail");
            jsonObject.put("message", "退出失败！");
        }
        jsonObject.put("status", "success");
        jsonObject.put("message", "退出成功！");
        return jsonObject;
    }



    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

}
