package com.spring.context;

import com.spring.bean.factory.ListableBeanFactory;

/**
 * 上下文接口
 * 个人理解: 上下文在创建的时候刷新了容器,执行了用户自定义的扩展逻辑后将单例bean放进容器中,对bean本身的创建和获取
 * 最终还是调用了DefaultListableBeanFactory体系中的实现,上下文本质上是对BeanFactory那一套实现的增强.
 */
public interface ApplicationContext extends ListableBeanFactory {
}
