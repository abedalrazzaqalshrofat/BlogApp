package com.newagetechsoft.BlogApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.AbstractMap;
import java.util.Map;

@Controller
@RequestMapping("/home")
@ResponseBody
public class HelloWorldController {

    @GetMapping
    public Map.Entry<String,String> stringEntry(){
        return new AbstractMap.SimpleEntry<>("message","hello world");
    }

}
