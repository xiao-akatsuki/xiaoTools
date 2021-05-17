package com.hcy.code.numUtil;

import com.hcy.code.arrayUtil.ArrayUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数字工具类
 * @author HCY
 * @since 2021/5/16 1:59 下午
*/
public class NumUtil {
    /**
     * 巧妙数
     */
    private static final int[] ARR = {0,2,3};

    /**
     * 圆周率
     */
    private static final double PI = Math.PI;

    /**
     * 常数
     */
    private static final double E = Math.E;

    /**
     * 阿拉伯数字的数组
     */
    private static final int[] ARAB1 = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final int[] ARAB2 = {1,5,10,50,100,500,1000};

    /**
     * 罗马数字的数组
     */
    private static final String[] ROMAN1 = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    private static final char[] ROMAN2 = {'I','V','X','L','C','D','M'};


    /**
     * 初始化操作
     * @author HCY
     * @since 2021/5/16 1:59 下午
     * @return null
    */
    public NumUtil() { }

    /**
     * 加法
     * @author HCY
     * @since 2021/5/16 4:32 下午
     * @param addend1: 加数1
     * @param addend2: 加数2
     * @return double
    */
    public static double addition(double addend1,double addend2){
        return addition(Double.toString(addend1),Double.toString(addend2)).doubleValue();
    }

    /**
     * 减法
     * @author HCY
     * @since 2021/5/16 4:42 下午
     * @param subtraction: 减数
     * @param minuend: 被减数
     * @return double
    */
    public static double subtraction(double subtraction,double minuend){
        return subtraction(Double.toString(subtraction),Double.toString(minuend)).doubleValue();
    }

    /**
     * 乘法
     * @author HCY
     * @since 2021/5/16 4:58 下午
     * @param multiplier: 乘数
     * @param multiplicand: 被乘数
     * @return double
    */
    public static double multiplication(double multiplier,double multiplicand){
        return multiplication(Double.toString(multiplier),Double.toString(multiplicand)).doubleValue();
    }

    /**
     * 除法
     * @author HCY
     * @since 2021/5/16 6:33 下午
     * @param divisor: 除数
     * @param dividend: 被除数
     * @return double
    */
    public static double division(double divisor,double dividend){
        return division(Double.toString(divisor),Double.toString(dividend)).doubleValue();
    }

    /**
     * [保留的小数点](含有四舍五入)
     * @author HCY
     * @since 2021/5/16 6:59 下午
     * @param decimal: 小数
     * @param digit: 需要保留的小数点
     * @return double
    */
    public static double retainDecimalPoint(double decimal,int digit){
        return retainDecimalPoint(Double.toString(decimal), Integer.toString(digit)).doubleValue();
    }

    /**
     * [保留小数点](含有四舍五入)
     * @author HCY
     * @since 2021/5/16 7:34 下午
     * @param decimal: 字符串模式的小数
     * @param digit: 需要保留的小数点
     * @return double
    */
    public static double retainDecimalPoint(String decimal,int digit){
        return retainDecimalPoint(decimal,Integer.toString(digit)).doubleValue();
    }

    /**
     * 判断是否是整数
     * @author HCY
     * @since 2021/5/16 7:45 下午
     * @param value: 疑似整数
     * @return boolean
    */
    public static boolean isInteger(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 判断是否是长整形
     * @author HCY
     * @since 2021/5/16 7:45 下午
     * @param value: 疑似长整形
     * @return boolean
    */
    public static boolean isLong(String value){
        try {
            Long.parseLong(value);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 判断是否是小数
     * @author HCY
     * @since 2021/5/16 7:53 下午
     * @param value: 疑似小数
     * @return boolean
    */
    public static boolean isDouble(String value){
        try {
            Double.parseDouble(value);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 判断是否是质数
     * @author HCY
     * @since 2021/5/16 8:18 下午
     * @param value: 数值
     * @return boolean
    */
    public static boolean isPrime(int value){
        if (value < ARR[1]){ return false; }
        if (value == ARR[1]){ return true; }
        if (value % ARR[1] == 0){ return false; }
        for (int i = ARR[2]; i * i <= value ; i+= ARR[1]){
            if ( value % i == 0){ return false; }
        }
        return true;
    }

    /**
     * 计算数字的阶乘
     * @author HCY
     * @since 2021/5/17 7:03 上午
     * @param n: 需要计算阶乘的数字
     * @return long
    */
    public static double factorial(double n){
        return n == 0 ? 1 : retainDecimalPoint(Math.sqrt(2 * n * PI) * Math.pow(n / E, n), 0);
    }

    /**
     * 求最大公约数
     * @author HCY
     * @since 2021/5/17 7:58 上午
     * @param m: 求最大公约数的值1
     * @param n: 求最大公约数的值2
     * @return int
    */
    public static int divisor(int m , int n){
        if (n == 0){ return m; }
        int r = m % n;
        return divisor(n,r);
    }

    /**
     * 求最小公倍数
     * @author HCY
     * @since 2021/5/17 9:12 上午
     * @param m: 求最小公倍数的值1
     * @param n: 求最小公倍数的值2
     * @return int
    */
    public static int multiple(int m ,int n){
        return (m * n) / divisor(m,n);
    }

    /**
     * 阿拉伯数字转换成为罗马数字
     * @author HCY
     * @since 2021/5/17 9:21 上午
     * @param num: 需要转换成为Roman数字的数值
     * @return java.lang.String
    */
    public static String intToRoman(int num){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ARAB1.length; i++) {
            int cv = ARAB1[i];
            String cs = ROMAN1[i];
            while (num >= cv){
                result.append(cs);
                num -= cv;
            }
        }
        return result.toString();
    }

    /**
     * 将罗马数字转换成为数字
     * @author HCY
     * @since 2021/5/17 9:45 上午
     * @param roman: 罗马数字
     * @return int
    */
    public static int romanToInt(String roman){
        int sum = 0;
        int preNum = romanOneToInt(roman.charAt(0));
        for (int i = 1; i < roman.length(); i++) {
            int sufNum = romanOneToInt(roman.charAt(i));
            if (preNum < sufNum){
                sum -= preNum;
            }else {
                sum += preNum;
            }
            preNum = sufNum;
        }
        sum += preNum;
        return sum;
    }

    /**
     * 将单个罗马数字转换为数字
     * @author HCY
     * @since 2021/5/17 9:44 上午
     * @param value: 单个罗马数字
     * @return int
    */
    private static int romanOneToInt(char value) {
        for (int i = 0; i < ARAB2.length; i++) {
            if (value == ROMAN2[i]){
                return ARAB2[i];
            }
        }
        return 0;
    }

    /**
     * [保留的小数点](含有四舍五入)
     * @author HCY
     * @since 2021/5/16 7:10 下午
     * @param values: 需要保留小数点的数组
     * @return java.math.BigDecimal
    */
    private static BigDecimal retainDecimalPoint(String... values) {
        if (ArrayUtil.isEmpty(values)){
            return BigDecimal.ZERO;
        }else {
            String value = values[0];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);
            for(int i = 1; i < values.length; ++i) {
                result = result.setScale(Integer.parseInt(values[i]), RoundingMode.HALF_UP);
            }
            return result;
        }
    }

    /**
     * 除法
     * @author HCY
     * @since 2021/5/16 5:03 下午
     * @param values: 除法的数组
     * @return java.math.BigDecimal
    */
    private static BigDecimal division(String... values) {
        if (ArrayUtil.isEmpty(values)){
            return BigDecimal.ZERO;
        }else {
            String value = values[0];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = 1; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.divide(new BigDecimal(value));
                }
            }
            return result;
        }
    }

    /**
     * 乘法
     * @author HCY
     * @since 2021/5/16 4:51 下午
     * @param values: 乘法的数组
     * @return java.math.BigDecimal
    */
    private static BigDecimal multiplication(String... values) {
        if (ArrayUtil.isEmpty(values)){
            return BigDecimal.ZERO;
        }else {
            String value = values[0];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = 1; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.multiply(new BigDecimal(value));
                }
            }
            return result;
        }
    }

    /**
     * 减法
     * @author HCY
     * @since 2021/5/16 4:41 下午
     * @param values: 减法的数组
     * @return java.math.BigDecimal
    */
    private static BigDecimal subtraction(String... values) {
        if (ArrayUtil.isEmpty(values)){
            return BigDecimal.ZERO;
        }else {
            String value = values[0];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = 1; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.subtract(new BigDecimal(value));
                }
            }
            return result;
        }
    }
    /**
     * 加法
     * @author HCY
     * @since 2021/5/16 2:23 下午
     * @param values: 参与加法的数组
     * @return java.math.BigDecimal
    */
    private static BigDecimal addition(String... values) {
        if (ArrayUtil.isEmpty(values)) {
            return BigDecimal.ZERO;
        } else {
            String value = values[0];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = 1; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.add(new BigDecimal(value));
                }
            }
            return result;
        }
    }
}
