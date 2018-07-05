package com.sptfy.web.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SecurityTestController {

    @RequestMapping("/home")
    public String goHome(){

        return "security-test";
    }

    @RequestMapping("/redirected")
    public String goRedir(){

        return "redir";
    }
}
