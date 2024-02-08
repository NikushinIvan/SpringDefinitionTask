package ru.sber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import ru.sber.shool.service.Service;

@Configuration
public class InheritanceDemo {

	public static void main(String... args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext("ru.sber");

		Service school1 = (Service) applicationContext.getBean("School1");
		Service school2 = (Service) applicationContext.getBean("School2");


		school1.getLoggerSchool().sendStartServiceMassage();
		school1.getMonitoringSchool().sendMetrics();

		school2.getLoggerSchool().sendStartServiceMassage();
		school2.getMonitoringSchool().sendMetrics();
	}
}
