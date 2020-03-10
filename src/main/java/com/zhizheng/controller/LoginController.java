package com.zhizheng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class LoginController {

    @RequestMapping("/")
    public ModelAndView goEditPage(){
        return new ModelAndView("partymemberinfoedit");
    }
    /*@RequestMapping(value="/login",method = {RequestMethod.GET, RequestMethod.POST})
    public Object login(String username,String password){
        UsernamePasswordToken passwordToken = new UsernamePasswordToken(username,
                password, true);
        Subject subject = SecurityUtils.getSubject();
        subject.login(passwordToken);
        ModelAndView modelAndView = new ModelAndView("/test.html");
        return true;
    }*/

}
