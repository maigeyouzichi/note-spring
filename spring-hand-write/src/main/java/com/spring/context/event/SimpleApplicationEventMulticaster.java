package com.spring.context.event;

import com.spring.bean.factory.BeanFactory;
import com.spring.context.ApplicationEvent;
import com.spring.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    /**
     * 这里的发布并不像消息中间件,一个发布一个监听,这里的发布就是遍历监听者然后执行监听方法
     * @param event the event to multicast
     */
    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}