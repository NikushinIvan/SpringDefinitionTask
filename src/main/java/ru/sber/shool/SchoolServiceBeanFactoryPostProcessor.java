package ru.sber.shool;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SchoolServiceBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private ProcessorCandidatesSupplier processorCandidatesSupplier;
    private SchoolBeanDefinitionGenerate schoolBeanDefinitionGenerate;

    public SchoolServiceBeanFactoryPostProcessor() {
    }

    public SchoolServiceBeanFactoryPostProcessor(ProcessorCandidatesSupplier processorCandidatesSupplier,
                                                 SchoolBeanDefinitionGenerate schoolBeanDefinitionGenerate) {
        this.processorCandidatesSupplier = processorCandidatesSupplier;
        this.schoolBeanDefinitionGenerate = schoolBeanDefinitionGenerate;
    }

    public void setProcessorCandidatesSupplier(ProcessorCandidatesSupplier processorCandidatesSupplier) {
        this.processorCandidatesSupplier = processorCandidatesSupplier;
    }

    public void setSchoolBeanDefinitionGenerate(SchoolBeanDefinitionGenerate schoolBeanDefinitionGenerate) {
        this.schoolBeanDefinitionGenerate = schoolBeanDefinitionGenerate;
    }

    /**
     * Регистрирует бин дефинишены
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        var defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        var supplier = defaultListableBeanFactory.getBean(ProcessorCandidatesSupplier.class);
        var candidates = supplier.getCandidates(defaultListableBeanFactory);
        var deanDefinitionGenerator = defaultListableBeanFactory.getBean(SchoolBeanDefinitionGenerate.class);
        for (String s : candidates.keySet()) {
            var beanDefinitionMap = deanDefinitionGenerator.generateBeanDefinition(s, candidates.get(s));
            for (String beanName : beanDefinitionMap.keySet()) {
                defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionMap.get(beanName));
            }
        }
    }
}
