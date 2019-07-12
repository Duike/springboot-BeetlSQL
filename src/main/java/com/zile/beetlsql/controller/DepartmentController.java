package com.zile.beetlsql.controller;

import com.zile.beetlsql.model.Department;
import com.zile.beetlsql.controller.base.BaseController;
import com.zile.beetlsql.service.DepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* DepartmentController
* Created on 2019年07月12日 16:17:23
**/
@RestController
@RequestMapping(value = "/department")
public class DepartmentController extends BaseController<Department> {

    @Autowired
    private DepartmentService departmentService;

}