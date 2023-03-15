package com.ciner.dongbao.portal.web.Md5;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

@SpringBootTest
public class EncoderOrDecoderTest {

    @Test
    public void md5(){
        String sourceString = "123456";
        String s = DigestUtils.md5DigestAsHex(sourceString.getBytes());

        System.out.println("第1次加密值："+s);
        s = DigestUtils.md5DigestAsHex(sourceString.getBytes());
        System.out.println("第2次加密值："+s);
    }

    @Test
    public void bcrypt() {
        String  sourceString = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第一次加密值："+encode);
        boolean matches = bCryptPasswordEncoder.matches(sourceString, encode);
        System.out.println("第一次验证："+matches);
        encode = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第二次加密值："+encode);
        matches = bCryptPasswordEncoder.matches(sourceString, encode);
        System.out.println("第一次验证："+matches);
    }

}
