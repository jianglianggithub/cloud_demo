package com;


import com.dao.entity.SysPermission;
import com.dao.service.TestService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.List;

@SpringBootApplication
@RestController
@MapperScan(basePackages = {"com.dao.com.mapper"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Autowired
    TestService testService;
    @RequestMapping("/aa")
    public Object test(Vo vo,String test){
        List<SysPermission> sle = testService.sle();
        return sle;
    }



}
