package com.spring.bean;

import com.spring.bean.factory.annotation.Autowired;
import com.spring.bean.factory.annotation.Value;
import com.spring.stereotype.Component;
import java.util.Random;

/**
 * @author lihao on 2023/2/18
 */
@Component("userService3")
public class UserService3 implements IUserService3{

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao3 userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDao3 getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao3 userDao) {
        this.userDao = userDao;
    }

}
