package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller     //需要进行页面跳转
@RequestMapping("/user")
public class UserController {

    /**
     * 实现用户模块页面跳转
     * url1: http://www.jt.com/user/login.html     页面:login.jsp
     * url2: http://www.jt.com/user/register.html  页面:register.jsp
     * 要求:实现通用页面跳转
     * restFul方式: 1.动态获取url中的参数,之后实现通用的跳转.
     */
    @RequestMapping("/{moduleName}")
    public String module(@PathVariable String moduleName){

        return moduleName;
    }

}
