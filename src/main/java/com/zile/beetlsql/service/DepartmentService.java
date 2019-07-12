package com.zile.beetlsql.service;

import com.zile.beetlsql.mapper.DepartmentDao;
import com.zile.beetlsql.model.Department;
import com.zile.beetlsql.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
* DepartmentService
* Created on 2019年07月12日 16:17:23
**/
@Service
public class DepartmentService extends BaseServiceImpl<Department> {

    @Autowired
    private DepartmentDao departmentDao;

}