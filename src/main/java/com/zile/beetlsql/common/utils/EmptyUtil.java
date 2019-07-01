package com.zile.beetlsql.common.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by zileShi on 2019/7/1 0001.
 **/
public class EmptyUtil {

    /**
     * 判断对象为空
     *
     * @param obj
     *            对象名
     * @return 是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            if (((String) obj).equals("") || ((String) obj).trim().equals("null")){
                return true;
            }
        }
        return false;
    }


    /**
     * 循环遍历数组，判断对象为空
     *
     * @param array
     *            数组
     * @return 是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmptyByArray(String[] array)
    {
        for (String obj : array) {
            if ((obj instanceof String))
            {
                if (((String) obj).equals("") || ((String) obj).trim().equals("null")){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 判断对象不为空
     *
     * @param obj
     *            对象名
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }

    /*
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断赋值属性中是否包含null ""
     * @param t
     * @return
     */
    public static Object judgementAssignment(Object t){
        for ( Field f : t.getClass().getDeclaredFields() ) {
            f.setAccessible(true);
            try {
                //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                if (("null").equals(f.get(t)) || ("\"\"").equals(f.get(t))) {
                    f.set(t,null);
                    //System.out.println(f.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

}
