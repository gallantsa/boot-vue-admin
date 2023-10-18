package com.example.bootvueadmin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// SpringMVC
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public String login(@RequestBody String userStr) { // 把前台发过来的 json字符串转成Java对象
        System.out.println(userStr);
        return "SUCCESS";
    }
}
