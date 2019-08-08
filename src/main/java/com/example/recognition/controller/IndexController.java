package com.example.recognition.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/6/25.
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/test")
    public String index(){
        return "hello world!";
    }


}
