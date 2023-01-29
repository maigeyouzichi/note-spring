package com.spring.bean;

import com.spring.bean.factory.DisposableBean;
import com.spring.bean.factory.InitializingBean;

/**
 * @author lihao on 2023/1/16
 */
public class UserService implements InitializingBean, DisposableBean {

    private String name;
    private String uId;
    private String company;
    private String location;
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
        System.out.println("查询用户信息：" + userDao.queryUserName(uId)+", 公司："+company+", 地点"+location);
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }
}
