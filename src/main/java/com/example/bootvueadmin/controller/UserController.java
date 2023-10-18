package com.example.bootvueadmin.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.bootvueadmin.entity.User;
import com.example.bootvueadmin.mapper.UserMapper;
import com.example.bootvueadmin.utils.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// SpringMVC
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public String login(@RequestBody String userStr) { // 把前台发过来的 json字符串转成Java对象
        // {"username":"admin","password":"admin"}
        System.out.println(userStr);
        JSONObject userObj = JSONUtil.parseObj(userStr);

        // 把接口参数转换成 java bean
        // User user = JSONUtil.toBean(userStr, User.class);

        // 直接从json对象获取属性
        String username = userObj.getStr("username");
        String password = userObj.getStr("password");

        // 查询数据
        // User user = JDBCUtil.executeQueryUser(username, password);

        // 通过mybatis查询数据
        User user = userMapper.selectUser(username, password);
        return user != null ? "SUCCESS" : "FAIL";
//        if ("admin".equals(username) && "admin".equals(password)) {
//            return "SUCCESS";
//        }
//        return "FAIL";
    }
}
