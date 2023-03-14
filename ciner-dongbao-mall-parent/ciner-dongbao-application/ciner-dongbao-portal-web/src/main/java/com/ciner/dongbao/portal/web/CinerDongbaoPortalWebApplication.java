package com.ciner.dongbao.portal.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ciner"})
@MapperScan("com.ciner.dongbao.ums.mapper")
public class CinerDongbaoPortalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinerDongbaoPortalWebApplication.class, args);
    }

}
