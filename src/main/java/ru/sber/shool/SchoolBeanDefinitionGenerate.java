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
    private final static String FACTORY_LOGGER_BEAN = StringUtils.uncapitalize(LoggerBeanFactory.class.getSimpleName());
    private final static String FACTORY_MONITORING_BEAN = StringUtils.uncapitalize(MonitoringBeanFactory.class.getSimpleName());


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

            result.put("monitoring" + annotationServiceName, configMonitoringDefinition(host));
            result.put("logger" + annotationServiceName, configLoggerDefinition(annotationServiceName));
            result.put(annotationServiceName, configServiceDefinition(annotationServiceName));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private AbstractBeanDefinition configMonitoringDefinition(String host) {
        return BeanDefinitionBuilder
                .genericBeanDefinition(Monitoring.class)
                .setFactoryMethodOnBean("createMonitoringService", FACTORY_MONITORING_BEAN)
                .addConstructorArgValue(host)
                .getBeanDefinition();
    }

    private AbstractBeanDefinition configLoggerDefinition(String serviceName) {
        return BeanDefinitionBuilder
                .genericBeanDefinition(Logger.class)
                .setFactoryMethodOnBean("createLoggerService", FACTORY_LOGGER_BEAN)
                .addConstructorArgValue(serviceName)
                .getBeanDefinition();
    }

    private AbstractBeanDefinition configServiceDefinition(String serviceName) {
        return BeanDefinitionBuilder
                .genericBeanDefinition(Service.class)
                .setFactoryMethodOnBean("create", FACTORY_BEAN)
                .addConstructorArgReference("logger" + serviceName)
                .addConstructorArgReference("monitoring" + serviceName)
                .getBeanDefinition();
    }
}
