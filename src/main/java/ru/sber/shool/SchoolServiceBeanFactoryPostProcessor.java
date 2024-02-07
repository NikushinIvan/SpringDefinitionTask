package ru.sber.shool;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class SchoolServiceBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private final ProcessorCandidatesSupplier processorCandidatesSupplier;
    private final SchoolBeanDefinitionGenerate schoolBeanDefinitionGenerate;

    public SchoolServiceBeanFactoryPostProcessor(ProcessorCandidatesSupplier processorCandidatesSupplier,
                                                 SchoolBeanDefinitionGenerate schoolBeanDefinitionGenerate) {
        this.processorCandidatesSupplier = processorCandidatesSupplier;
        this.schoolBeanDefinitionGenerate = schoolBeanDefinitionGenerate;
    }

    /**
     * Регистрирует бин дефинишены
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;

    }
}
