package com.spring.bean;

/**
 * @author lihao on 2023/1/16
 */
public class UserService {

    private String name;

    private String uId;

    private UserDao userDao;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + name);
    }

    public void queryUserInfoByDao() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

    @Override
    public String toString() {
        return "UserService{" + "name='" + name + '\'' + ", uId='" + uId + '\'' + ", userDao=" + userDao + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
