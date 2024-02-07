package ru.sber.shool.service.identity;


import org.springframework.stereotype.Component;
import ru.sber.shool.service.ServiceSchool;

@Component
@ServiceSchool(serviceName = "School1", host = "google.com")
public class EngSchoolService {
}
