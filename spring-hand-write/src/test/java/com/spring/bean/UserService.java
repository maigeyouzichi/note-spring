package com.spring.bean;

import com.spring.bean.factory.BeanClassLoaderAware;
import com.spring.bean.factory.BeanFactory;
import com.spring.bean.factory.BeanFactoryAware;
import com.spring.bean.factory.BeanNameAware;
import com.spring.bean.factory.DisposableBean;
import com.spring.bean.factory.InitializingBean;
import com.spring.context.ApplicationContext;
import com.spring.context.ApplicationContextAware;

/**
 * @author lihao on 2023/1/16
 */
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware, IUserService {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private String name;
    private String uId;
    private String company;
    private String location;
    private UserDao userDao;
    private IUserDao iUserDao;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + name + "token:" + token);
    }

    @Override
    public String register(String userName) {
        return "注册用户：" + userName + " success！";
    }

    public void queryUserInfoByDao() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId)+", 公司："+company+", 地点"+location);
    }

    public String queryUserInfoByIUserDao() {
        return iUserDao.queryUserName(uId) + "," + company + "," + location;
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

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader is : " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name is: "+ name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
