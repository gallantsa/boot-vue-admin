package com.example.bootvueadmin.mapper;

import com.example.bootvueadmin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectUser(@Param("username") String username,@Param("password") String password);
}
