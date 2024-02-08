Задание

Написать SchoolServiceBeanFactoryPostProcessor который будет регистрировать бин дефинишены созданные генератором. Для этого необходимо найти все бины с аннотацией ServiceSchool и передать параметры в фабрики. Для генерации бин дефинишинов использовать BeanDefinitionBuilder. На выходе получить Service c beanName = ServiceSchool.nameSerive, с логгером Logger.serviceName = ServiceSchool.nameService, Monitoring.host = ServiceSchool.host.
