package com.spring.bean;

import com.spring.bean.factory.config.BeanDefinition;
import com.spring.bean.factory.support.DefaultListableBeanFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

/**
 * @author lihao on 2023/1/17
 */
public class SpringIocTest {

    /**
     * 测试容器内实例化对象
     */
    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService","Jack");
        userService.queryUserInfo();
        System.out.println(userService.getClass().getName());

        UserService userService2 = (UserService) beanFactory.getBean("userService");
        userService2.queryUserInfo();
        System.out.println(userService.getClass().getName());

        // 4.第二次获取 bean from Singleton
//        UserService userService_singleton = (UserService) beanFactory.getSingleton("userService");
//        userService_singleton.queryUserInfo();
    }

    /**
     * 测试cglib生成代理对象
     */
    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"周杰伦"});
        System.out.println(obj);
        System.out.println(obj.getClass().getName());//生成的代理对象
    }

    /**
     * 测试Class对象直接实例化对象
     */
    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();//class对象默认调用无参构造器
        System.out.println(userService);
    }

    /**
     * 测试Class对象获取构造器,构造器根据参数实例化对象
     */
    @Test
    public void test_constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("周杰伦");//构造器调用实例化方法
        System.out.println(userService);
        System.out.println(userService.getClass().getName());
    }

    /**
     * 测试Class对象获取构造器,Class对象根据构造器获取的参数类型集合从而获取具体类型构造器
     */
    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = null;
        //这里简化了操作,根据构造器参数个数选择构造器, 再根据当前构造器的参数类型集合确定具体类型的构造器,进而实例化对象
        for (Constructor<?> ctor : declaredConstructors) {
            if (ctor.getParameterTypes().length == 1) {
                constructor = ctor;
                break;
            }
        }
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("周杰伦");
        System.out.println(userService);
        System.out.println(userService.getClass().getName());
    }

}
