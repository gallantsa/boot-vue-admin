package com.example.bootvueadmin;

import com.example.bootvueadmin.common.AuthFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

// 扫描mapper
@MapperScan("com.example.bootvueadmin.mapper")
@SpringBootApplication
public class BootVueAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootVueAdminApplication.class, args);
    }


    /**
     * 注册Filter
     */
    @Bean
    public FilterRegistrationBean<AuthFilter> getFilterRegistrationBean(){
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>(new AuthFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

}
