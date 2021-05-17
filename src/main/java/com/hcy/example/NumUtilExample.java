package com.hcy.example;

import com.hcy.code.console.Console;
import com.hcy.code.numUtil.NumUtil;

/**
 * 数字工具类测试
 * @author HCY
 * @since 2021/5/16 4:20 下午
*/
public class NumUtilExample {
    public static void main(String[] args) {
        //加法
        System.out.println( " 1 + 2 = " + NumUtil.addition(1.0f, 2.0f));
        System.out.println( " 1 + 2 = " + NumUtil.addition(1, 2));
        System.out.println("-------------------------------------");
        //减法
        System.out.println( " 4 - 2 = " + NumUtil.subtraction(4.0f, 2.0d));
        System.out.println( " 2 - 4 = " + NumUtil.subtraction(2.0d, 4.0f ));
        System.out.println("-------------------------------------");
        //乘法
        System.out.println( " 2 * 4 = " + NumUtil.multiplication(2.0d, 4.0f ));
        System.out.println( " 2 * -1 = " + NumUtil.multiplication(2.0d, -4.0f ));
        System.out.println("-------------------------------------");
        //除法
        System.out.println( " 2 / 4 = " + NumUtil.division(2.0d, 4.0f ));
        System.out.println( " 2 / -4 = " + NumUtil.division(2.0d, -4.0f ));
        System.out.println("-------------------------------------");
        //保留小数点
        double te1=123456.123456;
        double te2=123456.128456;
        //结果:123456.1235
        Console.log(NumUtil.retainDecimalPoint(te1,4));
        //结果:123456.1285
        Console.log(NumUtil.retainDecimalPoint(te2,4));
        System.out.println("-------------------------------------");
        //保留小数点
        String te3 = "123456.123456";
        String te5 = "123456.128456";
        //结果:123456.1235
        Console.log(NumUtil.retainDecimalPoint(te3,4));
        //结果:123456.1285
        Console.log(NumUtil.retainDecimalPoint(te5,4));
        System.out.println("-------------------------------------");
        //判断字符串是否为Integer类型
        System.out.println(NumUtil.isInteger("ad"));
        System.out.println(NumUtil.isInteger("123"));
        System.out.println("-------------------------------------");
        //判断字符串是否为Long类型
        System.out.println(NumUtil.isLong("ad"));
        System.out.println(NumUtil.isLong("123"));
        System.out.println("-------------------------------------");
        //判断字符串是否为Double类型
        System.out.println(NumUtil.isDouble("ad"));
        System.out.println(NumUtil.isDouble("123.3"));
        System.out.println("-------------------------------------");
        //判断数值是否是质数
        System.out.println(NumUtil.isPrime(2));
        System.out.println(NumUtil.isPrime(1));
        System.out.println(NumUtil.isPrime(3));
        System.out.println(NumUtil.isPrime(5));
        System.out.println(NumUtil.isPrime(7));
        System.out.println("-------------------------------------");
        //计算某一个数的阶乘
        System.out.println(NumUtil.factorial(4.0));
        System.out.println(NumUtil.factorial(1.0));
        System.out.println(NumUtil.factorial(0.0));
        System.out.println("-------------------------------------");
        //计算某一个数值的最大公约数
        System.out.println(NumUtil.divisor(18, 20));
        System.out.println("-------------------------------------");
        //计算某一个数值的最小公倍数
        System.out.println(NumUtil.multiple(18, 20));
        System.out.println("-------------------------------------");
        //将阿拉伯数字转换成为罗马数字
        /*III*/
        System.out.println(NumUtil.intToRoman(3));
        /*IV*/
        System.out.println(NumUtil.intToRoman(4));
        /*IX*/
        System.out.println(NumUtil.intToRoman(9));
        /*LVIII*/
        System.out.println(NumUtil.intToRoman(58));
        /*MCMXCIV*/
        System.out.println(NumUtil.intToRoman(1994));
        System.out.println("-------------------------------------");
        //将罗马数字转换成为数字
        /*3*/
        System.out.println(NumUtil.romanToInt("III"));
        /*4*/
        System.out.println(NumUtil.romanToInt("IV"));
        /*9*/
        System.out.println(NumUtil.romanToInt("IX"));
        /*58*/
        System.out.println(NumUtil.romanToInt("LVIII"));
        /*1994*/
        System.out.println(NumUtil.romanToInt("MCMXCIV"));
    }
}
