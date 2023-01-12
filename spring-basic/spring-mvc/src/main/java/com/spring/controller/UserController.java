package com.spring.controller;

import com.alibaba.fastjson.JSON;
import com.spring.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 实在不想写JSP,很多方法返回了string,记得@ResponseBody注解修饰
 * @author lihao
 */
@Controller
public class UserController {

    /**
     * 测试返回ModelAndView
     * 浏览器请求: <a href="http://localhost:9090/getUser">URL</a>
     * @return ModelAndView
     */
    @RequestMapping("getUser")
    public ModelAndView getUser(){
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

    /**
     * 测试URL: <a href="http://localhost:9090/user/find?id=1&name=2">URL</a>
     * ?方式拼接方法参数名接口完成传参
     */
    @RequestMapping("/user/find")
    @ResponseBody
    public String find(Long id,String name){
       return "id: "+ id + "name: " + name;
    }

    /**
     * 第二种 写法，可以在参数前面加上@RequestParam("id")注解，注解中的value和url中的参数对应
     * 如果用了这种写法，参数的名称可以随意定义
     * 测试URL: <a href="http://localhost:9090/user/find2?id=1&name=2">...</a>
     */
    @RequestMapping("/user/find2")
    @ResponseBody
    public String find2(@RequestParam("id") Long id, @RequestParam("name") String name){
        return "id: "+ id + "name: " + name;
    }

    /**
     * 如果使用实体类接收，不能使用@RequestParam注解
     * 测试URL: <a href="http://localhost:9090/user/find3?id=1&name=2&age=1&email=222">URL</a>
     * ?方式拼接参数类的字段完成传参
     */
    @RequestMapping("/user/find3")
    @ResponseBody
    public String find3(User user){
        return JSON.toJSONString(user);
    }

    /**
     * 测试参数非必须且提供默认值
     * <a href="http://localhost:9090/user/find4?name=2">URL</a>
     */
    @RequestMapping("/user/find4")
    @ResponseBody
    public String find4(@RequestParam(value = "id",required = false,defaultValue = "2") Long id, @RequestParam("name") String name){
        return "id: "+ id + "name: " + name;
    }
}