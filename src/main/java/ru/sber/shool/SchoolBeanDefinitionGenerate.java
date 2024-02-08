package ru.sber.shool;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.sber.shool.factory.LoggerBeanFactory;
import ru.sber.shool.factory.MonitoringBeanFactory;
import ru.sber.shool.factory.ServiceCompositeBeanDefinition;
import ru.sber.shool.service.Logger;
import ru.sber.shool.service.Monitoring;
import ru.sber.shool.service.Service;
import ru.sber.shool.service.ServiceSchool;

import java.util.HashMap;
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
        Map<String, BeanDefinition> result = new HashMap<>();

        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> beanClass = Class.forName(beanClassName);
            var annotation = beanClass.getAnnotation(ServiceSchool.class);
            String host = annotation.host();
            String annotationServiceName = annotation.serviceName();

            var serviceDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(Service.class)
                    .setFactoryMethodOnBean("create", FACTORY_BEAN)
                    .addConstructorArgValue(new LoggerBeanFactory().createLoggerService(annotationServiceName))
                    .addConstructorArgValue(new MonitoringBeanFactory().createMonitoringService(host))
                    .getBeanDefinition();

            result.put(annotationServiceName, serviceDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
