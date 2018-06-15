package com.sptfy.web.app.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TestRestController {


    @GetMapping("/greeting")
    public String getGreeting(){

        return "Hello in rest controller";
    }
}
