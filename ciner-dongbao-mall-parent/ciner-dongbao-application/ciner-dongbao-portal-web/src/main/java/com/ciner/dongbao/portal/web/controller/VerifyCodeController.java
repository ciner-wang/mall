package com.ciner.dongbao.portal.web.controller;

import com.ciner.dongbao.portal.web.code.ImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;


@RestController
@RequestMapping("/code")
public class VerifyCodeController {

    @GetMapping("/generator")
    public void generatorCode(HttpServletResponse response){

        try {
            ImageCode imageCode = ImageCode.getInstance();

            //验证码的值
            String code = imageCode.getCode();
            //验证码图片
            ByteArrayInputStream image = imageCode.getImage();
            response.setContentType("image/jpeg");

            byte[] bytes = new byte[1024];
            try(ServletOutputStream out = response.getOutputStream()){
                while (image.read(bytes) != -1){
                    out.write(bytes);
                }
            }
        } catch (Exception e){
            System.out.println("异常");
        }



    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request){

        return "";
    }
}
