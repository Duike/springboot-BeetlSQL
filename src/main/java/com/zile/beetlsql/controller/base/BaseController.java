package com.zile.beetlsql.controller.base;


import com.zile.beetlsql.common.annotations.UserLoginToken;
import com.zile.beetlsql.common.utils.JSONResult;
import com.zile.beetlsql.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 通用Controller
 * Created by zileShi on 2019/7/3 0003.
 **/
public abstract class BaseController<T> {

    @Autowired
    private BaseServiceImpl<T> baseService;

    public BaseServiceImpl<T> getBaseService(){
        return this.baseService;
    }


    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity        对象实体
     * @return              返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @PostMapping(value = "/insert")
    @ResponseBody
    public JSONResult insert(@RequestBody T entity) throws Exception{
        Integer result = getBaseService().insert(entity);
        if (result != 0){
            return JSONResult.success("创建成功！",result);
        }else {
            return JSONResult.fail("创建失败！");
        }
    }

    /**
     * 根据主键更新对象，只有不为null的属性参与更新
     *
     * @param entity        对象实体
     * @return              返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @PostMapping(value = "/updateTemplate")
    @ResponseBody
    public JSONResult updateTemplate(@RequestBody T entity) throws Exception{
        Integer result = getBaseService().updateTemplate(entity);
        if (result != 0){
            return JSONResult.success("更新成功！");
        }else {
            return JSONResult.fail("更新失败！");
        }
    }

    /**
     * 根据主键更新对象，所以属性都参与更新。也可以使用主键ColumnIgnore来控制更新的时候忽略此字段
     *
     * @param entity        对象实体
     * @return              返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @PostMapping(value = "/update")
    @ResponseBody
    public JSONResult update(@RequestBody T entity) throws Exception{
        Integer result = getBaseService().update(entity);
        if (result != 0){
            return JSONResult.success("更新成功！");
        }else {
            return JSONResult.fail("更新失败！");
        }
    }

    /**
     *  根据主键删除对象，如果对象是复合主键，传入对象本身即可
     *  (物理删除，不建议使用)
     *
     * @param key        该对象对应数据库的主键，一般都是id
     * @return           返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @PostMapping(value = "/delete")
    @ResponseBody
    public JSONResult delete(Object key) throws Exception{
        Integer result = getBaseService().delete(key);
        if (result != 0){
            return JSONResult.success("删除成功！");
        }else {
            return JSONResult.fail("删除失败！");
        }
    }


    /**
     * 根据主键获取对象，如果对象不存在，返回null
     *
     * @param key   该对象对应数据库的主键，一般都是id
     * @return      返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @GetMapping(value = "/single")
    @ResponseBody
    public JSONResult single(Object key) throws Exception{
        T t = getBaseService().single(key);
        return JSONResult.success("查询成功！",t);
    }


    /**
     * 根据字段的信息判断该数据是否存在
     * (例如:用于新建时的name字段不重复等)
     *
     * @param entity           实体对象
     * @return                 返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @GetMapping(value = "/judgeFieldUnique")
    @ResponseBody
    public JSONResult judgeFieldUnique(@RequestBody T entity) throws Exception{
        boolean result = getBaseService().judgeFieldUnique(entity);
        String returnCode = "false";
        if (result){
            returnCode = "true";
        }
        return JSONResult.success(returnCode);
    }


    /**
     *  返回实体在数据库里的总数
     *
     * @return 返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @GetMapping(value = "/allCount")
    @ResponseBody
    public JSONResult allCount() throws Exception{
        long result = getBaseService().allCount();
        return JSONResult.success(String.valueOf(result));
    }


    /**
     * 符合模板得个数
     *
     * @param entity   实体对象
     * @return         返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @GetMapping(value = "/templateCount")
    @ResponseBody
    public JSONResult templateCount(@RequestBody T entity) throws Exception{
        long result = getBaseService().templateCount(entity);
        return JSONResult.success(String.valueOf(result));
    }

    /**
     * 模板查询，返回符合模板得一条结果
     * （精确查询）
     *
     * @param entity    实体对象
     * @return          返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @GetMapping(value = "/templateOne")
    @ResponseBody
    public JSONResult templateOne(@RequestBody T entity) throws Exception{
        T result = getBaseService().templateOne(entity);
        return JSONResult.success("查询成功",result);
    }


    /**
     * 模板查询，返回符合模板得所有结果。beetlsql将取出非null值（日期类型排除在外），从数据库找出完全匹配的结果集
     * (精确查询)
     *
     * @param entity        实体对象
     * @param start         返回记录行的偏移量(初始记录行的偏移量是 1(而不是 0))
     * @param size          记录行的最大数目
     * @return              返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @GetMapping(value = "/findTemplateListWithPage")
    @ResponseBody
    public JSONResult findTemplateListWithPage(@RequestBody T entity,int start,int size) throws Exception{
        List<T> resultList = getBaseService().findTemplateList(entity,start,size);
        return JSONResult.success("查询成功",resultList);
    }

    /**
     * 模板查询，返回符合模板得所有结果。beetlsql将取出非null值（日期类型排除在外），从数据库找出完全匹配的结果集
     * (精确查询)
     *
     * @param entity        实体对象
     * @return              返回信息
     * @throws Exception
     */
    @UserLoginToken  //token验证注解
    @GetMapping(value = "/findTemplateList")
    @ResponseBody
    public JSONResult findTemplateList(@RequestBody T entity) throws Exception{
        List<T> resultList = getBaseService().findTemplateList(entity);
        return JSONResult.success("查询成功",resultList);
    }


}
