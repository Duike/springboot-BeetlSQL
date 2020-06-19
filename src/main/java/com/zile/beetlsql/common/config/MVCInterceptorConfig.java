package com.zile.beetlsql.common.config;

import com.zile.beetlsql.common.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Created by zileShi on 2019/7/17 0017.
 **/
@Configuration
public class MVCInterceptorConfig implements WebMvcConfigurer {

   @Autowired
   private AuthenticationInterceptor authenticationInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求，除/login外，在MVCInterceptor类上作判断限制。
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/loginOut");
    }

}
