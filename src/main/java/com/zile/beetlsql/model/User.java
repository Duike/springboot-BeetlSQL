package com.zile.beetlsql.model;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-07-12
*/
@Table(name="test_demo.user")
public class User   {
	
	/*
	id
	*/
	private Integer id ;
	/*
	年龄
	*/
	private Integer age ;
	/*
	1 表示正常显示，0 表示删除
	*/
	private Integer isActive ;
	/*
	用户角色
	*/
	private Integer roleId ;
	/*
	姓名
	*/
	private String name ;
	/*
	用户密码
	*/
	private String password ;
	/*
	用户名称
	*/
	private String username ;
	/*
	创建时间
	*/
	private Date createDate ;

	private String token;
	
	public User() {
	}
	
	/**
	* id
	*@return 
	*/
	public Integer getId(){
		return  id;
	}
	/**
	* id
	*@param  id
	*/
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**
	* 年龄
	*@return 
	*/
	public Integer getAge(){
		return  age;
	}
	/**
	* 年龄
	*@param  age
	*/
	public void setAge(Integer age ){
		this.age = age;
	}
	
	/**
	* 1 表示正常显示，0 表示删除
	*@return 
	*/
	public Integer getIsActive(){
		return  isActive;
	}
	/**
	* 1 表示正常显示，0 表示删除
	*@param  isActive
	*/
	public void setIsActive(Integer isActive ){
		this.isActive = isActive;
	}
	
	/**
	* 用户角色
	*@return 
	*/
	public Integer getRoleId(){
		return  roleId;
	}
	/**
	* 用户角色
	*@param  roleId
	*/
	public void setRoleId(Integer roleId ){
		this.roleId = roleId;
	}
	
	/**
	* 姓名
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**
	* 姓名
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
	}
	
	/**
	* 用户密码
	*@return 
	*/
	public String getPassword(){
		return  password;
	}
	/**
	* 用户密码
	*@param  password
	*/
	public void setPassword(String password ){
		this.password = password;
	}
	
	/**
	* 用户名称
	*@return 
	*/
	public String getUsername(){
		return  username;
	}
	/**
	* 用户名称
	*@param  username
	*/
	public void setUsername(String username ){
		this.username = username;
	}
	
	/**
	* 创建时间
	*@return 
	*/
	public Date getCreateDate(){
		return  createDate;
	}
	/**
	* 创建时间
	*@param  createDate
	*/
	public void setCreateDate(Date createDate ){
		this.createDate = createDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
