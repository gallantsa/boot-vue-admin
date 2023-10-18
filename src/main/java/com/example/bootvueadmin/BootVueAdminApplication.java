package com.example.bootvueadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 扫描mapper
@MapperScan("com.example.bootvueadmin.mapper")
@SpringBootApplication
public class BootVueAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootVueAdminApplication.class, args);
    }

}
