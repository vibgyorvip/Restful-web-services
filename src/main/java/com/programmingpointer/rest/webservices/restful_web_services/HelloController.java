package com.programmingpointer.rest.webservices.restful_web_services;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {

    private MessageSource messageSource;

    public HelloController(MessageSource messageSource){
        this.messageSource=messageSource;
    }

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


    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
