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
import java.util.List;

// SpringMVC
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user, HttpServletRequest request) { // 把前台发过来的 json字符串转成Java对象

        // 通过mybatis查询数据
        User res = userService.login(user.getUsername(), user.getPassword());
        if (res != null) {
            // 把用户信息存到session中
            request.getSession().setAttribute("user", res);
        }
        return Result.success(res);
    }

    /**
     * 注册
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user, HttpServletRequest request) { // 把前台发过来的 json字符串转成Java对象
        // 通过mybatis查询数据
        userService.register(user.getUsername(), user.getPassword());
        return Result.success();
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        System.out.println("登出之后session == " + request.getSession().getAttribute("user"));
        response.sendRedirect("/login.html");
    }

    // 写一个get请求
    @GetMapping("/all")
    public Result<List<User>> findAll(String name,
                                      @RequestParam(required = false) String phone) {
        return Result.success(userService.findAll(name, phone));
    }

    @PostMapping
    public Result<Void> add(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }
}
