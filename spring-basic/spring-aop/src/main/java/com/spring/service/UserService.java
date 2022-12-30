package com.spring.service;

import com.spring.annotation.MsMetric;
import com.spring.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lihao
 */
@Component
public class UserService {

    private List<User> users = new ArrayList<>(List.of(
            new User(1L, "aaa","aaa@mszlu.com", "123456" ),
            new User(2L, "bbb","bbb@mszlu.com", "123456" ),
            new User(3L, "ccc","ccc@mszlu.com", "123456")));

    @Autowired
    private MailService mailService;

    /**
     * 通过setter方法注入 MailService
     */
    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @MsMetric
    public void registerUser(String mail,String password,String nickname){

        users.forEach(user -> {
            if(user.getMail().equalsIgnoreCase(mail)){
                throw new RuntimeException("已经注册");
            }
        });
        //输出null
        System.out.println(mailService.user);
        //输出null
        System.out.println(mailService.getFinalUser());
        //正常输出
        System.out.println(mailService.getUser());
        User user = new User(null,mail,password,nickname);
        mailService.sendRegisterMail(user);
    }
}