package com.luoqincai.controller;

import com.luoqincai.service.SayTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayTestController {
    @Autowired
    SayTestService sayTestService;

    @GetMapping(value = "/sayTest")
    public String sayTest()
    {
        return sayTestService.sayTest();
    }
}
