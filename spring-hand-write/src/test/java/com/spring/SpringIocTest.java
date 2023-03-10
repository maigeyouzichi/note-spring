package com.spring;

import cn.hutool.core.io.IoUtil;
import com.spring.aop.AdvisedSupport;
import com.spring.aop.ClassFilter;
import com.spring.aop.MethodMatcher;
import com.spring.aop.TargetSource;
import com.spring.aop.aspectj.AspectJExpressionPointcut;
import com.spring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.spring.aop.framework.Cglib2AopProxy;
import com.spring.aop.framework.JdkDynamicAopProxy;
import com.spring.aop.framework.ProxyFactory;
import com.spring.aop.framework.ReflectiveMethodInvocation;
import com.spring.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.spring.bean.BeansException;
import com.spring.bean.IUserService;
import com.spring.bean.IUserService2;
import com.spring.bean.IUserService3;
import com.spring.bean.PropertyValue;
import com.spring.bean.PropertyValues;
import com.spring.bean.UserDao;
import com.spring.bean.UserService;
import com.spring.bean.UserServiceBeforeAdvice;
import com.spring.bean.UserServiceInterceptor;
import com.spring.bean.factory.config.BeanDefinition;
import com.spring.bean.factory.config.BeanPostProcessor;
import com.spring.bean.factory.config.BeanReference;
import com.spring.bean.factory.support.DefaultListableBeanFactory;
import com.spring.bean.factory.xml.XmlBeanDefinitionReader;
import com.spring.common.MyBeanFactoryPostProcessor;
import com.spring.common.MyBeanPostProcessor;
import com.spring.context.support.ClassPathXmlApplicationContext;
import com.spring.converter.StringToIntegerConverter;
import com.spring.core.convert.converter.Converter;
import com.spring.core.convert.support.StringToNumberConverterFactory;
import com.spring.core.io.DefaultResourceLoader;
import com.spring.core.io.Resource;
import com.spring.cyclic.Husband;
import com.spring.cyclic.Wife;
import com.spring.event.CustomEvent;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author lihao on 2023/1/17
 */
public class SpringIocTest {

    private DefaultResourceLoader resourceLoader;
    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
        // ????????????
        IUserService userService = new UserService();
        // ??????????????????
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.spring.bean.IUserService.*(..))"));
    }

    /**
     * ??????????????????????????????
     */
    @Test
    public void test_BeanFactory(){
        // 1.????????? BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao ??????
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService ????????????[uId???userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService ??????bean
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        // 5. UserService ??????bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfoByDao();

        UserService userService2 = (UserService) beanFactory.getBean("userService");
        userService2.queryUserInfoByDao();
    }

    /**
     * ??????cglib??????????????????
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
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"?????????"});
        System.out.println(obj);
        System.out.println(obj.getClass().getName());//?????????????????????
    }

    /**
     * ??????Class???????????????????????????
     */
    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();//class?????????????????????????????????
        System.out.println(userService);
    }

    /**
     * ??????Class?????????????????????,????????????????????????????????????
     */
    @Test
    public void test_constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("?????????");//??????????????????????????????
        System.out.println(userService);
        System.out.println(userService.getClass().getName());
    }

    /**
     * ??????Class?????????????????????,Class?????????????????????????????????????????????????????????????????????????????????
     */
    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = null;
        //?????????????????????,??????????????????????????????????????????, ???????????????????????????????????????????????????????????????????????????,?????????????????????
        for (Constructor<?> ctor : declaredConstructors) {
            if (ctor.getParameterTypes().length == 1) {
                constructor = ctor;
                break;
            }
        }
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("?????????");
        System.out.println(userService);
        System.out.println(userService.getClass().getName());
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        // ????????????????????????GitHub????????????????????????????????????Gitee??????????????????????????????????????????????????????OLpj9823dZ
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content.contains("OLpj9823dZ"));//????????????????????????xml
    }

    @Test
    public void test_xml() {
        // 1.????????? BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. ??????????????????&??????Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        // 3. ??????Bean??????????????????
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfoByDao();
    }

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.????????? BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. ??????????????????&??????Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition ???????????? & Bean???????????????????????? BeanDefinition ????????????
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean???????????????????????? Bean ????????????
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. ??????Bean??????????????????
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfoByDao();
    }

    @Test
    public void test_xml2() {
        // 1.????????? BeanFactory (?????????????????????????????????)
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 2. ??????Bean??????????????????
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfoByDao();
    }

    @Test
    public void test_xml3() {
        // 1.????????? BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2. ??????Bean??????????????????
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfoByDao();
        System.out.println("ApplicationContextAware: "+ userService.getApplicationContext());
    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close???")));
    }

    @Test
    public void test_prototype() {
        // 1.????????? BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. ??????Bean??????????????????
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. ?????? scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. ????????????????????????
        System.out.println(userService01 + " ?????????????????????" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    @Test
    public void test_factory_bean() {
        // 1.????????? BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        // 2. ??????????????????
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("???????????????" + userService.queryUserInfoByIUserDao());
    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "????????????"));
        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_proxy_method() {
        // ????????????(????????????????????????????????????)
        Object targetObj = new UserService("??????");
        // AOP ??????
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // ??????????????? -- ??????????????????????????????????????????,??????????????????????????????????????????
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.spring.bean.IUserService.*(..))");
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // ???????????????
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("?????? - Begin By AOP");
                            System.out.println("???????????????" + invocation.getMethod().getName());
                            System.out.println("???????????????" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("?????? - End\r\n");
                        }
                    };
                    // ????????????
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });
        proxy.queryUserInfo();
        System.out.println(proxy.register("ls"));
    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.spring.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic() {
        // ????????????
        IUserService userService = new UserService("zs");
        // ??????????????????
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.spring.bean.IUserService.*(..))"));

        // ????????????(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // ????????????
        proxy_jdk.queryUserInfo();
        // ????????????(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // ????????????
        System.out.println("???????????????" + proxy_cglib.register("??????"));
    }

    @Test
    public void test_proxy_class() {
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, (proxy, method, args) -> "??????????????????");
        userService.queryUserInfo();
        userService.register("hello");
    }

    @Test
    public void test_proxyFactory() {
        advisedSupport.setProxyTargetClass(false); // false/true???JDK???????????????CGlib????????????
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        proxy.queryUserInfo();
    }

    @Test
    public void test_beforeAdvice() {
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        proxy.queryUserInfo();
    }

    @Test
    public void test_advisor() {
        // ????????????
        IUserService userService = new UserService();
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.spring.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));
        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(userService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true); // false/true???JDK???????????????CGlib????????????
            IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
            proxy.queryUserInfo();
        }
    }

    @Test
    public void test_aop_() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        userService.queryUserInfo();
    }


    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService2 userService2 = applicationContext.getBean("userService2", IUserService2.class);
        System.out.println("???????????????" + userService2.queryUserInfo());
    }

    @Test
    public void test_property() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IUserService2 userService2 = applicationContext.getBean("userService2", IUserService2.class);
        System.out.println("???????????????" + userService2);
    }

    @Test
    public void test_beanPost(){

        BeanPostProcessor beanPostProcessor = new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return null;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return null;
            }
        };

        List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
        beanPostProcessors.add(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
        beanPostProcessors.remove(beanPostProcessor);

        System.out.println(beanPostProcessors.size());
    }

    @Test
    public void test_scan3() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring3.xml");
        IUserService3 userService3 = applicationContext.getBean("userService3", IUserService3.class);
        System.out.println("???????????????" + userService3.queryUserInfo());
    }

    @Test
    public void test_autoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_circular() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-cyclic.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("??????????????????" + husband.queryWife());
        System.out.println("??????????????????" + wife.queryHusband());
    }

    @Test
    public void test_convert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-convert.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("???????????????" + husband);
    }

    @Test
    public void test_StringToIntegerConverter() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("1234");
        System.out.println("???????????????" + num);
    }

    @Test
    public void test_StringToNumberConverterFactory() {
        StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

        Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
        System.out.println("???????????????" + stringToIntegerConverter.convert("1234"));

        Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
        System.out.println("???????????????" + stringToLongConverter.convert("1234"));
    }

}
