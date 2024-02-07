package ru.sber.shool.service.identity;


import org.springframework.stereotype.Component;
import ru.sber.shool.service.ServiceSchool;

@Component
@ServiceSchool(serviceName = "School2", host = "yandex.ru")
public class RuSchoolService {
}
