package com.yizhuoyan.teaching.demo.core;

import javax.xml.bind.SchemaOutputResolver;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Administrator on 18/04/17.
 */
public class RMBUpper {
    //定义相关常量
    private static final  char[] DIGIT_CHARS = "零壹贰叁肆伍陆柒捌玖".toCharArray();
    private static final  char[] UNIT_CHARS ="元角分整".toCharArray();
    private static final  String[] RADIX_CHARS = new String[]{"","拾","佰", "仟", "万", "亿"};

    /**
     * 人民大写转换
     *@param {string} currencyStr 金额字符串
     */
    public static String convertCurrency(String currencyStr) {
        //1 验证输入
        if (currencyStr==null||(currencyStr=currencyStr.trim()).length() == 0) {
            throw new IllegalArgumentException("金额不能为空");
        }
        //2去掉,空格
        currencyStr = currencyStr.replaceAll("[, ]","");

        if (currencyStr.length() == 0) {
            throw new IllegalArgumentException("金额格式错误");
        }
        //3验证格式
        BigDecimal currencyNum;
        try {
            currencyNum = new BigDecimal(currencyStr);
        }catch (Exception e){
            throw new IllegalArgumentException("金额格式错误");
        }

        double currencyDoubleValue=currencyNum.doubleValue();
        //4限制金额
        if (currencyDoubleValue>=1e12) {
            throw new IllegalArgumentException("金额过大,不能超过1万亿");
        }
        if(currencyDoubleValue<0){
            throw new IllegalArgumentException("金额必须为正数");
        }

        //5保留小数点后两位,四舍五入
        currencyNum=currencyNum.setScale(2,BigDecimal.ROUND_HALF_UP);

        //6 处理0元
        if (currencyNum.doubleValue() ==0) {
            return new String(new char[]{DIGIT_CHARS[0],UNIT_CHARS[0]});
        }
        //7 转换为字符串,便于取出整数和小数
        currencyStr = currencyNum.toPlainString();
        int dotPos = currencyStr.lastIndexOf(".");
        String integral = currencyStr,  decimal = "";
        System.out.println(currencyStr);
        if (dotPos != -1) {
            integral = currencyStr.substring(0, dotPos);
            decimal = currencyStr.substring(dotPos + 1,currencyStr.length());
        }
        //8定义结果字符串数组
        StringBuilder result= new StringBuilder();
        //9处理整数部分,0.##不处理
        if (currencyNum.doubleValue() >= 1) {
            int zeroCount = 0;
            int pos, numChar;
            for (int i = 0; i < integral.length(); i++) {
                numChar = integral.charAt(i);
                pos = integral.length() - i - 1;
                if (numChar != '0') {
                    //添零
                    if (zeroCount > 0) {
                        result.append(DIGIT_CHARS[0]);
                        zeroCount = 0;
                    }
                    //放入对应大写
                    result.append(DIGIT_CHARS[numChar-'0']);
                    //放入基数
                    result.append(RADIX_CHARS[pos % 4]);
                } else {
                    zeroCount++;
                }
                //添加万亿
                if (pos != 0 && pos % 4 == 0 && zeroCount < 4) {
                    result.append(RADIX_CHARS[pos / 4 + 3]);
                }

            }
            result.append("元");
        }
        System.out.println(decimal);
        //10处理小数
        for (int i = 0; i < decimal.length(); i++) {
            result.append(DIGIT_CHARS[decimal.charAt(i)-'0']);
            result.append(UNIT_CHARS[i + 1]);
        }
        //11添加整或正字
        if (decimal.length()!= 2) {
            result.append(UNIT_CHARS[3]);
        }
        return result.toString();
    }

    public static void main(String[] args) throws Throwable {
        String result=convertCurrency("34562323.245543");
        System.out.println(result);
    }

}
