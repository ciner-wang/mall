package com.ciner.dongbao.portal.web;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@SpringBootTest
class CinerDongbaoPortalWebApplicationTests {

    @Test
    void contextLoads() throws IOException {
        FileInputStream file = new FileInputStream("D:/aa.txt");
        InputStreamReader reader = new InputStreamReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String str = "";
        String strings = "";

        BufferedWriter out = new BufferedWriter(new FileWriter("D:/bb.txt"));

        String time = null;
        while ((str = bufferedReader.readLine()) != null){
            if (str.length()<56) continue;
            String t = str.substring(15, 18).replace(" ","");
            if (t.equals("52")) {

                strings = splitString(str.substring(39, str.length() - 12), 33);
            } else {

                strings = splitString2(str.substring(39, str.length() - 12), 69);
            }
            time = "20"+str.substring(21, 29).replace(" ","-")+" "
            + str.substring(30, 38).replace(" ",":");
            //System.out.println(hexToTime(t)+"   "+strings);
            out.write(time+" "+hexToTime(t)+"   "+strings+"\n");

        }

        out.close();
        bufferedReader.close();
    }

    /**
     *数字切割
     */
    public String splitString2 (String src, int length){

        //检查参数是否合法
        if (null == src || src.equals("")) {
            return null;
        }

        if (length <= 0) {
            return null;
        }
        int n = (src.length() + length - 1) / length; //获取整个字符串可以被切割成字符子串的个数
        String[] split = new String[n];

        String sum = "";

        for (int i = 0; i < n; i++) {

            if (i < (n - 1)) {
                split[i] = src.substring(i * length, (i + 1) * length);

            } else {
                split[i] = src.substring(i * length);

            }

            String sp = split[i].substring(0,66);

            String bs = hexToBs(sp.substring(0,9).replace(" ",""));
            String vs = sp.substring(18);
            String v1 = "排放量"+hexToSingle(vs.substring(0,12),32);
            String v2 = "最小值"+hexToSingle(vs.substring(12,24),32);
            String v3 = "平均值"+hexToSingle(vs.substring(24,36),32);
            String v4 = "最大值"+hexToSingle(vs.substring(36,48),32);
            sp = bs+":"+v1+","+v2+","+v3+","+v4+";";
            sum += sp;
        }

        return sum;


    }

    /**
     *数字切割
     */
    public String splitString (String src, int length){

        //检查参数是否合法
        if (null == src || src.equals("")) {
            return null;
        }

        if (length <= 0) {
            return null;
        }
        int n = (src.length() + length - 1) / length; //获取整个字符串可以被切割成字符子串的个数
        String[] split = new String[n];

        String sum = "";

        for (int i = 0; i < n; i++) {

            if (i < (n - 1)) {
                split[i] = src.substring(i * length, (i + 1) * length);

            } else {
                split[i] = src.substring(i * length);

            }
            String sp = split[i].substring(0,30);

            String bs = hexToBs(sp.substring(0, 9).replace(" ", ""));
            String time = sp.substring(12, 15).replace(" ", "").equals("5A")?"Zs":"";
            sp = hexToSingle(sp.substring(18),32);
            String v = sp;

            sum += bs+time+":"+v+";";

        }

        return sum;


    }


    /**
     * 字节数组转IEEE 754
     *
     * @param hex
     * @param bitLen 32或者64
     * @author: 若非
     * @date: 2021/9/10 16:57
     */
    private static String hexToSingle(String hex, int bitLen) {
        hex = hex.replace(" ", "");
        if (StringUtils.isEmpty(hex)) {
            return BigDecimal.valueOf(0).toString();
        }
        if (bitLen == 32) {
            BigInteger bi = new BigInteger(hex, 16);
            //int i = Integer.parseInt(hex, 16);
            float v = Float.intBitsToFloat(bi.intValue());
            String s = new BigDecimal(v).toString();

            if (s.indexOf(".") != -1){
                int r = s.split("\\.")[0].length();
                int l = s.split("\\.")[1].length();
                return s.substring(0, l>2?(r+5):l);
            }else {
                return s;
            }

        }
//        if (bitLen == 64) {
//            long l = Long.parseLong(hex, 16);
//            double d = Double.longBitsToDouble(l);
//            return new BigDecimal(d).toString();
//        }
        return BigDecimal.valueOf(0).toString();
    }


    public static String hexToBs(String s){
        if (s.equals("423032")){
            return "烟气瞬时流量";
        }
        if (s.equals("393920")){
            return "甲烷";
        }
        if (s.equals("533031")){
            return "O2含量";
        }
        if (s.equals("533032")){
            return "烟气流速";
        }
        if (s.equals("533033")){
            return "烟气温度";
        }
        if (s.equals("533034")){
            return "烟气动压";
        }
        if (s.equals("533035")){
            return "烟气湿度";
        }
        if (s.equals("533036")){
            return "制冷温度";
        }
        if (s.equals("533037")){
            return "烟道截面积";
        }
        if (s.equals("533038")){
            return "烟气压力";
        }
        if (s.equals("313133")){
            return "总经";
        }
        if (s.equals("313134")){
            return "非甲烷总经";
        }

        return "";
    }

    public static String hexToTime(String s){

        if (s.equals("52")){
            return "实时数据";
        }
        if (s.equals("4D")){
            return "分钟数据";
        }
        if (s.equals("48")){
            return "小时数据";
        }
        if (s.equals("44")){
            return "日数据";
        }

        return "";
    }
}
