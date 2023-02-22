package com.spring.converter;

import com.spring.bean.factory.FactoryBean;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lihao
 */
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {

	@Override
	public Set<?> getObject() {
		HashSet<Object> converters = new HashSet<>();
		StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
		converters.add(stringToLocalDateConverter);
		return converters;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
