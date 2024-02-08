package ru.sber.shool.service;

import org.springframework.beans.factory.annotation.Autowired;

public class Service {

    private Logger loggerSchool;
    private Monitoring monitoringSchool;

    @Autowired
    public Service(Logger loggerSchool, Monitoring monitoringSchool) {
        this.loggerSchool = loggerSchool;
        this.monitoringSchool = monitoringSchool;
    }

    public Logger getLoggerSchool() {
        return loggerSchool;
    }

    public Monitoring getMonitoringSchool() {
        return monitoringSchool;
    }
}
