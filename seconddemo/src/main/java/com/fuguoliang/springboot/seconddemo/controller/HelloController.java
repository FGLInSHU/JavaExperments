package com.fuguoliang.springboot.seconddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author FGL_S
 */
@Controller("/fgl")
public class HelloController {
    @RequestMapping("/Hello")
    @ResponseBody
    public String hello() {
        System.out.println("Rcv request");
        return "hello";
    }
}
