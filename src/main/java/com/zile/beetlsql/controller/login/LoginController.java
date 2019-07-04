package com.zile.beetlsql.controller.login;

import com.zile.beetlsql.common.annotations.UserLoginToken;
import com.zile.beetlsql.common.utils.EmptyUtil;
import com.zile.beetlsql.common.utils.JSONResult;
import com.zile.beetlsql.common.utils.TokenUtil;
import com.zile.beetlsql.model.User;
import com.zile.beetlsql.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * Created by zileShi on 2019/7/1 0001.
 **/
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


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
        user.setIsActive(1);
        User userResult = userService.templateOne(user);

        if (EmptyUtil.isEmpty(userResult)) {
            jsonObject.put("status", "fail");
            jsonObject.put("message", "登陆失败:用户名或密码错误");
            return jsonObject;
        } else {
            //token生效开始时间
            Date nowDate = new Date();
            //token生效结束时间
            //设置2小时后失效
            Date clearTokenDate = TokenUtil.getAfterDate(nowDate, 0, 0, 0, 2, 0, 0);
            //创建token
            String token = TokenUtil.getToken(String.valueOf(userResult.getId()), nowDate, clearTokenDate);
            //把新的token保存到redis中
            stringRedisTemplate.opsForValue().set("userId_" + userResult.getId(), token);
            //把密码置空
            userResult.setPassword("");
            jsonObject.put("status", "success");
            jsonObject.put("message", "登录成功");
            jsonObject.put("user", userResult);
            jsonObject.put("token", token);
            return jsonObject;
        }
    }


    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

}
