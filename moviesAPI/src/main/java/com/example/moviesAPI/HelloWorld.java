package com.example.moviesAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/")
    public String sayHello(){
        return "Hi, Welcome to Movie Review application by Rubal Goyal";
    }
}
