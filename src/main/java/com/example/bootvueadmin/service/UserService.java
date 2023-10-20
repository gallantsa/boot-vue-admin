package com.example.bootvueadmin.service;

import com.example.bootvueadmin.entity.User;
import com.example.bootvueadmin.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        // 通过mybatis查询数据
        User user = userMapper.selectUser(username, password);
        return user;
    }

    public boolean register(String username, String password) {
        try {
            User user = userMapper.selectUserByUsername(username);
            if (user == null) {
                User savedUser = new User(username, password);
                userMapper.save(savedUser);
            } else { // 有重名用户, 就注册失败了
                return false;
            }
        } catch (Exception e) {
            logger.error("注册用户失败", e); // 非常重要的排查错误的手段
            return false;
        }
        return true;
    }
}
