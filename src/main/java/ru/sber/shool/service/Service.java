package ru.sber.shool.service;

public class Service {

    private Logger logger;
    private Monitoring monitoring;

    public Service(Logger logger, Monitoring monitoring) {
        this.logger = logger;
        this.monitoring = monitoring;
    }

    public Logger getLogger() {
        return logger;
    }

    public Monitoring getMonitoring() {
        return monitoring;
    }
}
