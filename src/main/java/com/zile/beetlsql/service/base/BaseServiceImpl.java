package com.zile.beetlsql.service.base;

import com.zile.beetlsql.common.utils.EmptyUtil;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


/**
 * 通用serviceImpl
 * Created by zileShi on 2019/7/1 0001.
 **/
public abstract class BaseServiceImpl<T> {

    @Autowired
    private BaseMapper<T> baseMapper;


    public BaseMapper getBaseMapper(){
        return this.baseMapper;
    }


    /**
     *
     * @return  返回一个SQLManager对象
     */
    public SQLManager getBaseMapperSQLManager(){
        return this.baseMapper.getSQLManager();
    }


    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity    对象实体
     * @return          成功则返回该数据的id，失败则返回0
     */
    @Transactional //本地事务注解
    public int insert(T entity){
        KeyHolder keyHolder = getBaseMapper().insertReturnKey(entity);
        Integer id =  keyHolder.getInt();
        if (EmptyUtil.isNotEmpty(id)){
            return id;
        }else {
            return 0;
        }
    }

    /**
     * 根据主键更新对象，只有不为null的属性参与更新
     *
     * @param entity    对象实体
     * @return          返回0为失败，返回1为成功
     */
    @Transactional
    public int updateTemplate(T entity){
        int result = getBaseMapper().updateTemplateById(entity);

        if (result != 0) {
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 根据主键更新对象，所以属性都参与更新。也可以使用主键ColumnIgnore来控制更新的时候忽略此字段
     *
     * @param entity    对象实体
     * @return          返回0为失败，返回1为成功
     */
    @Transactional
    public int update(T entity){
        int result = getBaseMapper().updateById(entity);
        if (result != 0) {
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 根据主键删除对象，如果对象是复合主键，传入对象本身即可
     * (物理删除，不建议使用)
     *
     * @param key   该对象对应数据库的主键，一般都是id
     * @return      返回0为失败，返回1为成功
     */
    @Transactional
    public int delete(Object key){
        int result = getBaseMapper().deleteById(key);
        if (result != 0) {
            return 1;
        }else {
            return 0;
        }
    }


    /**
     * 根据主键获取对象，如果对象不存在，返回null
     *
     * @param key   该对象对应数据库的主键，一般都是id
     * @return      返回null为未查询到结果，返回对象为查询结果，返回多个结果则抛出异常
     */
    public T single(Object key){
        return (T) getBaseMapper().single(key);
    }


    /**
     * 根据字段的信息判断该数据是否存在
     * (例如:用于新建时的name字段不重复等)
     *
     * @param entity    实体对象
     * @return          返回false为不存在，返回true为存在
     */
    public boolean judgeFieldUnique(T entity){
        boolean result = false;
        if (EmptyUtil.isNotEmpty(getBaseMapper().templateOne(entity))){
            result = true;
        }
        return result;
    }


    /**
     * 返回实体在数据库里的总数
     *
     * @return  返回总数
     */
    public long allCount(){
        return getBaseMapper().allCount();
    }

    /**
     * 符合模板得个数
     *
     * @param entity    实体对象
     * @return          返回符合模板的个数
     */
    public long templateCount(T entity){
        return getBaseMapper().templateCount(entity);
    }

    /**
     * 模板查询，返回符合模板得一条结果
     * （精确查询）
     *
     * @param entity    实体对象
     * @return
     */
    public T templateOne(T entity){
        return (T) getBaseMapper().templateOne(entity);
    }

    /**
     * 模板查询，返回符合模板得所有结果。beetlsql将取出非null值（日期类型排除在外），从数据库找出完全匹配的结果集
     * (精确查询)
     *
     * @param entity    实体对象
     * @param start     返回记录行的偏移量(初始记录行的偏移量是 1(而不是 0))
     * @param size      记录行的最大数目
     *
     * @return          返回对象列表为查询结果
     */
   public List<T> findTemplateList(T entity,int start,int size){
        return getBaseMapper().template(entity,start,size);
   }

    /**
     * 模板查询，返回符合模板得所有结果。beetlsql将取出非null值（日期类型排除在外），从数据库找出完全匹配的结果集
     * (精确查询)
     *
     * @param entity    实体对象
     *
     * @return          返回对象列表为查询结果
     */
    public List<T> findTemplateList(T entity){
        return getBaseMapper().template(entity);
    }



}
