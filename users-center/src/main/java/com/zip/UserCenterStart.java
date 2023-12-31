package com.zip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@MapperScan("com.example.demo.**.mapper")
public class UserCenterStart {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterStart.class,args);
    }
}
