package com.example.managersystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.managersystem.mapper")
public class ManagerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerSystemApplication.class, args);
    }

}
