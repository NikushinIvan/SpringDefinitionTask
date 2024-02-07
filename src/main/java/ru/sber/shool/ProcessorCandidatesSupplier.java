package ru.sber.shool;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcessorCandidatesSupplier {

    /**
     * Метод находит компоненты с аннтацией {@link ru.sber.shool.service.ServiceSchool}
     * @param beanFactory
     * @return Map с BeanDefinition
     */
    public Map<String, BeanDefinition> getCandidates(DefaultListableBeanFactory beanFactory) {
        return null;
    }
}
