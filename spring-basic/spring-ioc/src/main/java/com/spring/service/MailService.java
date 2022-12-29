package com.spring.service;

import com.spring.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author lihao
 */
@Service
public class MailService {

    public boolean sendRegisterMail(User user) {
        System.out.println(String.format("%s 正在注册码神之路，您可以点击以下的链接完成注册 %s,如果不是你注册的，请忽略本邮件", user.getNickname(), "http://www.mszlu.com"));
        return true;
    }
}