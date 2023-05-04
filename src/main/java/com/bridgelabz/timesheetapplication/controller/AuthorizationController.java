package com.bridgelabz.timesheetapplication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
    /*
    Authorization microservice: This microservice manages user authentication and authorization. It can have the following APIs:
    POST /login: User login
    POST /logout: User logout
    */
    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password){
        return "";
    }
}
