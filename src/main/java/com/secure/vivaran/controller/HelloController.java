package com.secure.vivaran.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/contacts")
    public String contact(){
        return "1234567890";
    }
}
