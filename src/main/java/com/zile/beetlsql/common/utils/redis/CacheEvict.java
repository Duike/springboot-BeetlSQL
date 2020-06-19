package com.zile.beetlsql.common.utils.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis缓存自定义注解清除
 * Created by jisongZhou on 2017/12/21.
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CacheEvict {

    public enum KeyMode {
        DEFAULT,    //只有加了@CacheKey的参数,才获取key后缀,默认情况进行缓存删除操作
        //以下结构为（参数类型_操作类型_返回值类型）
        //BASIC代表基本类型参数,加入key后缀中,如:String,Integer,Long,Short,Boolean
        //OBJECT代表对象类型参数，选择特定字段加入key后缀中，如：User对象选中id字段
        //操作类型分为INSERT(新增)、UPDATE(更新)、DELETE(删除)
        BASIC_INSERT,
        BASIC_UPDATE,
        BASIC_DELETE,
        OBJECT_INSERT_BASIC,
        OBJECT_INSERT_OBJECT,
        OBJECT_UPDATE_BASIC,
        OBJECT_UPDATE_OBJECT,
        OBJECT_DELETE;
    }

    public String type() default "";    //业务类型

    public String[] objectKeyPrefix() default {}; //对象缓存键值前缀集合

    public String[] listKeyPrefix() default {}; //列表缓存键值前缀集合

    public KeyMode keyMode() default KeyMode.DEFAULT;    //key的后缀模式
}
