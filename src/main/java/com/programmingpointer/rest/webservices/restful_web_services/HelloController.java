package com.programmingpointer.rest.webservices.restful_web_services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World !!";
    }

    @GetMapping("hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping("hello-world/{name}")
    public HelloWorldBean helloWorldBeanPath(@PathVariable String name){
        return  new HelloWorldBean(String.format("Hello Good Morning , %s" , name));
    }
}
