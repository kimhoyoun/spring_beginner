package springboot.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Boot ~~";
    }
}
