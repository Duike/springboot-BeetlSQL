package com.zile.beetlsql.model;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-07-12
*/
@Table(name="test_demo.department")
public class Department   {
	
	/*
	id
	*/
	private Integer id ;
	/*
	名称
	*/
	private String name ;
	
	public Department() {
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
	* 名称
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**
	* 名称
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
	}
	

}
