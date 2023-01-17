package com.spring.selector;

import com.spring.entity.User;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现ImportSelector配合@Import(MyImportSelector.class),仍然可以将没有注解修饰的User加载到IOC容器中
 * @author lihao
 */
public class MyImportSelector implements ImportSelector {

    /**
     * User.class.getName()可以获得User类的全限定类名:com.spring.entity.User,根据命名可以找到具体的类,实例化并加入到容器
     * @return String[]类名数组,返回的类名都会实例化并加入到容器中
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{User.class.getName()};
    }

}