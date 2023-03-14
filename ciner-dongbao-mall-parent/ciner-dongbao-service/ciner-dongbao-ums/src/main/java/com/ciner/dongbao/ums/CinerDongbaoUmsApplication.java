package com.ciner.dongbao.ums;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//测试时取消注释
//@MapperScan("com.ciner.dongbao.ums.mapper")
public class CinerDongbaoUmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinerDongbaoUmsApplication.class, args);
    }

}
