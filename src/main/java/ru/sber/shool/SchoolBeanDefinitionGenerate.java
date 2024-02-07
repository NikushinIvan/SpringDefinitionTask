package ru.sber.shool;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.sber.shool.factory.ServiceCompositeBeanDefinition;
import ru.sber.shool.service.Service;

import java.util.Map;

@Component
public class SchoolBeanDefinitionGenerate {

    private final static String FACTORY_BEAN = StringUtils.uncapitalize(ServiceCompositeBeanDefinition.class.getSimpleName());

    /**
     *
     * @param serviceName имя сервиса
     * @param beanDefinition класса с аннотацией {@link ru.sber.shool.service.ServiceSchool}
     * @return
     */
    public  Map<String, BeanDefinition>  generateBeanDefinition(String serviceName, BeanDefinition beanDefinition) {

        //return BeanDefinitionBuilder.genericBeanDefinition(Service.class).setFactoryMethodOnBean("create", FACTORY_BEAN).getBeanDefinition();
        return null;
    }
}
