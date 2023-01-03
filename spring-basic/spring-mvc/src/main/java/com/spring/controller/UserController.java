package com.spring.controller;

import com.spring.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    //代码的意义为，获取到用户信息，将用户信息显示到index.jsp中
    @RequestMapping("getUser")
    public ModelAndView getUser(Long id){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setName("mvc");
        user.setId(1L);
        user.setEmail("email");
        user.setAge(20);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }
}