package com.ciner.dongbao.portal.web.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Random;

@Data
public class ImageCode {

    //内容
    private String code;

    //图片
    private ByteArrayInputStream image;

    //宽
    private int width = 100;

    //长
    private int height = 40;

    //单例
    public static ImageCode getInstance(){
        return new ImageCode();
    }
    public ImageCode() {
        //图形缓冲区

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //给你一支笔
        Graphics graphics = image.getGraphics();

        //拿笔，画图形
        graphics.setColor(new Color(17,147,37));

        //画矩形
        graphics.fillRect(0,0, width, height);

        //画字
        graphics.setFont(new Font("宋体",Font.PLAIN,30));

        Random random = new Random();
        this.code = "";
        for (int i = 0; i < 6; i++) {
            String s = String.valueOf(random.nextInt(10));

            this.code += s;
            graphics.setColor(new Color(235,152,37));

            graphics.drawString(s, (width/6)*i, 40);
        }

        //收笔
        graphics.dispose();
        ByteArrayInputStream inputStream = null;
        ByteOutputStream outputStream = new ByteOutputStream();

        try {
            //赋值给byteArrayInputStream
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image,"jpeg",imageOutputStream);

            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            System.out.println("生成验证码失败");
        }
        this.image = inputStream;

        }
    }
