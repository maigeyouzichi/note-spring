package com.spring.selector;

import com.spring.entity.User;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现ImportSelector配合@Import(MyImportSelector.class),仍然可以将没有注解修饰的User加载到IOC容器中
 * @author lihao
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{User.class.getName()};
    }
}