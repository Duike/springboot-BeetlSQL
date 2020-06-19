package com.zile.beetlsql.common.utils.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis缓存自定义键值
 * Created by jisongZhou on 2017/12/21.
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface CacheKey {

    public String field() default "";    //键值包含字段名

    public String[] fields() default {};    //键值包含的字段名集合（建议不要同时使用两个属性）

}
