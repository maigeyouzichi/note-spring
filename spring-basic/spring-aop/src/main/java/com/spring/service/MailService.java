package com.spring.service;

import com.spring.annotation.MsMetric;
import com.spring.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author lihao
 */
@Service
public class MailService {

    //带有默认值,注入ioc容器的时候会根据默认值赋值
    public User user = new User(1L, "zhangsan", "maigeyouzichi@gmain.com", "123");

    public User getUser(){
        return user;
    }

    public final User getFinalUser(){
        return user;
    }

    /**
     * 添加@MsMetric注解之后会被代理,代理之后的对象,直接访问属性或者通过final方法访问属性数据,都拿不到结果
     * 如果没有添加@MsMetric注解,也就没有被代理,就不会有上述问题
     */
    @MsMetric
    public boolean sendRegisterMail(User user) {
        System.out.println(String.format("%s 正在注册码神之路，您可以点击以下的链接完成注册 %s,如果不是你注册的，请忽略本邮件", user.getNickname(), "http://www.mszlu.com"));
        return true;
    }
}