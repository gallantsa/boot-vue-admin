package com.example.bootvueadmin.service;

import cn.hutool.core.util.StrUtil;
import com.example.bootvueadmin.common.exception.CustomerException;
import com.example.bootvueadmin.entity.User;
import com.example.bootvueadmin.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String DEFAULT_PASSWORD = "123";

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

    public List<User> findAll(String name, String phone) {
        return userMapper.selectAll(name, phone);
    }

    public void save(User user) {
        User res = userMapper.selectUserByUsername(user.getUsername());
        if (res != null) { // 表示数据库中没有重名用户
            logger.error("用户名重复");
            throw new CustomerException("用户名重复");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword(DEFAULT_PASSWORD);
        }
        userMapper.save(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }
}
