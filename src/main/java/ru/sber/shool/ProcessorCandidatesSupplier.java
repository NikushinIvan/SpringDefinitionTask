package ru.sber.shool;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;
import ru.sber.shool.service.ServiceSchool;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProcessorCandidatesSupplier {

    /**
     * Метод находит компоненты с аннтацией {@link ru.sber.shool.service.ServiceSchool}
     * @param beanFactory
     * @return Map с BeanDefinition
     */
    public Map<String, BeanDefinition> getCandidates(DefaultListableBeanFactory beanFactory) {
        Map<String, BeanDefinition> result = new HashMap<>();

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> beanClass = Class.forName(beanClassName);
                if (beanClass.isAnnotationPresent(ServiceSchool.class)) {
                    result.put(beanClassName, beanDefinition);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
