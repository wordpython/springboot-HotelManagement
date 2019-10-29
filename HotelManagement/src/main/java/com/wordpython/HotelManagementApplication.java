package com.wordpython;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.wordpython.dao","com.wordpython.admin.dao"})
public class HotelManagementApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HotelManagementApplication.class, args);
    }
    /*
     * 打包成war包时
     *启动类继承了SpringBootServletInitializer类并且重写configure方法
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HotelManagementApplication.class);
    }

}
