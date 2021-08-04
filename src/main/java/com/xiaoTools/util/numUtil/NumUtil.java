package com.xiaoTools.util.numUtil;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.randomUtil.RandomUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @description: zh - 数字工具类
 * @description: en - Digital tools
 * @author XiaoXunYao
 * @since 2021/5/16 1:59 [下午](afternoon)
*/
public class NumUtil {

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

    private static final long[] FACTORIALS = new long[]{
            1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L, 39916800L, 479001600L, 6227020800L,
            87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L,
            2432902008176640000L};


    /**
     * [初始化操作](Initialization operation)
     * @description: zh - 这是初始化的操作
     * @description: en - This is the initialization operation
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/16 1:59 [下午](afternoon)
     */
    public NumUtil() { }

    /*Basic four operations-----------------------------------------------------------基本四则运算*/

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

    /*keep decimal point ----------------------------------------------------------- 保留小数点*/

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

    /*Determine whether it is an integer ----------------------------------------------------------- 判断是否是整数*/

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

    /*Judge whether it is a prime number ----------------------------------------------------------- 判断是否是质数*/

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
        if (value < Constant.TWO){ return false; }
        if (value == Constant.TWO){ return true; }
        if (value % Constant.TWO == Constant.ZERO){ return false; }
        for (int i = Constant.THREE; i * i <= value ; i+= Constant.TWO){
            if ( value % i == Constant.ZERO){ return false; }
        }
        return true;
    }

    /*Calculate the factorial of a number ----------------------------------------------------------- 计算数字的阶乘*/

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
        return number == Constant.ZERO ? Constant.ONE : retainDecimalPoint(Math.sqrt(Constant.TWO * number * Constant.PI) * Math.pow(number / Constant.E, number), Constant.ZERO);
    }

    /*greatest common factor ----------------------------------------------------------- 求最大公约数*/

    /**
     * [求最大公约数](greatest common factor)
     * @description: zh - 输入两个数字，计算出他们的最大公约数。
     * @description: en - Enter two numbers and calculate their greatest common divisor.
     * @issue: zh - 把辗转相除和更相减损的优势结合，在更相减损的基础上使用位移运算。
     * @issue: en - Combining the advantages of tossing and dividing and more subtracting, using displacement calculation on the basis of more subtracting.
     * @version: V2.0
     * @author XiaoXunYao and dai1474446125
     * @since 2021/5/21 20:27
     * @param m: [求最大公约数的值1](Finding the value 1 of the greatest common divisor)
     * @param n: [求最大公约数的值2](Finding the value 2 of the greatest common divisor)
     * @return int
    */
    public static int divisor(int m,int n){
      if (n == m){ return n; }
      if ( ((n & Constant.ONE) == Constant.ZERO) && (( m & Constant.ONE ) == Constant.ZERO) ){
          return divisor(n >> Constant.ONE, m >> Constant.ONE) << Constant.ONE;
      }else if ( (( n & Constant.ONE ) == Constant.ZERO ) && ( (m & Constant.ONE) != Constant.ZERO) ){
          return divisor( n >> Constant.ONE, m );
      } else if ( ( ( n & Constant.ONE ) != Constant.ZERO ) && ( ( m & Constant.ONE ) == Constant.ZERO ) ){
          return divisor(n, m >> Constant.ONE);
      } else {
          int big = Math.max(n, m);
          int small = Math.min(n, m);
          return divisor(big - small, small);
      }
    }

    /*Finding the least common multiple ----------------------------------------------------------- 求最小公倍数*/

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

    /*Arabic numerals AND Roman numerals ----------------------------------------------------------- 阿拉伯数字 AND 罗马数字*/

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
        for (int i = Constant.ZERO; i < ARAB1.length; i++) {
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
        int sum = Constant.ZERO;
        int preNum = romanOneToInt(roman.charAt(Constant.ZERO));
        for (int i = Constant.ONE; i < roman.length(); i++) {
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

    /*Calculate the absolute value ----------------------------------------------------------- 计算绝对值*/

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
        return value < Constant.ZERO ? Constant.ZERO - value : value;
    }

    /*trigonometric function ----------------------------------------------------------- 三角函数*/

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
        angle = (angle * Constant.PI) / Constant.CIRCULAR;
        int i = Constant.ONE, sign = Constant.ONE;
        double item = angle, answer = Constant.ZERO, molecule = angle, denominator = Constant.ZERO;
        for(; absolute(item) >= Constant.CLEVER ; i += Constant.TWO){
            //累加
            answer += item;
            //分子
            molecule = molecule * angle * angle;
            //分母
            denominator = denominator * ( i + Constant.ONE ) * ( i + Constant.TWO );
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
        angle = (angle * Constant.PI) / Constant.CIRCULAR;
        int i = Constant.ZERO, sign = Constant.ONE;
        double item = Constant.ONE , answer = Constant.ZERO, molecule = Constant.ONE, denominator = Constant.ZERO ;
        for( ; absolute(item) >= Constant.CLEVER ; i += Constant.TWO){
            answer += item;
            molecule = molecule * angle * angle;
            denominator = denominator * ( i + Constant.ONE ) * ( i + Constant.TWO );
            sign = -sign;
            item = sign * ( molecule / denominator );
        }
        return answer;
    }

    /**
     * [计算该角度的正切值](Calculate the tangent of the angle)
     * @description: zh - 输入一个角度，计算出该角度的正切值。
     * @description: en - Enter an angle and calculate the tangent of the angle.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/20 6:56 下午
     * @param angle: [角度](angle)
     * @return double
    */
    public static double tangent(double angle){
        return sine(angle)/cosine(angle);
    }

    /*equal ----------------------------------------------------------- 比较大小*/


    /**
     * [比较两个数值是否相等](Compare two values for equality)
     * @description: zh - 比较两个数值是否相等
     * @description: en - Compare two values for equality
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/1 6:59 下午
     * @param value1: 比较的数值1
     * @param value2: 比较的数值2
     * @return boolean
     */
    public static boolean equals(double value1, double value2) {
        return Double.doubleToLongBits(value1) == Double.doubleToLongBits(value2);
    }

    /**
     * [比较两个数值是否相等](Compare two values for equality)
     * @description: zh - 比较两个数值是否相等
     * @description: en - Compare two values for equality
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/1 7:06 下午
     * @param value1: 比较的数值1
     * @param value2: 比较的数值2
     * @return boolean
     */
    public static boolean equals(float value1, float value2) {
        return Float.floatToIntBits(value1) == Float.floatToIntBits(value2);
    }

    /**
     * [比较大小，值相等 返回true](Compare the size, the value is equal, return true)
     * @description: zh - 比较大小，值相等 返回true
     * @description: en - Compare the size, the value is equal, return true
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 11:08 上午
     * @param value1: 数字1
     * @param value2: 数字2
     * @return boolean
    */
    public static boolean equals(BigDecimal value1, BigDecimal value2) {
        if (value1.equals(value2)) {
            // 如果用户传入同一对象，省略compareTo以提高性能。
            return Constant.TRUE;
        }
        if (value1 == Constant.NULL || value2 == Constant.NULL) {
            return Constant.FALSE;
        }
        return Constant.ZERO == value1.compareTo(value2);
    }

    /**
     * [比较两个字符是否相同](Compare two characters to see if they are the same)
     * @description: zh - 比较两个字符是否相同
     * @description: en - Compare two characters to see if they are the same
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:10 上午
     * @param c1: 字符1
     * @param c2: 字符2
     * @param ignoreCase: 是否忽略大小写
     * @return boolean
    */
    public static boolean equals(char c1, char c2, boolean ignoreCase) {
        return CharUtil.equals(c1, c2, ignoreCase);
    }

    /*转换 ----------------------------------------------------------- parse*/

    /**
     * [将指定字符串转换为Number 对象](Converts the specified string to a number object)
     * @description: zh - 将指定字符串转换为Number 对象
     * @description: en - Converts the specified string to a number object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:40 下午
     * @param numberStr: Number字符串
     * @return java.lang.Number
    */
    public static Number parseNumber(String numberStr) throws NumberFormatException {
        try {
            return NumberFormat.getInstance().parse(numberStr);
        } catch (ParseException e) {
            final NumberFormatException nfe = new NumberFormatException(e.getMessage());
            nfe.initCause(e);
            throw nfe;
        }
    }

    /**
     * [解析转换数字字符串为 float 型数字](Parsing and converting numeric string to float numeric string)
     * @description: zh - 解析转换数字字符串为 float 型数字
     * @description: en - Parsing and converting numeric string to float numeric string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:45 下午
     * @param number: 数字，支持0x开头、0开头和普通十进制
     * @return float
    */
    public static float parseFloat(String number) {
        if (StrUtil.isBlank(number)) { return Constant.FLOAT_ZERO; }
        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException e) {
            return parseNumber(number).floatValue();
        }
    }

    /**
     * [解析转换数字字符串为long型数字](Parsing and converting numeric string to long numeric string)
     * @description: zh - 解析转换数字字符串为double型数字
     * @description: en - Parsing and converting numeric string to double numeric string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:47 下午
     * @param number: 数字，支持0x开头、0开头和普通十进制
     * @return double
    */
    public static double parseDouble(String number) {
        if (StrUtil.isBlank(number)) {
            return Constant.DOUBLE_ZERO;
        }
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return parseNumber(number).doubleValue();
        }
    }

    /**
     * [解析转换数字字符串为int型数字](Analysis and conversion of numeric string to int numeric string)
     * @description: zh - 解析转换数字字符串为int型数字
     * @description: en - Analysis and conversion of numeric string to int numeric string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:56 下午
     * @param number: 数字，支持0x开头、0开头和普通十进制
     * @return int
    */
    public static int parseInt(String number) throws NumberFormatException {
        if (StrUtil.isBlank(number)) {
            return Constant.ZERO;
        }
        if (StrUtil.startWithIgnoreCase(number, Constant.STRING_ZERO_X)) {
            // 0x04表示16进制数
            return Integer.parseInt(number.substring(Constant.TWO), Constant.SIXTEEN);
        }
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return parseNumber(number).intValue();
        }
    }

    /**
     * [解析转换数字字符串为long型数字](Parsing and converting numeric string to long numeric string)
     * @description: zh - 解析转换数字字符串为long型数字
     * @description: en - Parsing and converting numeric string to long numeric string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 11:04 上午
     * @param number: 数字，支持0x开头、0开头和普通十进制
     * @return long
    */
    public static long parseLong(String number) {
        if (StrUtil.isBlank(number)) {
            return Constant.LONG_ZERO;
        }
        if (number.startsWith(Constant.STRING_ZERO_X)) {
            // 0x04表示16进制数
            return Long.parseLong(number.substring(Constant.TWO), Constant.SIXTEEN);
        }
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            return parseNumber(number).longValue();
        }
    }

    /**
     * [是否为数字](Is it a number)
     * @description: zh - 是否为数字
     * @description: en - Is it a number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 9:10 上午
     * @param str: 字符串值
     * @return boolean
    */
    public static boolean isNumber(CharSequence str) {
        if (StrUtil.isBlank(str)) { return Constant.FALSE; }
        char[] chars = str.toString().toCharArray();
        int sz = chars.length;
        boolean hasExp = Constant.FALSE;
        boolean hasDecPoint = Constant.FALSE;
        boolean allowSigns = Constant.FALSE;
        boolean foundDigit = Constant.FALSE;
        // 提前处理大部分的可能性
        int start = (chars[Constant.ZERO] == Constant.CHAR_DASH || chars[Constant.ZERO] == Constant.CHAR_PLUS) ? Constant.ONE : Constant.ZERO;
        if (sz > start + Constant.ONE) {
            if (chars[start] == Constant.CHAR_ZERO && (chars[start + Constant.ONE] == Constant.CHAR_DOWN_X || chars[start + Constant.ONE] == Constant.CHAR_UP_X)) {
                int i = start + Constant.TWO;
                if (i == sz) {
                    return Constant.FALSE;
                }
                // 检查十六进制（不能是其他任何东西）
                for (; i < chars.length; i++) {
                    if ((chars[i] < Constant.CHAR_ZERO || chars[i] > Constant.CHAR_NINE) && (chars[i] < Constant.CHAR_DOWN_A || chars[i] > Constant.CHAR_DOWN_F) && (chars[i] < Constant.CHAR_UP_A || chars[i] > Constant.CHAR_UP_F)) {
                        return Constant.FALSE;
                    }
                }
                return Constant.TRUE;
            }
        }
        // 不想循环到最后一个字符，请在后面检查
        sz--;
        // 对于类型限定符
        int i = start;
        // 循环到下一个到最后一个字符，或者如果需要另一个数字，则循环到最后一个字符,生成有效数字
        while (i < sz || (i < sz + Constant.ONE && allowSigns && !foundDigit)) {
            if (chars[i] >= Constant.CHAR_ZERO && chars[i] <= Constant.CHAR_NINE) {
                foundDigit = Constant.TRUE;
                allowSigns = Constant.FALSE;

            } else if (chars[i] == Constant.CHAR_SPOT) {
                if (hasDecPoint || hasExp) {
                    // 两个小数点或指数小数点
                    return Constant.FALSE;
                }
                hasDecPoint = Constant.TRUE;
            } else if (chars[i] == Constant.CHAR_DOWN_E || chars[i] == Constant.CHAR_UP_E) {
                // 处理完成
                if (hasExp) {
                    // 产生了两个E
                    return Constant.FALSE;
                }
                if (!foundDigit) {
                    return Constant.FALSE;
                }
                hasExp = Constant.TRUE;
                allowSigns = Constant.TRUE;
            } else if (chars[i] == Constant.CHAR_PLUS || chars[i] == Constant.CHAR_DASH) {
                if (!allowSigns) {
                    return Constant.FALSE;
                }
                allowSigns = Constant.FALSE;
                // 我们需要在E后面加一个数字
                foundDigit = Constant.FALSE;
            } else {
                return Constant.FALSE;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= Constant.CHAR_ZERO && chars[i] <= Constant.CHAR_NINE) {
                // 没有类型限定符
                return Constant.TRUE;
            }
            if (chars[i] == Constant.CHAR_DOWN_E || chars[i] == Constant.CHAR_UP_E) {
                // 最后一个字节不能有E
                return Constant.FALSE;
            }
            if (chars[i] == Constant.CHAR_SPOT) {
                if (hasDecPoint || hasExp) {
                    // 两个小数点或指数小数点
                    return Constant.FALSE;
                }
                // 非指数后的单个尾随小数点是可以的
                return foundDigit;
            }
            if (!allowSigns && (chars[i] == Constant.CHAR_DOWN_D || chars[i] == Constant.CHAR_UP_D || chars[i] == Constant.CHAR_DOWN_F || chars[i] == Constant.CHAR_UP_F)) {
                return foundDigit;
            }
            if (chars[i] == Constant.CHAR_DOWN_L || chars[i] == Constant.CHAR_UP_L) {
                // 不允许带指数的L
                return foundDigit && !hasExp;
            }
            // 最后一个字符是非法的
            return Constant.FALSE;
        }
        // 如果val以“E”结尾，则allowSigns为真,同时确保是数字，不会产生奇怪的东西。
        return !allowSigns && foundDigit;
    }

    /*生成XXX ----------------------------------------------------------- generateXXX*/

    /**
     * [生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组](Generate a non repeating random number to generate a specified non repeating array according to the given minimum number, maximum number and the number of random numbers)
     * @description: zh - 生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组
     * @description: en - Generate a non repeating random number to generate a specified non repeating array according to the given minimum number, maximum number and the number of random numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/27 5:38 下午
     * @param begin: 最小数字（包含该数）
     * @param end: 最大数字（不包含该数）
     * @param size: 指定产生随机数的个数
     * @return int[]
    */
    public static int[] generateRandomNumber(int begin, int end, int size) {
        // 种子你可以随意生成，但不能重复
        final int[] seed = ArrayUtil.range(begin, end);
        return generateRandomNumber(begin, end, size, seed);
    }

    /**
     * [生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组](Generate a non repeating random number to generate a specified non repeating array according to the given minimum number, maximum number and the number of random numbers)
     * @description: zh - 生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组
     * @description: en - Generate a non repeating random number to generate a specified non repeating array according to the given minimum number, maximum number and the number of random numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/27 5:37 下午
     * @param begin: 最小数字（包含该数）
     * @param end: 最大数字（不包含该数）
     * @param size: 指定产生随机数的个数
     * @param seed: 种子，用于取随机数的int池
     * @return int[]
    */
    public static int[] generateRandomNumber(int begin, int end, int size, int[] seed) {
        if (begin > end) {
            int temp = begin;
            begin = end;
            end = temp;
        }
        // 加入逻辑判断，确保begin<end并且size不能大于该表示范围
        Assertion.isTrue((end - begin) >= size, "Size is larger than range between begin and end!");
        Assertion.isTrue(seed.length >= size, "Size is larger than seed size!");

        final int[] ranArr = new int[size];
        // 数量你可以自己定义。
        for (int i = Constant.ZERO; i < size; i++) {
            // 得到一个位置
            int j = RandomUtil.randomInt(seed.length - i);
            // 得到那个位置的数值
            ranArr[i] = seed[j];
            // 将最后一个未用的数字放到这里
            seed[j] = seed[seed.length - Constant.ONE - i];
        }
        return ranArr;
    }

    /**
     * [生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组](Generate a non repeating random number to generate a specified non repeating array according to the given minimum number, maximum number and the number of random numbers)
     * @description: zh - 生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组
     * @description: en - Generate a non repeating random number to generate a specified non repeating array according to the given minimum number, maximum number and the number of random numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/27 5:41 下午
     * @param begin: 最小数字（包含该数）
     * @param end: 最大数字（不包含该数）
     * @param size: 指定产生随机数的个数
     * @return java.lang.Integer[]
    */
    public static Integer[] generateBySet(int begin, int end, int size) {
        if (begin > end) {
            int temp = begin;
            begin = end;
            end = temp;
        }
        // 加入逻辑判断，确保begin<end并且size不能大于该表示范围
        if ((end - begin) < size) {
            throw new UtilException("The size is greater than the range between the beginning and the end");
        }

        Random ran = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < size) {
            set.add(begin + ran.nextInt(end - begin));
        }

        return set.toArray(new Integer[size]);
    }

    /*范围 ----------------------------------------------------------- range*/

    /**
     * [从0开始给定范围内的整数列表，步进为1](A list of integers in the given range starting from 0 in steps of 1)
     * @description: zh - 从0开始给定范围内的整数列表，步进为1
     * @description: en - A list of integers in the given range starting from 0 in steps of 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/27 7:21 下午
     * @param stop: 结束（包含）
     * @return int[]
    */
    public static int[] range(int stop) {
        return range(Constant.ZERO, stop);
    }

    /**
     * [给定范围内的整数列表，步进为1](A list of integers within a given range in steps of 1)
     * @description: zh - 给定范围内的整数列表，步进为1
     * @description: en - A list of integers within a given range in steps of 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/27 7:22 下午
     * @param start: 开始（包含）
     * @param stop: 结束（包含）
     * @return int[]
    */
    public static int[] range(int start, int stop) {
        return range(start, stop, Constant.ONE);
    }

    /**
     * [给定范围内的整数列表](List of integers in the given range)
     * @description: zh - 给定范围内的整数列表
     * @description: en - List of integers in the given range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/27 7:27 下午
     * @param start: 开始（包含）
     * @param stop: 结束（包含）
     * @param step: 步进
     * @return int[]
    */
    public static int[] range(int start, int stop, int step) {
        if (start < stop) {
            step = Math.abs(step);
        } else if (start > stop) {
            step = -Math.abs(step);
        } else {
            // start == end 的情况
            return new int[]{start};
        }

        int size = Math.abs((stop - start) / step) + Constant.ONE;
        int[] values = new int[size];
        int index = Constant.ZERO;
        for (int i = start; (step > Constant.ZERO) ? i <= stop : i >= stop; i += step) {
            values[index] = i;
            index++;
        }
        return values;
    }

    /**
     * [将给定范围内的整数添加到已有集合中，步进为1](Adds an integer in the given range to an existing set in steps of 1)
     * @description: zh - 将给定范围内的整数添加到已有集合中，步进为1
     * @description: en - Adds an integer in the given range to an existing set in steps of 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/27 7:35 下午
     * @param start: 开始（包含）
     * @param stop: 结束（包含）
     * @param values: 集合
     * @return java.util.Collection<java.lang.Integer>
    */
    public static Collection<Integer> appendRange(int start, int stop, Collection<Integer> values) {
        return appendRange(start, stop, Constant.ONE, values);
    }

    /**
     * [将给定范围内的整数添加到已有集合中](Adds an integer in the given range to an existing set)
     * @description: zh - 将给定范围内的整数添加到已有集合中
     * @description: en - Adds an integer in the given range to an existing set
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/27 7:36 下午
     * @param start: 开始（包含）
     * @param stop: 结束（包含）
     * @param step: 步进
     * @param values: 集合
     * @return java.util.Collection<java.lang.Integer>
    */
    public static Collection<Integer> appendRange(int start, int stop, int step, Collection<Integer> values) {
        if (start < stop) {
            step = Math.abs(step);
        } else if (start > stop) {
            step = -Math.abs(step);
        } else {
            // start == end 的情况
            values.add(start);
            return values;
        }

        for (int i = start; (step > Constant.ZERO) ? i <= stop : i >= stop; i += step) {
            values.add(i);
        }
        return values;
    }

    /*factorial ----------------------------------------------------------- 阶乘方法*/

    /**
     * [计算阶乘](Calculate factorial)
     * @description: zh - 计算阶乘
     * @description: en - Calculate factorial
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/28 3:29 下午
     * @param n: 阶乘起始
     * @return java.math.BigInteger
    */
    public static BigInteger factorial(BigInteger n) {
        return n.equals(BigInteger.ZERO) ? BigInteger.ONE : factorial(n, BigInteger.ZERO);
    }

    /**
     * [计算范围阶乘](Calculation range factorial)
     * @description: zh - 计算范围阶乘
     * @description: en - Calculation range factorial
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/28 3:30 下午
     * @param start: 阶乘起始（包含）
     * @param end: 阶乘结束，必须小于起始（不包括）
     * @return java.math.BigInteger
    */
    public static BigInteger factorial(BigInteger start, BigInteger end) {
        Assertion.notNull(start, "Factorial start must be not null!");
        Assertion.notNull(end, "Factorial end must be not null!");
        if(start.compareTo(BigInteger.ZERO) < Constant.ZERO || end.compareTo(BigInteger.ZERO) < Constant.ZERO){
            throw new IllegalArgumentException(StrUtil.format("Factorial start and end both must be > 0, but got start={}, end={}", start, end));
        }

        if (start.equals(BigInteger.ZERO)){
            start = BigInteger.ONE;
        }

        if(end.compareTo(BigInteger.ONE) < Constant.ZERO){
            end = BigInteger.ONE;
        }

        BigInteger result = start;
        end = end.add(BigInteger.ONE);
        while(start.compareTo(end) > Constant.ZERO) {
            start = start.subtract(BigInteger.ONE);
            result = result.multiply(start);
        }
        return result;
    }

    /**
     * [计算范围阶乘](Calculation range factorial)
     * @description: zh - 计算范围阶乘
     * @description: en - Calculation range factorial
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/28 3:53 下午
     * @param start: 阶乘起始（包含）
     * @param end: 阶乘结束，必须小于起始（不包括）
     * @return long
    */
    public static long factorial(long start, long end) {
        // 负数没有阶乘
        if (start < Constant.ZERO || end < Constant.ZERO) {
            throw new IllegalArgumentException(StrUtil.format("Factorial start and end both must be >= 0, but got start={}, end={}", start, end));
        }
        return Constant.LONG_ZERO == start || start == end ? Constant.LONG_ONE :
                start < end ? Constant.LONG_ZERO :
                    factorialMultiplyAndCheck(start, factorial(start - Constant.ONE, end));
    }

    /**
     * [计算阶乘](Calculate factorial)
     * @description: zh - 计算阶乘
     * @description: en - Calculate factorial
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/29 10:24 上午
     * @param value: 阶乘起始
     * @return long
    */
    public static long factorial(long value) {
        if (value < Constant.ZERO || value > Constant.TWENTY) {
            throw new IllegalArgumentException(StrUtil.format("Factorial must have n >= 0 and n <= 20 for n!, but got n = {}", value));
        }
        return FACTORIALS[(int) value];
    }

    /*square root ----------------------------------------------------------- 平方根*/

    /**
     * [平方根算法](Square root algorithm)
     * @description: zh - 平方根算法
     * @description: en - Square root algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/29 10:53 上午
     * @param value: 值
     * @return long
    */
    public static long sqrt(long value) {
        long result = Constant.ZERO;
        long bit = (~Long.MAX_VALUE) >>> Constant.ONE;
        while (bit > Constant.ZERO) {
            if (value >= result + bit) {
                value -= result + bit;
                result >>= Constant.ONE;
                result += bit;
            } else {
                result >>= Constant.ONE;
            }
            bit >>= Constant.TWO;
        }
        return result;
    }

    /*max AND min ----------------------------------------------------------- 比较数字的大小*/

    /**
     * [比较两个数字谁大](Compare the two numbers. Who is bigger)
     * @description: zh - 比较两个数字谁大
     * @description: en - Compare the two numbers. Who is bigger
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/29 1:48 下午
     * @param a: 比较的数字1
     * @param b: 比较的数字2
     * @return int
    */
    public static int max(int a,int b){
        return (a >= b) ? a : b;
    }

    /**
     * [比较两个数字谁大](Compare the two numbers. Who is bigger)
     * @description: zh - 比较两个数字谁大
     * @description: en - Compare the two numbers. Who is bigger
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/29 1:48 下午
     * @param a: 比较的数字1
     * @param b: 比较的数字2
     * @return long
    */
    public static long max(long a,long b){
        return (a >= b) ? a : b;
    }

    /**
     * [比较两个数字谁大](Compare the two numbers. Who is bigger)
     * @description: zh - 比较两个数字谁大
     * @description: en - Compare the two numbers. Who is bigger
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/29 2:06 下午
     * @param a: 比较的数字1
     * @param b: 比较的数字2
     * @return float
    */
    public static float max(float a, float b) {
        return a != a ? a :
                (a == Constant.FLOAT_DOUBLE_ZERO) &&
                        (b == Constant.FLOAT_DOUBLE_ZERO) &&
                        (Float.floatToRawIntBits(a) == Float.floatToRawIntBits(Constant.FLOAT_DOUBLE_NEGATIVE_ZERO)) ? b :
                            (a >= b) ? a : b;
    }

    /**
     * [比较两个数字谁大](Compare the two numbers. Who is bigger)
     * @description: zh - 比较两个数字谁大
     * @description: en - Compare the two numbers. Who is bigger
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/29 2:08 下午
     * @param a: 比较的数字1
     * @param b: 比较的数字2
     * @return double
    */
    public static double max(double a, double b) {
        return a != a ? a :
                (a == Constant.DOUBLE_DOUBLE_ZERO) &&
                        (b == Constant.DOUBLE_DOUBLE_ZERO) &&
                        (Double.doubleToRawLongBits(a) == Double.doubleToRawLongBits(Constant.DOUBLE_DOUBLE_NEGATIVE_ZERO)) ? b :
                        (a >= b) ? a : b;
    }

    /**
     * [比较两个数字谁小](Compare the two numbers. Who is the smaller)
     * @description: zh - 比较两个数字谁小
     * @description: en - Compare the two numbers. Who is the smaller
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/31 5:46 下午
     * @param a: 比较的数字1
     * @param b: 比较的数字2
     * @return int
    */
    public static int min(int a,int b){
        return (a <= b) ? a : b;
    }

    /**
     * [比较两个数字谁小](Compare the two numbers. Who is the smaller)
     * @description: zh - 比较两个数字谁小
     * @description: en - Compare the two numbers. Who is the smaller
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/31 5:48 下午
     * @param a: 比较的数字1
     * @param b: 比较的数字2
     * @return long
    */
    public static long min (long a, long b){
        return (a <= b) ? a : b;
    }

    /**
     * [比较两个数字谁小](Compare the two numbers. Who is the smaller)
     * @description: zh - 比较两个数字谁小
     * @description: en - Compare the two numbers. Who is the smaller
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/31 5:55 下午
     * @param a: 比较的数字1
     * @param b: 比较的数字2
     * @return float
    */
    public static float min(float a, float b){
        return a != a ? a :
                (a == Constant.FLOAT_DOUBLE_ZERO) &&
                        (b == Constant.FLOAT_DOUBLE_ZERO) &&
                        (Float.floatToRawIntBits(b) == Float.floatToRawIntBits(Constant.FLOAT_DOUBLE_NEGATIVE_ZERO)) ? b :
                            (a <= b) ? a : b;
    }

    /**
     * [比较两个数字谁小](Compare the two numbers. Who is the smaller)
     * @description: zh - 比较两个数字谁小
     * @description: en - Compare the two numbers. Who is the smaller
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/31 5:58 下午
     * @param a: 比较的数字1
     * @param b: 比较的数字2
     * @return double
    */
    public static double min(double a, double b){
        return a != a ? a :
                (a == Constant.DOUBLE_DOUBLE_NEGATIVE_ZERO) &&
                        (b == Constant.DOUBLE_DOUBLE_NEGATIVE_ZERO) &&
                        (Double.doubleToRawLongBits(b) == Double.doubleToRawLongBits(Constant.DOUBLE_DOUBLE_NEGATIVE_ZERO)) ? b :
                            (a <= b) ? a : b;
    }

    /*二进制转XXX ----------------------------------------------------------- binaryToXXX*/

    /**
     * [获得数字对应的二进制字符串](Get the binary string corresponding to the number)
     * @description: zh - 获得数字对应的二进制字符串
     * @description: en - Get the binary string corresponding to the number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/1 3:56 下午
     * @param number: 数字
     * @return java.lang.String
    */
    public static String getBinaryStr(Number number) {
        return number instanceof Long ? Long.toBinaryString((Long) number) :
                number instanceof Integer ? Integer.toBinaryString((Integer) number):
                        Long.toBinaryString(number.longValue());
    }

    /**
     * [二进制转int](Binary to int)
     * @description: zh - 二进制转int
     * @description: en - Binary to int
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/1 3:52 下午
     * @param value: 二进制字符串
     * @return int
    */
    public static int binaryToInt(String value) {
        return Integer.parseInt(value, Constant.TWO);
    }

    /**
     * [二进制转long](Binary to long)
     * @description: zh - 二进制转long
     * @description: en - Binary to long
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/1 3:55 下午
     * @param value: 二进制字符串
     * @return long
    */
    public static long binaryToLong(String value) {
        return Long.parseLong(value, Constant.TWO);
    }

    /*转换成为字符串 ----------------------------------------------------------- to string*/

    /**
     * [数字转字符串](Number to String)
     * @description: zh - 数字转字符串
     * @description: en - Number to String
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/1 7:35 下午
     * @param value: 数字
     * @param defaultValue: 如果 value 参数为null，返回此默认值
     * @return java.lang.String
    */
    public static String toStr(Number value, String defaultValue) {
        return (Constant.NULL == value) ? defaultValue : toStr(value);
    }

    /**
     * [数字转字符串](Number to String)
     * @description: zh - 数字转字符串
     * @description: en - Number to String
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/1 7:39 下午
     * @param value: 数字
     * @return java.lang.String
    */
    public static String toStr(Number value) {
        return toStr(value, Constant.TRUE);
    }

    /**
     * [数字转字符串](Number to String)
     * @description: zh - 数字转字符串
     * @description: en - Number to String
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/1 7:49 下午
     * @param value: 数字
     * @param isStripTrailingZeros: 是否去除末尾多余0，例如5.0返回5
     * @return java.lang.String
    */
    public static String toStr(Number value, boolean isStripTrailingZeros) {
        Assertion.notNull(value, "Number is null !");

        // BigDecimal单独处理，使用非科学计数法
        if (value instanceof BigDecimal) {
            return toStr((BigDecimal) value, isStripTrailingZeros);
        }

        Assertion.isTrue(isValidNumber(value), "Number is non-finite!");
        // 去掉小数点儿后多余的0
        String string = value.toString();
        if (isStripTrailingZeros) {
            if (string.indexOf(Constant.CHAR_SPOT) > Constant.ZERO && string.indexOf(Constant.CHAR_DOWN_E) < Constant.ZERO && string.indexOf(Constant.CHAR_UP_E) < Constant.ZERO) {
                while (string.endsWith(Constant.STRING_ZERO)) {
                    string = string.substring(Constant.ZERO, string.length() - Constant.ONE);
                }
                if (string.endsWith(Constant.SPOT)) {
                    string = string.substring(Constant.ZERO, string.length() - Constant.ONE);
                }
            }
        }
        return string;
    }

    /*BigDecimal数字转字符串 ----------------------------------------------------------- BigDecimal to string*/

    /**
     * [BigDecimal数字转字符串](BigDecimal number to String)
     * @description: zh - BigDecimal数字转字符串
     * @description: en - BigDecimal number to String
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/4 1:10 下午
     * @param bigDecimal: a BigDecimal
     * @return java.lang.String
    */
    public static String toStr(BigDecimal bigDecimal) {
        return toStr(bigDecimal, Constant.TRUE);
    }

    /**
     * [BigDecimal数字转字符串](BigDecimal number to String)
     * @description: zh - BigDecimal数字转字符串
     * @description: en - BigDecimal number to String
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/4 1:11 下午
     * @param bigDecimal: a BigDecimal
     * @param isStripTrailingZeros: 是否去除末尾多余0
     * @return java.lang.String
    */
    public static String toStr(BigDecimal bigDecimal, boolean isStripTrailingZeros) {
        Assertion.notNull(bigDecimal, "BigDecimal is null !");
        return isStripTrailingZeros ? bigDecimal.stripTrailingZeros().toPlainString() : bigDecimal.toPlainString();
    }

    /*数字转BigDecimal ----------------------------------------------------------- number to BigDecimal*/

    /**
     * [数字转BigDecimal](number to BigDecimal)
     * @description: zh - 数字转BigDecimal
     * @description: en - number to BigDecimal
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/4 1:26 下午
     * @param number: 数字
     * @return java.math.BigDecimal
    */
    public static BigDecimal toBigDecimal(Number number) {
        return Constant.NULL == number ? BigDecimal.ZERO :
                number instanceof BigDecimal ? (BigDecimal) number :
                        number instanceof Long ? new BigDecimal((Long) number) :
                                number instanceof Integer ? new BigDecimal((Integer) number) :
                                        number instanceof BigInteger ? new BigDecimal((BigInteger) number) :
                                                toBigDecimal(number.toString());
    }

    /**
     * [数字转BigDecimal](number to BigDecimal)
     * @description: zh - 数字转BigDecimal
     * @description: en - number to BigDecimal
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/4 1:26 下午
     * @param number: 数字
     * @return java.math.BigDecimal
    */
    public static BigDecimal toBigDecimal(String number) {
        try {
            number = parseNumber(number).toString();
        } catch (Exception ignore) {
            // 忽略解析错误
        }
        return StrUtil.isBlank(number) ? BigDecimal.ZERO : new BigDecimal(number);
    }

    /**
     * [数字转BigDecimal](number to BigDecimal)
     * @description: zh - 数字转BigDecimal
     * @description: en - number to BigDecimal
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/4 1:31 下午
     * @param number: 数字
     * @return java.math.BigInteger
    */
    public static BigInteger toBigInteger(Number number) {
        return Constant.NULL == number ? BigInteger.ZERO :
                number instanceof BigInteger ? (BigInteger) number :
                        number instanceof Long ? BigInteger.valueOf((Long) number) :
                                toBigInteger(number.longValue());
    }

    /**
     * [数字转BigDecimal](number to BigDecimal)
     * @description: zh - 数字转BigDecimal
     * @description: en - number to BigDecimal
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/4 1:33 下午
     * @param number: 数字
     * @return java.math.BigInteger
    */
    public static BigInteger toBigInteger(String number) {
        return StrUtil.isBlank(number) ? BigInteger.ZERO : new BigInteger(number);
    }

    /*计算等份个数 ----------------------------------------------------------- Calculate the number of equal parts*/

    /**
     * [计算等份个数](Calculate the number of equal parts)
     * @description: zh - 计算等份个数
     * @description: en - Calculate the number of equal parts
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/4 1:40 下午
     * @param total: 总数
     * @param part: 每份的个数
     * @return int
    */
    public static int count(int total, int part) {
        return (total % part) == Constant.ZERO ? (total / part) : (total / part + Constant.ONE);
    }

    /*私有的方法 ----------------------------------------------------------- private*/

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
        for (int i = Constant.ZERO; i < ARAB2.length; i++) {
            if (value == ROMAN2[i]){
                return ARAB2[i];
            }
        }
        return Constant.ZERO;
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
            String value = values[Constant.ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);
            for(int i = Constant.ONE; i < values.length; ++i) {
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
            String value = values[Constant.ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = Constant.ONE; i < values.length; ++i) {
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
            String value = values[Constant.ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = Constant.ONE; i < values.length; ++i) {
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
            String value = values[Constant.ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = Constant.ONE; i < values.length; ++i) {
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
            String value = values[Constant.ZERO];
            BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);

            for(int i = Constant.ONE; i < values.length; ++i) {
                value = values[i];
                if (null != value) {
                    result = result.add(new BigDecimal(value));
                }
            }
            return result;
        }
    }

    /**
     * [计算范围阶乘中校验中间的计算是否存在溢出，factorial提前做了负数和0的校验，因此这里没有校验数字的正负](In the factorial of the calculation range, check whether there is overflow in the middle calculation. Factorial checks the negative number and 0 in advance, so there is no positive or negative number of the check number)
     * @description: zh - 计算范围阶乘中校验中间的计算是否存在溢出，factorial提前做了负数和0的校验，因此这里没有校验数字的正负
     * @description: en - In the factorial of the calculation range, check whether there is overflow in the middle calculation. Factorial checks the negative number and 0 in advance, so there is no positive or negative number of the check number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/29 10:18 上午
     * @param multiplier: 乘数
     * @param multiplicand: 被乘数
     * @return long
    */
    private static long factorialMultiplyAndCheck(long multiplier, long multiplicand) {
        if (multiplier <= Long.MAX_VALUE / multiplicand) {
            return multiplier * multiplicand;
        }
        throw new IllegalArgumentException(StrUtil.format("Overflow in multiplication: {} * {}", multiplier, multiplicand));
    }
}
