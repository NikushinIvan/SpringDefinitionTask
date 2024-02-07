package ru.sber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.sber.shool")
public class InheritanceDemo {

	public static void main(String... args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InheritanceDemo.class);
	}
}
