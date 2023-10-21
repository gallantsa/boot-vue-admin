package com.example.bootvueadmin.service;

import com.example.bootvueadmin.common.exception.CustomerException;
import com.example.bootvueadmin.entity.User;
import com.example.bootvueadmin.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        // 通过mybatis查询数据
        User user = userMapper.selectUser(username, password);
        if (user == null) {
            logger.error("用户名或密码错误");
            throw new CustomerException("用户名或密码错误");
        }
        return user;
    }

    public void register(String username, String password) throws CustomerException {

        User user = userMapper.selectUserByUsername(username);
        if (user != null) { // 表示数据库中没有重名用户
            logger.error("用户名重复");
            throw new CustomerException("用户名重复");
        }

        userMapper.save(new User(username, password));
    }
}
