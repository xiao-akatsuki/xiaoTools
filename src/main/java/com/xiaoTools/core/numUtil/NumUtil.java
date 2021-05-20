package com.xiaoTools.core.numUtil;

import com.xiaoTools.core.arrayUtil.ArrayUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @description: zh - 数字工具类
 * @description: en - Digital tools
 * @author XiaoXunYao
 * @since 2021/5/16 1:59 [下午](afternoon)
*/
public class NumUtil {

    /**
     * @description: zh - 巧妙数
     * @description: en - Clever number
     */
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int CIRCULAR = 180;
    private static final double CLEVER = 10E-5;


    /**
     * @description: zh - 圆周率
     * @description: en - PI
     */
    private static final double PI = Math.PI;

    /**
     * @description: zh - 常数
     * @description: en - constant
     */
    private static final double E = Math.E;

    /**
     * @description: zh - 阿拉伯数字的数组
     * @description: en - Array of Arabic numerals
     */
    private static final int[] ARAB1 = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final int[] ARAB2 = {1,5,10,50,100,500,1000};

    /**
     * @description: zh - 罗马数字的数组
     * @description: en - Array of Roman numerals
     */
    private static final String[] ROMAN1 = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    private static final char[] ROMAN2 = {'I','V','X','L','C','D','M'};


    /**
     * [初始化操作](Initialization operation)
     * @description: zh - 这是初始化的操作
     * @description: en - This is the initialization operation
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 1:59 [下午](afternoon)
     */
    public NumUtil() { }

    /**
     * [加法](addition)
     * @description: zh - 计算两个数字之间的相加，可以带符号。
     * @description: en - Calculate the sum between two numbers, can be signed.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 4:32 [下午](afternoon)
     * @param addend1: [加数1](Addend 1)
     * @param addend2: [加数2](Addend 2)
     * @return double
    */
    public static double addition(double addend1,double addend2){
        return addition(Double.toString(addend1),Double.toString(addend2)).doubleValue();
    }

    /**
     * [减法](subtraction)
     * @description: zh - 将输入的两个数字进行相减。
     * @description: en - Subtract the two numbers you enter.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 4:42 [下午](afternoon)
     * @param subtraction: [减数](subtraction)
     * @param minuend: [被减数](minuend)
     * @return double
    */
    public static double subtraction(double subtraction,double minuend){
        return subtraction(Double.toString(subtraction),Double.toString(minuend)).doubleValue();
    }

    /**
     * [乘法](multiplication)
     * @description: zh - 将输入的两个数字进行相乘，可以带符号。
     * @description: en - Will enter the multiplication of two numbers, can be signed.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 4:58 [下午](afternoon)
     * @param multiplier: [乘数](multiplier)
     * @param multiplicand: [被乘数](multiplicand)
     * @return double
    */
    public static double multiplication(double multiplier,double multiplicand){
        return multiplication(Double.toString(multiplier),Double.toString(multiplicand)).doubleValue();
    }

    /**
     * [除法](division)
     * @description: zh - 计算两个数字进行相除得到的结果
     * @description: en - Calculate the result of dividing two numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 6:33 [下午](afternoon)
     * @param divisor: [除数](divisor)
     * @param dividend: [被除数](dividend)
     * @return double
    */
    public static double division(double divisor,double dividend){
        return division(Double.toString(divisor),Double.toString(dividend)).doubleValue();
    }

    /**
     * @description: zh - [保留小数点](含有四舍五入)
     * @description: en - [keep decimal point] (including rounding)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 6:59 [下午](afternoon)
     * @param decimal: [小数](decimal)
     * @param digit: [需要保留的小数点](Decimal point to keep)
     * @return double
    */
    public static double retainDecimalPoint(double decimal,int digit){
        return retainDecimalPoint(Double.toString(decimal), Integer.toString(digit)).doubleValue();
    }

    /**
     * @description: zh - [保留小数点](含有四舍五入)
     * @description: en - [keep decimal point] (including rounding)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 7:34 [下午](afternoon)
     * @param decimal: [字符串模式的小数](Decimal of string pattern)
     * @param digit: [需要保留的小数点](Decimal point to keep)
     * @return double
    */
    public static double retainDecimalPoint(String decimal,int digit){
        return retainDecimalPoint(decimal,Integer.toString(digit)).doubleValue();
    }

    /**
     * [判断是否是整数](Judge whether it is an integer)
     * @description: zh - 输入一个数字将会返回布尔值，true为是整数，false为不是整数
     * @description: en - Entering a number will return a Boolean value. True is an integer and false is not an integer
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 7:45 [下午](afternoon)
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
     * [判断是否是长整形](Judge whether it is long plastic surgery)
     * @description: zh - 输入一个数字判断是否是长整型，如果是则返回true，反之则返回false。
     * @description: en - Enter a number to judge whether it is a long integer. If it is, it will return true. Otherwise, it will return false.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 7:45 [下午](afternoon)
     * @param value: [疑似长整形](Suspected long plastic surgery)
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
     * [判断是否是小数](Judge whether it is a decimal)
     * @description: zh - 输入一个数字判断是否是双精度浮点型，如果是则返回true，反之则返回false。
     * @description: en - Enter a number to determine whether it is a double precision floating-point type. If it is, return true; otherwise, return false.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 7:53 [下午](afternoon)
     * @param value: [疑似双精度浮点型](Suspected double precision floating point)
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
     * [判断是否是质数](Judge whether it is prime or not)
     * @description: zh - 输入一个数字判断是否是质数，如果是则返回true，反之则返回false。
     * @description: en - Enter a number to judge whether it is a prime number. If it is, it will return true. Otherwise, it will return false.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 8:18 [下午](afternoon)
     * @param value: [疑似质数](Suspected prime number)
     * @return boolean
    */
    public static boolean isPrime(int value){
        if (value < TWO){ return false; }
        if (value == TWO){ return true; }
        if (value % TWO == ZERO){ return false; }
        for (int i = THREE; i * i <= value ; i+= TWO){
            if ( value % i == ZERO){ return false; }
        }
        return true;
    }

    /**
     * [计算数字的阶乘](Calculate the factorial of a number)
     * @description: zh - 输入一个数组，将会计算出他的阶乘，然后将其返回。
     * @description: en - Entering an array will calculate its factorial and return it.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/17 7:03 [上午](morning)
     * @param number: [需要计算阶乘的数字](A number that needs to be factorized)
     * @return long
    */
    public static double factorial(double number){
        return number == ZERO ? ONE : retainDecimalPoint(Math.sqrt(TWO * number * PI) * Math.pow(number / E, number), ZERO);
    }

    /**
     * [求最大公约数](greatest common factor)
     * @description: zh - 输入两个数字，计算出他们的最大公约数。
     * @description: en - Enter two numbers and calculate their greatest common divisor.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/17 7:58 [上午](morning)
     * @param m: [求最大公约数的值1](Finding the value 1 of the greatest common divisor)
     * @param n: [求最大公约数的值2](Finding the value 2 of the greatest common divisor)
     * @return int
    */
    public static int divisor(int m , int n){
        if (n == 0){ return m; }
        int r = m % n;
        return divisor(n,r);
    }

    /**
     * [求最小公倍数](Finding the least common multiple)
     * @description: zh - 输入两个数字，计算出他们的最小公倍数。
     * @description: en - Enter two numbers and calculate their least common multiple.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/17 9:12 [上午](morning)
     * @param m: [求最小公倍数的值1](Finding the value 1 of the least common multiple)
     * @param n: [求最小公倍数的值2](Finding the value 2 of the least common multiple)
     * @return int
    */
    public static int multiple(int m ,int n){
        return (m * n) / divisor(m,n);
    }

    /**
     * @description: [阿拉伯数字转换成为罗马数字](Conversion of Arabic numerals to Roman numerals)
     * @description: zh - 输入数字，将他转换成为罗马数字。
     * @description: en - Enter the number and convert it to Roman.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/17 9:21 [上午](morning)
     * @param num: [需要转换成为Roman数字的数值](Values that need to be converted to roman numbers)
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
     * [将罗马数字转换成为数字](Converting Roman numerals to numbers)
     * @description: zh - 输入罗马数字，将它转换成为阿拉伯数字。
     * @description: en - Enter Roman numerals and convert them to Arabic numerals.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/17 9:45 [上午](morning)
     * @param roman: [罗马数字](Roman numerals)
     * @return int
    */
    public static int romanToInt(String roman){
        int sum = ZERO;
        int preNum = romanOneToInt(roman.charAt(0));
        for (int i = ONE; i < roman.length(); i++) {
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
     * [计算绝对值](Calculate the absolute value)
     * @description: zh - 输入一个数值，计算出他的绝对值
     * @description: en - Enter a number and calculate its absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/19 10:33 [下午](afternoon)
     * @param value: [需要计算绝对值数值](The absolute value needs to be calculated)
     * @return double
    */
    public static double absolute(double value){
        return value < ZERO ? ZERO - value : value;
    }

    /**
     * [计算该度数的正弦值](Calculate the sine of the degree)
     * @description: zh - 输入一个角度计算出该角度的正弦值
     * @description: en - Enter an angle and calculate the sine value of the angle
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/20 7:56 [上午](morning)
     * @param angle: [计算的度数](Calculated degrees)
     * @return double
    */
    public static double sine(double angle){
        angle = (angle * PI) / CIRCULAR;
        int i = ONE, sign = ONE;
        double item = angle, answer = ZERO, molecule = angle, denominator = ZERO;
        for(; absolute(item) >= CLEVER ; i += TWO){
            //累加
            answer += item;
            //分子
            molecule = molecule * angle * angle;
            //分母
            denominator = denominator * ( i + ONE ) * ( i + TWO );
            //符号
            sign = -sign;
            //临时变量
            item = sign * (molecule/denominator);
        }
        return answer;
    }

    /**
     * [计算该角度的余弦值](Calculate the cosine of the angle)
     * @description: zh - 输入一个角度，计算该角度的余弦值。
     * @description: en - Enter an angle and calculate the cosine of the angle.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/20 7:59 [上午](morning)
     * @param angle: 角度
     * @return double
    */
    public static double cosine(double angle){

        angle = (angle * PI) / CIRCULAR;
        int i = ZERO, sign = ONE;
        double item = ONE , answer = ZERO, molecule = ONE, denominator = ZERO ;
        for( ; absolute(item) >= CLEVER ; i += TWO){
            answer += item;
            molecule = molecule * angle * angle;
            denominator = denominator * ( i + ONE ) * ( i + TWO );
            sign = -sign;
            item = sign * ( molecule / denominator );
        }
        return answer;
    }


    
    /**
     * [将单个罗马数字转换为数字](Converting a single Roman numeral to a number)
     * @description: zh - 将输入的罗马数字的字符串转换成为数字
     * @description: en - Converts the input Roman numeral string to a number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/17 9:44 [上午](morning)
     * @param value: [单个罗马数字](Single Roman numeral)
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
     * [保留小数点](Keep decimal point)
     * @description: zh - [保留的小数点](含有四舍五入)
     * @description: en - [reserved decimal point] (including rounding)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 7:10 [下午](afternoon)
     * @param values: [需要保留小数点的数组](Array with decimal point)
     * @return java.math.BigDecimal
    */
    private static BigDecimal retainDecimalPoint(String... values) {
        if (ArrayUtil.isEmpty(values)){
            return BigDecimal.ZERO;
        }else {
            String value = values[ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);
            for(int i = ONE; i < values.length; ++i) {
                result = result.setScale(Integer.parseInt(values[i]), RoundingMode.HALF_UP);
            }
            return result;
        }
    }

    /**
     * [除法](division)
     * @description: zh - 计算除法的方法
     * @description: en - The method of calculating Division
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 5:03 [下午](afternoon)
     * @param values: [完成除法所组成的数组](Complete the array of division)
     * @return java.math.BigDecimal
    */
    private static BigDecimal division(String... values) {
        if (ArrayUtil.isEmpty(values)){
            return BigDecimal.ZERO;
        }else {
            String value = values[ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = ONE; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.divide(new BigDecimal(value));
                }
            }
            return result;
        }
    }

    /**
     * [乘法](multiplication)
     * @description: zh - 具体完成乘法的方法
     * @description: en - The specific method of multiplication
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 4:51 [下午](afternoon)
     * @param values: [乘法的数组](Multiplicative array)
     * @return java.math.BigDecimal
    */
    private static BigDecimal multiplication(String... values) {
        if (ArrayUtil.isEmpty(values)){
            return BigDecimal.ZERO;
        }else {
            String value = values[ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = ONE; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.multiply(new BigDecimal(value));
                }
            }
            return result;
        }
    }

    /**
     * [减法](subtraction)
     * @description: zh - 具体完成减法的方法
     * @description: en - How to complete the subtraction
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 4:41 [下午](afternoon)
     * @param values: [减法的数组](Subtracted array)
     * @return java.math.BigDecimal
    */
    private static BigDecimal subtraction(String... values) {
        if (ArrayUtil.isEmpty(values)){
            return BigDecimal.ZERO;
        }else {
            String value = values[ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = ONE; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.subtract(new BigDecimal(value));
                }
            }
            return result;
        }
    }
    /**
     * [加法](addition)
     * @description: zh - 具体完成加法的方法
     * @description: en - How to complete the addition
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 2:23 [下午](afternoon)
     * @param values: [参与加法的数组](Arrays participating in addition)
     * @return java.math.BigDecimal
    */
    private static BigDecimal addition(String... values) {
        if (ArrayUtil.isEmpty(values)) {
            return BigDecimal.ZERO;
        } else {
            String value = values[ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = ONE; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.add(new BigDecimal(value));
                }
            }
            return result;
        }
    }
}
