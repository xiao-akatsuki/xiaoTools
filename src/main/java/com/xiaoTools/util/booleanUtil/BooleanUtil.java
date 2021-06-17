package com.xiaoTools.util.booleanUtil;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.util.Set;

/**
 * [布尔值工具类](Boolean tool class)
 * @description: zh - 布尔值工具类
 * @description: en - Boolean tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 4:25 下午
*/
public class BooleanUtil {

    /**
     * 表示为真的字符串
     */
    private static final Set<String> TRUE_SET = CollUtil.newHashSet("true", "yes", "y", "t", "ok", "1", "on", "是", "对", "真", "對", "√");

    /**
     * [取反操作](Reverse operation)
     * @description: zh - 取反操作
     * @description: en - Reverse operation
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:40 下午
     * @param bool: 取相反值
     * @return java.lang.Boolean
    */
    public static Boolean negate(Boolean bool) {
        return bool == Constant.NULL ? Constant.BOOLEAN_NULL : bool ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * [取相反值](Take the opposite value)
     * @description: zh - 取相反值
     * @description: en - Take the opposite value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:47 下午
     * @param bool: Boolean值
     * @return boolean
    */
    public static boolean negate(boolean bool) {
        return !bool;
    }

    /**
     * [检查 Boolean 值是否为 true](Check whether the Boolean value is true)
     * @description: zh - 检查 Boolean 值是否为 true
     * @description: en - Check whether the Boolean value is true
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:42 下午
     * @param bool: 被检查的Boolean值
     * @return boolean
    */
    public static boolean isTrue(Boolean bool) {
        return Boolean.TRUE.equals(bool);
    }

    /**
     * [检查 Boolean 值是否为 false](Check whether the Boolean value is false)
     * @description: zh - 检查 Boolean 值是否为 false
     * @description: en - Check whether the Boolean value is false
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:45 下午
     * @param bool: 被检查的Boolean值
     * @return boolean
    */
    public static boolean isFalse(Boolean bool) {
        return Boolean.FALSE.equals(bool);
    }

    /**
     * [转换字符串为boolean值](Convert string to Boolean value)
     * @description: zh - 转换字符串为boolean值
     * @description: en - Convert string to Boolean value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:07 下午
     * @param valueStr: 字符串
     * @return boolean
    */
    public static boolean toBoolean(String valueStr) {
        if (StrUtil.isNotBlank(valueStr)) {
            valueStr = valueStr.trim().toLowerCase();
            return TRUE_SET.contains(valueStr);
        }
        return Constant.FALSE;
    }

    /**
     * [boolean值转为int](Boolean to int)
     * @description: zh - boolean值转为int
     * @description: en - Boolean to int
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:10 下午
     * @param value: Boolean值
     * @return int
    */
    public static int toInt(boolean value) {
        return value ? 1 : 0;
    }

    /**
     * [将Boolean值转换成为Integer](Convert Boolean value to integer)
     * @description: zh - 将Boolean值转换成为Integer
     * @description: en - Convert Boolean value to integer
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:20 下午
     * @param value: Boolean值
     * @return java.lang.Integer
    */
    public static Integer toInteger(boolean value) {
        return toInt(value);
    }

    /**
     * [boolean值转为char](The Boolean value is converted to char)
     * @description: zh - boolean值转为char
     * @description: en - The Boolean value is converted to char
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:20 下午
     * @param value: Boolean值
     * @return char
    */
    public static char toChar(boolean value) {
        return (char) toInt(value);
    }

    /**
     * [boolean值转为Character](Convert Boolean value to character)
     * @description: zh - boolean值转为Character
     * @description: en - Convert Boolean value to character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:21 下午
     * @param value: Boolean值
     * @return java.lang.Character
    */
    public static Character toCharacter(boolean value) {
        return toChar(value);
    }

    /**
     *
     * @description: zh - boolean值转为byte
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:21 下午
     * @param value:
     * @return byte
    */
    public static byte toByte(boolean value) {
        return (byte) toInt(value);
    }

    /**
     * [boolean值转为Byte](Convert Boolean value to byte)
     * @description: zh - boolean值转为Byte
     * @description: en - Convert Boolean value to byte
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:28 下午
     * @param value: Boolean值
     * @return java.lang.Byte
    */
    public static Byte toByteObj(boolean value) {
        return toByte(value);
    }

    /**
     * [boolean值转为long](The Boolean value is changed to long)
     * @description: zh - boolean值转为long
     * @description: en - The Boolean value is changed to long
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:18 下午
     * @param value: Boolean值
     * @return long
    */
    public static long toLong(boolean value) {
        return toInt(value);
    }

    /**
     * [boolean值转为Long](The Boolean value is changed to long)
     * @description: zh - boolean值转为Long
     * @description: en - The Boolean value is changed to long
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:17 下午
     * @param value: Boolean值
     * @return java.lang.Long
    */
    public static Long toLongObj(boolean value) {
        return toLong(value);
    }

    /**
     *
     * @description: zh - boolean值转为short
     * @description: en - The Boolean value is changed to short
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:15 下午
     * @param value: Boolean值
     * @return short
    */
    public static short toShort(boolean value) {
        return (short) toInt(value);
    }

    /**
     * [boolean值转为Short](The Boolean value is changed to short)
     * @description: zh - boolean值转为Short
     * @description: en - The Boolean value is changed to short
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:15 下午
     * @param value: Boolean值
     * @return java.lang.Short
    */
    public static Short toShortObj(boolean value) {
        return toShort(value);
    }

    /**
     * [boolean值转为float](The Boolean value is changed to float)
     * @description: zh - boolean值转为float
     * @description: en - The Boolean value is changed to float
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:13 下午
     * @param value: Boolean值
     * @return float
    */
    public static float toFloat(boolean value) {
        return (float) toInt(value);
    }

    /**
     *
     * @description: zh - boolean值转为Float
     * @description: en - The Boolean value is changed to float
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:13 下午
     * @param value: Boolean值
     * @return java.lang.Float
    */
    public static Float toFloatObj(boolean value) {
        return toFloat(value);
    }

    /**
     * [boolean值转为double](The Boolean value is changed to double)
     * @description: zh - boolean值转为double
     * @description: en - The Boolean value is changed to double
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:12 下午
     * @param value: Boolean值
     * @return double
    */
    public static double toDouble(boolean value) {
        return toInt(value);
    }

    /**
     * [boolean值转为double](The Boolean value is changed to double)
     * @description: zh - boolean值转为double
     * @description: en - The Boolean value is changed to double
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:11 下午
     * @param value: Boolean值
     * @return java.lang.Double
    */
    public static Double toDoubleObj(boolean value) {
        return toDouble(value);
    }

    /**
     * [将boolean转换为字符串 'true' 或者 'false'.](Convert Boolean to string 'true' or 'false')
     * @description: zh - 将boolean转换为字符串 'true' 或者 'false'.
     * @description: en - Convert Boolean to string 'true' or 'false'
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:38 下午
     * @param bool: Boolean值
     * @return java.lang.String
    */
    public static String toStringTrueFalse(boolean bool) {
        return toString(bool, Constant.STRING_TRUE, Constant.STRING_FALSE);
    }

    /**
     * [将boolean转换为字符串 'on' 或者 'off'.](Convert Boolean to string 'on' or 'off')
     * @description: zh - 将boolean转换为字符串 'on' 或者 'off'.
     * @description: en - Convert Boolean to string 'on' or 'off'
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:41 下午
     * @param bool: Boolean值
     * @return java.lang.String
    */
    public static String toStringOnOff(boolean bool) {
        return toString(bool, Constant.STRING_ON, Constant.STRING_OFF);
    }

    /**
     * [将boolean转换为字符串 'yes' 或者 'no'.](Convert Boolean to string 'yes' or' no ')
     * @description: zh - 将boolean转换为字符串 'yes' 或者 'no'.
     * @description: en - Convert Boolean to string 'yes' or' no '
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:45 下午
     * @param bool: Boolean值
     * @return java.lang.String
    */
    public static String toStringYesNo(boolean bool) {
        return toString(bool, Constant.STRING_YES, Constant.STRING_NO);
    }

    /**
     * [将boolean转换为字符串](Convert Boolean to string)
     * @description: zh - 将boolean转换为字符串
     * @description: en - Convert Boolean to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 5:32 下午
     * @param bool: Boolean值
     * @param trueString: 当值为 true时返回此字符串, 可能为 null
     * @param falseString: 当值为 false时返回此字符串, 可能为 null
     * @return java.lang.String
    */
    public static String toString(boolean bool, String trueString, String falseString) {
        return bool ? trueString : falseString;
    }

    /**
     * [对于数组的进行一个取与](For the array of an and)
     * @description: zh - 对于数组的进行一个取与
     * @description: en - For the array of an and
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 6:46 下午
     * @param array: Boolean数组
     * @return boolean
    */
    public static boolean takeAnd(boolean... array) {
        if (ArrayUtil.isEmpty(array)) {
            throw new IllegalArgumentException("The Array must not be empty !");
        }
        for (final boolean element : array) {
            if (!element) {
                return Constant.FALSE;
            }
        }
        return Constant.TRUE;
    }

    /**
     * [对Boolean数组取与](The sum of Boolean array)
     * @description: zh - 对Boolean数组取与
     * @description: en - The sum of Boolean array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:31 下午
     * @param array: Boolean数组
     * @return java.lang.Boolean
    */
    public static Boolean andOfWrap(Boolean... array) {
        if (ArrayUtil.isEmpty(array)) {
            throw new IllegalArgumentException("The Array must not be empty !");
        }
        final boolean[] primitive = Convert.convert(boolean[].class, array);
        return takeAnd(primitive);
    }

    /**
     * [对Boolean数组取或](Take or for Boolean array)
     * @description: zh - 对Boolean数组取或
     * @description: en - Take or for Boolean array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:33 下午
     * @param array: Boolean数组
     * @return boolean
    */
    public static boolean or(boolean... array) {
        if (ArrayUtil.isEmpty(array)) {
            throw new IllegalArgumentException("The Array must not be empty !");
        }
        for (final boolean element : array) {
            if (element) {
                return Constant.TRUE;
            }
        }
        return Constant.FALSE;
    }

    /**
     * [对Boolean数组取或](Take or for Boolean array)
     * @description: zh - 对Boolean数组取或
     * @description: en - Take or for Boolean array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:34 下午
     * @param array: Boolean数组
     * @return java.lang.Boolean
    */
    public static Boolean orOfWrap(Boolean... array) {
        if (ArrayUtil.isEmpty(array)) {
            throw new IllegalArgumentException("The Array must not be empty !");
        }
        final boolean[] primitive = Convert.convert(boolean[].class, array);
        return or(primitive);
    }

    /**
     * [对Boolean数组取异或](XOR Boolean array)
     * @description: zh - 对Boolean数组取异或
     * @description: en - XOR Boolean array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:36 下午
     * @param array: Boolean数组
     * @return boolean
    */
    public static boolean xor(boolean... array) {
        if (ArrayUtil.isEmpty(array)) {
            throw new IllegalArgumentException("The Array must not be empty");
        }
        boolean result = Constant.FALSE;
        for (final boolean element : array) {
            result ^= element;
        }
        return result;
    }

    /**
     * [对Boolean数组取异或](XOR Boolean array)
     * @description: zh - 对Boolean数组取异或
     * @description: en - XOR Boolean array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:37 下午
     * @param array: Boolean数组
     * @return java.lang.Boolean
    */
    public static Boolean xorOfWrap(Boolean... array) {
        if (ArrayUtil.isEmpty(array)) {
            throw new IllegalArgumentException("The Array must not be empty !");
        }
        final boolean[] primitive = Convert.convert(boolean[].class, array);
        return xor(primitive);
    }

    /**
     * [给定类是否为Boolean或者boolean](Whether the given class is Boolean or Boolean)
     * @description: zh - 给定类是否为Boolean或者boolean
     * @description: en - Whether the given class is Boolean or Boolean
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:37 下午
     * @param clazz: 类
     * @return boolean
    */
    public static boolean isBoolean(Class<?> clazz) {
        return (clazz == Boolean.class || clazz == boolean.class);
    }
}
