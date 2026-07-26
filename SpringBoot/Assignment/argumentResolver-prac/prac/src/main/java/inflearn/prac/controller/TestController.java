package inflearn.prac.controller;

import inflearn.prac.config.custom.CustomPathVariable;
import inflearn.prac.config.custom.CustomRequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/request")
    public String requestParamDemo(@CustomRequestParam String name) {
        return "name=" + name;
    }

    @GetMapping("/path/{id}")
    public String pathVariableDemo(@CustomPathVariable Long id) {
        return "id=" + id;
    }
}