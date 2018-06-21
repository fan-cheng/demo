package com.demof.controller;

import com.demof.model.DemoModel;
import com.demof.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){
        DemoModel model = demoService.findDemo();
        System.out.print("名称："+model.getName()+",年龄："+model.getAge()+",手机号码："+model.getPhone()+"\n");

        return model;
    }
}
