package com.example.bootvueadmin.mapper;

import com.example.bootvueadmin.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    User selectUser(@Param("username") String username,@Param("password") String password);

    User selectUserByUsername(String username);

    int save(User savedUser);

//    @Select("select * from user")
    List<User> selectAll(@Param("name") String name, @Param("phone") String phone);

    void update(User user);

    void deleteById(Integer id);
}
