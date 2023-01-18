package com.spring.bean;

/**
 * @author lihao on 2023/1/16
 */
public class UserService {

    private String name;

    public UserService() {};

    public UserService(String name) {
        this.name = name;
    }
    public void queryUserInfo(){
        System.out.println("查询用户信息: "+name);
    }

    @Override
    public String toString() {
        return "UserService{" +
            "name='" + name + '\'' +
            '}';
    }
}
