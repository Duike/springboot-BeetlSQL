package com.zile.beetlsql.common.exception;

import com.zile.beetlsql.common.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理机制
 * Created by zileShi on 2019/7/3 0003.
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONResult handException(HttpServletRequest req, Exception e) throws Exception {
        logger.error("报错信息:" + e.getMessage(), e);
        return JSONResult.exception(e.getMessage());
    }

}
