package ru.sber.shool.factory;

import org.springframework.stereotype.Component;
import ru.sber.shool.service.Logger;
import ru.sber.shool.service.Monitoring;
import ru.sber.shool.service.Service;

@Component
public class ServiceCompositeBeanDefinition {


    public Service create(Logger loggerSchool, Monitoring monitoringSchool) {
        return new Service(loggerSchool, monitoringSchool);
    }
}
