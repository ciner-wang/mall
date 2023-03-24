package com.ciner.dongbao.common.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.omg.CORBA.TIMEOUT;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Base64只是编码器，可以编码解码；
 * 散列算法：MD5,sha系列
 * 对称加密：DES,3DES,AES
 * 非对称加密：公加私解，私加公解
 * MD5才是加密算法，不可逆，只能彩虹表暴力破解
 */
public class JwtUtil {

    private static final String secret = "asdfasdf";
    public static String createToken(String subject){


        String token = Jwts.builder().setSubject(subject)
                //有效期，System.currentTimeMillis()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return token;
    }

    public static String parseToken(String token){
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        String subject = body.getSubject();

        return subject;
    }

    /*
    public static void main(String[] args) throws InterruptedException {
        String name = "王丽琦";
        String token = createToken(name);
        System.out.println("token:" + token);

        String srcStr = parseToken(token);
        System.out.println("解析结果："+srcStr);

        TimeUnit.SECONDS.sleep(4);

        srcStr = parseToken(token);
        System.out.println("解析结果："+srcStr);
    }
    */
}
