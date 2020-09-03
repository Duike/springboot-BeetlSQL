package com.zile.beetlsql.controller;

import com.zile.beetlsql.service.DepartmentService;
import com.zile.beetlsql.websocket.WebSocket;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* DepartmentController
* Created on 2019年07月12日 16:17:23
**/
@RestController
@RequestMapping(value = "/department")
public class DepartmentController{

//    @Autowired
//    private DepartmentService departmentService;
    @Autowired
    private WebSocket webSocket;


    @PostMapping("/sendAllWebSocket")
    public String test() {
        String text="你们好！这是websocket群体发送！";
        webSocket.sendAllMessage(text);
        return text;
    }

    @GetMapping("/sendOneWebSocket/{userName}")
    public String sendOneWebSocket(@PathVariable("userName") String userName) {
        String text=userName+" 你好！ 这是websocket单人发送！";
        webSocket.sendOneMessage(userName,text);
        return text;
    }


}
