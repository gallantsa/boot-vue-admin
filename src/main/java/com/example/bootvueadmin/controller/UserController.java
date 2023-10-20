package com.example.bootvueadmin.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.bootvueadmin.common.Result;
import com.example.bootvueadmin.entity.User;
import com.example.bootvueadmin.mapper.UserMapper;
import com.example.bootvueadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

// SpringMVC
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param userStr
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody String userStr, HttpServletRequest request) { // 把前台发过来的 json字符串转成Java对象
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
        User user = userService.login(username, password);
        if (user != null) {
            // 把用户信息存到session中
            request.getSession().setAttribute("user", user);
            return Result.success(user);
        } else {
            return Result.error("账号或密码错误");
        }

//        if ("admin".equals(username) && "admin".equals(password)) {
//            return "SUCCESS";
//        }
//        return "FAIL";
    }

    /**
     * 注册
     * @param userStr
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody String userStr, HttpServletRequest request) { // 把前台发过来的 json字符串转成Java对象
        JSONObject userObj = JSONUtil.parseObj(userStr);

        // 把接口参数转换成 java bean
        // User user = JSONUtil.toBean(userStr, User.class);

        // 直接从json对象获取属性
        String username = userObj.getStr("username");
        String password = userObj.getStr("password");

        // 查询数据
        // User user = JDBCUtil.executeQueryUser(username, password);

        // 通过mybatis查询数据
        userService.register(username, password);
        return Result.success();
//        if ("admin".equals(username) && "admin".equals(password)) {
//            return "SUCCESS";
//        }
//        return "FAIL";
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        System.out.println("登出之后session == " + request.getSession().getAttribute("user"));
        response.sendRedirect("/login.html");
    }
}
