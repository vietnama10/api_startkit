package com.startkit.api.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @GetMapping("/public/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/private/hello")
    public String sayPrivateHello() {
        return "Hello, Private World!";
    }
}
