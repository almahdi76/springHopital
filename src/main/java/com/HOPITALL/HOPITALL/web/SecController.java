package com.HOPITALL.HOPITALL.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecController {

    @GetMapping("/403")
    public String notAuthorzed(){
        return "403";
    }

    
}
