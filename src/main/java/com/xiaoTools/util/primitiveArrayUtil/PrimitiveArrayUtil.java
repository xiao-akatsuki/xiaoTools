package com.xiaoTools.util.primitiveArrayUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.randomUtil.RandomUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * [原始类型数组工具类](Primitive type array utility class)
 * @description: zh - 原始类型数组工具类
 * @description: en - Primitive type array utility class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/8/10 4:53 下午
*/
public class PrimitiveArrayUtil {

    /* 数组是否为空 ------------------------------------------------------------------------------- Is Empty */

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:00 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(long[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:01 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(int[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:08 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(char[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:09 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(short[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:10 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(byte[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:10 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(double[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:11 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(float[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:11 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(boolean[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /* 数组是否为非空 ------------------------------------------------------------------------------- Is Not Empty */

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:03 下午
     * @param array: 数组
     * @return boolean
     */
    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:02 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:04 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:05 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:05 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:05 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:05 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:06 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

    /* 重新设置大小的数组 ------------------------------------------------------------------------------- resize*/

    /**
     * [生成一个新的重新设置大小的数组](Generates a new resized array)
     * @description: zh - 生成一个新的重新设置大小的数组
     * @description: en - Generates a new resized array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:16 下午
     * @param bytes: 原数组
     * @param newSize: 新的数组大小
     * @return byte[]
    */
    public static byte[] resize(byte[] bytes, int newSize) {
        if (newSize < Constant.ZERO) {
            return bytes;
        }
        final byte[] newArray = new byte[newSize];
        if (newSize > Constant.ZERO && isNotEmpty(bytes)) {
            System.arraycopy(bytes, Constant.ZERO, newArray, Constant.ZERO, Math.min(bytes.length, newSize));
        }
        return newArray;
    }

    /* 多个数组合并在一起 ------------------------------------------------------------------------------- Add All*/

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merge multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:49 下午
     * @param arrays: 数组
     * @return byte[]
    */
    public static byte[] addAll(byte[]... arrays) {
        if (arrays.length == Constant.ONE) {
            return arrays[Constant.ZERO];
        }
        // 计算总长度
        int length = Constant.ZERO;
        for (byte[] array : arrays) {
            if (Constant.NULL != array) {
                length += array.length;
            }
        }
        final byte[] result = new byte[length];
        length = Constant.ZERO;
        for (byte[] array : arrays) {
            if (Constant.NULL != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merge multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:52 下午
     * @param arrays: 数组
     * @return int[]
    */
    public static int[] addAll(int[]... arrays) {
        if (arrays.length == Constant.ONE) {
            return arrays[Constant.ZERO];
        }

        // 计算总长度
        int length = Constant.ZERO;
        for (int[] array : arrays) {
            if (Constant.NULL != array) {
                length += array.length;
            }
        }

        final int[] result = new int[length];
        length = Constant.ZERO;
        for (int[] array : arrays) {
            if (Constant.NULL != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merge multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:54 下午
     * @param arrays: 数组
     * @return long[]
    */
    public static long[] addAll(long[]... arrays) {
        if (arrays.length == Constant.ONE) {
            return arrays[Constant.ZERO];
        }

        // 计算总长度
        int length = Constant.ZERO;
        for (long[] array : arrays) {
            if (Constant.NULL != array) {
                length += array.length;
            }
        }

        final long[] result = new long[length];
        length = Constant.ZERO;
        for (long[] array : arrays) {
            if (Constant.NULL != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merge multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:57 下午
     * @param arrays: 数组
     * @return double[]
    */
    public static double[] addAll(double[]... arrays) {
        if (arrays.length == Constant.ONE) {
            return arrays[Constant.ZERO];
        }

        // 计算总长度
        int length = Constant.ZERO;
        for (double[] array : arrays) {
            if (Constant.NULL != array) {
                length += array.length;
            }
        }

        final double[] result = new double[length];
        length = Constant.ZERO;
        for (double[] array : arrays) {
            if (Constant.NULL != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merge multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 9:03 下午
     * @param arrays: 数组
     * @return float[]
    */
    public static float[] addAll(float[]... arrays) {
        if (arrays.length == Constant.ONE) {
            return arrays[Constant.ZERO];
        }

        // 计算总长度
        int length = Constant.ZERO;
        for (float[] array : arrays) {
            if (Constant.NULL != array) {
                length += array.length;
            }
        }

        final float[] result = new float[length];
        length = Constant.ZERO;
        for (float[] array : arrays) {
            if (Constant.NULL != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merge multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 9:04 下午
     * @param arrays: 数组
     * @return char[]
    */
    public static char[] addAll(char[]... arrays) {
        if (arrays.length == Constant.ONE) {
            return arrays[Constant.ZERO];
        }

        // 计算总长度
        int length = Constant.ZERO;
        for (char[] array : arrays) {
            if (Constant.NULL != array) {
                length += array.length;
            }
        }

        final char[] result = new char[length];
        length = Constant.ZERO;
        for (char[] array : arrays) {
            if (Constant.NULL != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merge multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 9:05 下午
     * @param arrays: 数组
     * @return boolean[]
    */
    public static boolean[] addAll(boolean[]... arrays) {
        if (arrays.length == Constant.ONE) {
            return arrays[Constant.ZERO];
        }

        // 计算总长度
        int length = Constant.ZERO;
        for (boolean[] array : arrays) {
            if (null != array) {
                length += array.length;
            }
        }

        final boolean[] result = new boolean[length];
        length = Constant.ZERO;
        for (boolean[] array : arrays) {
            if (null != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merge multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 9:07 下午
     * @param arrays: 数组
     * @return short[]
    */
    public static short[] addAll(short[]... arrays) {
        if (arrays.length == Constant.ONE) {
            return arrays[Constant.ZERO];
        }

        // 计算总长度
        int length = Constant.ZERO;
        for (short[] array : arrays) {
            if (Constant.NULL != array) {
                length += array.length;
            }
        }

        final short[] result = new short[length];
        length = Constant.ZERO;
        for (short[] array : arrays) {
            if (Constant.NULL != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /* 生成一个数字列表 ------------------------------------------------------------------------------- range*/

    /**
     * [生成一个从0开始的数字列表](Generate a list of numbers starting from 0)
     * @description: zh - 生成一个从0开始的数字列表
     * @description: en - Generate a list of numbers starting from 0
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/13 1:24 下午
     * @param value: 结束的数字（不包含）
     * @return int[]
    */
    public static int[] range(int value) {
        return range(Constant.ZERO, value, Constant.ONE);
    }

    /**
     * [生成一个数字列表](Generate a list of numbers)
     * @description: zh - 生成一个数字列表
     * @description: en - Generate a list of numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/13 1:27 下午
     * @param start: 开始的数字（包含）
     * @param end: 结束的数字（不包含）
     * @return int[]
    */
    public static int[] range(int start, int end) {
        return range(start, end, Constant.ONE);
    }

    /**
     * [生成一个数字列表](Generate a list of numbers)
     * @description: zh - 生成一个数字列表
     * @description: en - Generate a list of numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/13 1:28 下午
     * @param start: 开始的数字（包含）
     * @param end: 结束的数字（不包含）
     * @param step: 步进
     * @return int[]
    */
    public static int[] range(int start, int end, int step) {
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }

        if (step <= Constant.ZERO) {
            step = Constant.ONE;
        }

        int deviation = end - start;
        int length = deviation / step;
        if (deviation % step != Constant.ZERO) {
            length += Constant.ONE;
        }
        int[] range = new int[length];
        for (int i = Constant.ZERO; i < length; i++) {
            range[i] = start;
            start += step;
        }
        return range;
    }

    /* 拆分 ------------------------------------------------------------------------------- split */

    /**
     * [拆分byte数组为几个等份](Split byte array into several equal parts)
     * @description: zh - 拆分byte数组为几个等份
     * @description: en - Split byte array into several equal parts
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/13 1:35 下午
     * @param array: 数组
     * @param len: 每个小节的长度
     * @return byte[][]
    */
    public static byte[][] split(byte[] array, int len) {
        int amount = array.length / len;
        final int remainder = array.length % len;
        if (remainder != Constant.ZERO) {
            ++amount;
        }
        final byte[][] arrays = new byte[amount][];
        byte[] arr;
        for (int i = Constant.ZERO; i < amount; i++) {
            if (i == amount - Constant.ONE && remainder != Constant.ZERO) {
                // 有剩余，按照实际长度创建
                arr = new byte[remainder];
                System.arraycopy(array, i * len, arr, Constant.ZERO, remainder);
            } else {
                arr = new byte[len];
                System.arraycopy(array, i * len, arr, Constant.ZERO, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }

    /* 返回数组中指定元素所在位置 ------------------------------------------------------------------------------- indexOf */

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在位置
     * @description: en - Returns the position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 12:41 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(long[] array, long value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在位置
     * @description: en - Returns the position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:00 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(int[] array, int value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在位置
     * @description: en - Returns the position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:01 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(short[] array, short value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在位置
     * @description: en - Returns the position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:02 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(char[] array, char value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在位置
     * @description: en - Returns the position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:02 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(byte[] array, byte value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在位置
     * @description: en - Returns the position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:03 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(double[] array, double value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (NumUtil.equals(value,array[i])) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在位置
     * @description: en - Returns the position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:04 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(float[] array, float value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (NumUtil.equals(value,array[i])) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在位置
     * @description: en - Returns the position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:04 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(boolean[] array, boolean value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /* 返回数组中指定元素所在最后的位置 ------------------------------------------------------------------------------- lastIndexOf */

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在最后的位置
     * @description: en - Returns the last position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 12:47 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int lastIndexOf(long[] array, long value) {
        if (Constant.NULL != array) {
            for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在最后的位置
     * @description: en - Returns the last position of the specified element in the array 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:13 下午
     * @param array: 数组
     * @param value: 被检查的元素 
     * @return int
    */
    public static int lastIndexOf(int[] array, int value) {
        if (Constant.NULL != array) {
            for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在最后的位置
     * @description: en - Returns the last position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:14 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int lastIndexOf(short[] array, short value) {
        if (Constant.NULL != array) {
            for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在最后的位置
     * @description: en - Returns the last position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:15 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int lastIndexOf(char[] array, char value) {
        if (Constant.NULL != array) {
            for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在最后的位置
     * @description: en - Returns the last position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:15 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int lastIndexOf(byte[] array, byte value) {
        if (Constant.NULL != array) {
            for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在最后的位置
     * @description: en - Returns the last position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:16 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int lastIndexOf(double[] array, double value) {
        if (Constant.NULL != array) {
            for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
                if (NumUtil.equals(value, array[i])) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在最后的位置
     * @description: en - Returns the last position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:18 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int lastIndexOf(float[] array, float value) {
        if (Constant.NULL != array) {
            for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
                if (NumUtil.equals(value, array[i])) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description: zh - 返回数组中指定元素所在最后的位置
     * @description: en - Returns the last position of the specified element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 1:20 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int lastIndexOf(boolean[] array, boolean value) {
        if (Constant.NULL != array) {
            for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /* 数组中是否包含元素 ------------------------------------------------------------------------------- contains */

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:33 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(long[] array, long value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:34 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(int[] array, int value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:34 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(short[] array, short value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:35 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(char[] array, char value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:35 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(byte[] array, byte value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:36 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(double[] array, double value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:36 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(float[] array, float value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:37 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(boolean[] array, boolean value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /* 将原始类型数组包装为包装类型 ------------------------------------------------------------------------------- wrap */

    /**
     * [将原始类型数组包装为包装类型](Wrap the original type array as a wrapper type)
     * @description: zh - 将原始类型数组包装为包装类型
     * @description: en - Wrap the original type array as a wrapper type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:43 下午
     * @param values: 原始类型数组
     * @return java.lang.Integer[]
    */
    public static Integer[] wrap(int... values) {
        if (Constant.NULL == values) { return Constant.INTEGERS_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new Integer[Constant.ZERO]; }
        final Integer[] array = new Integer[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /**
     * [将原始类型数组包装为包装类型](Wrap the original type array as a wrapper type)
     * @description: zh - 将原始类型数组包装为包装类型
     * @description: en - Wrap the original type array as a wrapper type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:58 下午
     * @param values: 原始类型数组
     * @return java.lang.Long[]
    */
    public static Long[] wrap(long... values) {
        if (Constant.NULL == values) { return Constant.LONGS_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new Long[Constant.ZERO]; }
        final Long[] array = new Long[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /**
     * [将原始类型数组包装为包装类型](Wrap the original type array as a wrapper type)
     * @description: zh - 将原始类型数组包装为包装类型
     * @description: en - Wrap the original type array as a wrapper type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 5:03 下午
     * @param values: 原始类型数组
     * @return java.lang.Character[]
    */
    public static Character[] wrap(char... values) {
        if (Constant.NULL == values) { return Constant.CHARACTERS_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new Character[Constant.ZERO]; }
        final Character[] array = new Character[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /**
     * [将原始类型数组包装为包装类型](Wrap the original type array as a wrapper type)
     * @description: zh - 将原始类型数组包装为包装类型
     * @description: en - Wrap the original type array as a wrapper type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 5:16 下午
     * @param values: 原始类型数组
     * @return java.lang.Byte[]
    */
    public static Byte[] wrap(byte... values) {
        if (Constant.NULL == values) { return Constant.BYTES_UP_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new Byte[Constant.ZERO]; }
        final Byte[] array = new Byte[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /**
     * [将原始类型数组包装为包装类型](Wrap the original type array as a wrapper type)
     * @description: zh - 将原始类型数组包装为包装类型
     * @description: en - Wrap the original type array as a wrapper type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 5:51 下午
     * @param values: 原始类型数组
     * @return java.lang.Short[]
    */
    public static Short[] wrap(short... values) {
        if (Constant.NULL == values) { return Constant.SHORTS_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new Short[Constant.ZERO]; }
        final Short[] array = new Short[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /**
     * [将原始类型数组包装为包装类型](Wrap the original type array as a wrapper type)
     * @description: zh - 将原始类型数组包装为包装类型
     * @description: en - Wrap the original type array as a wrapper type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 8:26 上午
     * @param values: 原始类型数组
     * @return java.lang.Float[]
    */
    public static Float[] wrap(float... values) {
        if (Constant.NULL == values) { return Constant.FLOATS_UP_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new Float[Constant.ZERO]; }
        final Float[] array = new Float[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /**
     * [将原始类型数组包装为包装类型](Wrap the original type array as a wrapper type)
     * @description: zh - 将原始类型数组包装为包装类型
     * @description: en - Wrap the original type array as a wrapper type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 8:28 上午
     * @param values: 原始类型数组
     * @return java.lang.Double[]
    */
    public static Double[] wrap(double... values) {
        if (Constant.NULL == values) { return Constant.DOUBLES_UP_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new Double[Constant.ZERO]; }
        final Double[] array = new Double[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /**
     * [将原始类型数组包装为包装类型](Wrap the original type array as a wrapper type)
     * @description: zh - 将原始类型数组包装为包装类型
     * @description: en - Wrap the original type array as a wrapper type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 8:31 上午
     * @param values: 原始类型数组
     * @return java.lang.Boolean[]
    */
    public static Boolean[] wrap(boolean... values) {
        if (Constant.NULL == values) { return Constant.BOOLEANS_UP_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new Boolean[Constant.ZERO]; }
        final Boolean[] array = new Boolean[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = values[i];
        }
        return array;
    }

    /* 包装类数组转为原始类型数组 ------------------------------------------------------------------------------- unWrap */

    /**
     * [包装类数组转为原始类型数组](Convert wrapper class array to original type array)
     * @description: zh - 包装类数组转为原始类型数组
     * @description: en - Convert wrapper class array to original type array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 4:54 下午
     * @param values: 包装类型数组
     * @return int[]
    */
    public static int[] unWrap(Integer... values) {
        if (Constant.NULL == values) { return Constant.INTS_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new int[Constant.ZERO]; }
        final int[] array = new int[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = ObjectUtil.defaultIfNull(values[i], Constant.ZERO);
        }
        return array;
    }

    /**
     * [包装类数组转为原始类型数组](Convert wrapper class array to original type array)
     * @description: zh - 包装类数组转为原始类型数组
     * @description: en - Convert wrapper class array to original type array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 5:01 下午
     * @param values: 包装类型数组
     * @return long[]
    */
    public static long[] unWrap(Long... values) {
        if (Constant.NULL == values) { return Constant.LONGS_DOWN_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new long[Constant.ZERO]; }
        final long[] array = new long[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = ObjectUtil.defaultIfNull(values[i], Constant.LONG_ZERO);
        }
        return array;
    }

    /**
     * [包装类数组转为原始类型数组](Convert wrapper class array to original type array)
     * @description: zh - 包装类数组转为原始类型数组
     * @description: en - Convert wrapper class array to original type array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 5:14 下午
     * @param values: 包装类型数组
     * @return char[]
    */
    public static char[] unWrap(Character... values) {
        if (Constant.NULL == values) { return Constant.CHARS_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new char[Constant.ZERO]; }
        char[] array = new char[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = ObjectUtil.defaultIfNull(values[i], Character.MIN_VALUE);
        }
        return array;
    }

    /**
     * [包装类数组转为原始类型数组](Convert wrapper class array to original type array)
     * @description: zh - 包装类数组转为原始类型数组
     * @description: en - Convert wrapper class array to original type array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 5:17 下午
     * @param values: 包装类型数组
     * @return byte[]
    */
    public static byte[] unWrap(Byte... values) {
        if (Constant.NULL == values) { return Constant.BYTES_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new byte[Constant.ZERO]; }
        final byte[] array = new byte[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = ObjectUtil.defaultIfNull(values[i], (byte) Constant.ZERO);
        }
        return array;
    }

    /**
     * [包装类数组转为原始类型数组](Convert wrapper class array to original type array)
     * @description: zh - 包装类数组转为原始类型数组
     * @description: en - Convert wrapper class array to original type array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/14 5:52 下午
     * @param values: 包装类型数组
     * @return short[]
    */
    public static short[] unWrap(Short... values) {
        if (Constant.NULL == values) { return Constant.SHORTS_DOWN_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new short[Constant.ZERO]; }
        final short[] array = new short[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = ObjectUtil.defaultIfNull(values[i], (short) Constant.ZERO);
        }
        return array;
    }

    /**
     * [包装类数组转为原始类型数组](Convert wrapper class array to original type array)
     * @description: zh - 包装类数组转为原始类型数组
     * @description: en - Convert wrapper class array to original type array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 8:27 上午
     * @param values: 包装类型数组
     * @return float[]
    */
    public static float[] unWrap(Float... values) {
        if (Constant.NULL == values) { return Constant.FLOATS_DOWN_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new float[Constant.ZERO]; }
        final float[] array = new float[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = ObjectUtil.defaultIfNull(values[i], Constant.FLOAT_ZERO);
        }
        return array;
    }

    /**
     * [包装类数组转为原始类型数组](Convert wrapper class array to original type array)
     * @description: zh - 包装类数组转为原始类型数组
     * @description: en - Convert wrapper class array to original type array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 8:30 上午
     * @param values: 包装类型数组
     * @return double[]
    */
    public static double[] unWrap(Double... values) {
        if (Constant.NULL == values) { return Constant.DOUBLES_DOWN_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new double[Constant.ZERO]; }
        final double[] array = new double[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = ObjectUtil.defaultIfNull(values[i], Constant.DOUBLE_ZERO);
        }
        return array;
    }

    /**
     * [包装类数组转为原始类型数组](Convert wrapper class array to original type array)
     * @description: zh - 包装类数组转为原始类型数组
     * @description: en - Convert wrapper class array to original type array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 8:32 上午
     * @param values: 包装类型数组
     * @return boolean[]
    */
    public static boolean[] unWrap(Boolean... values) {
        if (Constant.NULL == values) { return Constant.BOOLEANS_DOWN_NULL; }
        final int length = values.length;
        if (Constant.ZERO == length) { return new boolean[Constant.ZERO]; }
        final boolean[] array = new boolean[length];
        for (int i = Constant.ZERO; i < length; i++) {
            array[i] = ObjectUtil.defaultIfNull(values[i], Constant.FALSE);
        }
        return array;
    }

    /* 获取子数组 ------------------------------------------------------------------------------- sub */

    /**
     * [获取子数组](Get subarray)
     * @description: zh - 获取子数组
     * @description: en - Get subarray
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 1:47 下午
     * @param array: 数组
     * @param start: 开始位置
     * @param end: 结束位置
     * @return byte[]
    */
    public static byte[] sub(byte[] array, int start, int end) {
        int length = Array.getLength(array);
        if (start < Constant.ZERO) {
            start += length;
        }
        if (end < Constant.ZERO) {
            end += length;
        }
        if (start == length) {
            return new byte[Constant.ZERO];
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > length) {
            if (start >= length) {
                return new byte[Constant.ZERO];
            }
            end = length;
        }
        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * [获取子数组](Get subarray)
     * @description: zh - 获取子数组
     * @description: en - Get subarray
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 1:48 下午
     * @param array: 数组
     * @param start: 开始位置
     * @param end: 结束位置
     * @return int[]
    */
    public static int[] sub(int[] array, int start, int end){
        int length = Array.getLength(array);
        if (start < Constant.ZERO) {
            start += length;
        }
        if (end < Constant.ZERO) {
            end += length;
        }
        if (start == length) {
            return new int[Constant.ZERO];
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > length) {
            if (start >= length) {
                return new int[Constant.ZERO];
            }
            end = length;
        }
        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * [获取子数组](Get subarray)
     * @description: zh - 获取子数组
     * @description: en - Get subarray
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 1:48 下午
     * @param array: 数组
     * @param start: 开始位置
     * @param end: 结束位置
     * @return long[]
    */
    public static long[] sub(long[] array, int start, int end) {
        int length = Array.getLength(array);
        if (start < Constant.ZERO) {
            start += length;
        }
        if (end < Constant.ZERO) {
            end += length;
        }
        if (start == length) {
            return new long[Constant.ZERO];
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > length) {
            if (start >= length) {
                return new long[Constant.ZERO];
            }
            end = length;
        }
        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * [获取子数组](Get subarray)
     * @description: zh - 获取子数组
     * @description: en - Get subarray
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 1:49 下午
     * @param array: 数组
     * @param start: 开始位置
     * @param end: 结束位置
     * @return char[]
    */
    public static char[] sub(char[] array, int start, int end) {
        int length = Array.getLength(array);
        if (start < Constant.ZERO) {
            start += length;
        }
        if (end < Constant.ZERO) {
            end += length;
        }
        if (start == length) {
            return new char[Constant.ZERO];
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > length) {
            if (start >= length) {
                return new char[Constant.ZERO];
            }
            end = length;
        }
        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * [获取子数组](Get subarray)
     * @description: zh - 获取子数组
     * @description: en - Get subarray
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 1:48 下午
     * @param array: 数组
     * @param start: 开始位置
     * @param end: 结束位置
     * @return short[]
    */
    public static short[] sub(short[] array, int start, int end) {
        int length = Array.getLength(array);
        if (start < Constant.ZERO) {
            start += length;
        }
        if (end < Constant.ZERO) {
            end += length;
        }
        if (start == length) {
            return new short[Constant.ZERO];
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > length) {
            if (start >= length) {
                return new short[Constant.ZERO];
            }
            end = length;
        }
        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * [获取子数组](Get subarray)
     * @description: zh - 获取子数组
     * @description: en - Get subarray
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 1:49 下午
     * @param array: 数组
     * @param start: 开始位置
     * @param end: 结束位置
     * @return double[]
    */
    public static double[] sub(double[] array, int start, int end) {
        int length = Array.getLength(array);
        if (start < Constant.ZERO) {
            start += length;
        }
        if (end < Constant.ZERO) {
            end += length;
        }
        if (start == length) {
            return new double[Constant.ZERO];
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > length) {
            if (start >= length) {
                return new double[Constant.ZERO];
            }
            end = length;
        }
        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * [获取子数组](Get subarray)
     * @description: zh - 获取子数组
     * @description: en - Get subarray
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 1:47 下午
     * @param array: 数组
     * @param start: 开始位置
     * @param end: 结束位置
     * @return float[]
    */
    public static float[] sub(float[] array, int start, int end) {
        int length = Array.getLength(array);
        if (start < Constant.ZERO) {
            start += length;
        }
        if (end < Constant.ZERO) {
            end += length;
        }
        if (start == length) {
            return new float[Constant.ZERO];
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > length) {
            if (start >= length) {
                return new float[Constant.ZERO];
            }
            end = length;
        }
        return Arrays.copyOfRange(array, start, end);
    }

    /**
     * [获取子数组](Get subarray)
     * @description: zh - 获取子数组
     * @description: en - Get subarray
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 1:45 下午
     * @param array: 数组
     * @param start: 开始位置
     * @param end: 结束位置
     * @return boolean[]
    */
    public static boolean[] sub(boolean[] array, int start, int end) {
        int length = Array.getLength(array);
        if (start < Constant.ZERO) {
            start += length;
        }
        if (end < Constant.ZERO) {
            end += length;
        }
        if (start == length) {
            return new boolean[Constant.ZERO];
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > length) {
            if (start >= length) {
                return new boolean[Constant.ZERO];
            }
            end = length;
        }
        return Arrays.copyOfRange(array, start, end);
    }

    /* 分隔符将数组转换为字符串 ------------------------------------------------------------------------------- join */

    /**
     * [分隔符将数组转换为字符串](The delimiter converts the array to a string)
     * @description: zh - 分隔符将数组转换为字符串
     * @description: en - The delimiter converts the array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 3:49 下午
     * @param array: 数组
     * @param conjunction: 分隔符
     * @return java.lang.String
    */
    public static String join(int[] array, CharSequence conjunction) {
        if (Constant.NULL == array) {
            return Constant.STRING_NULL;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = Constant.TRUE;
        for (int item : array) {
            if (isFirst) {
                isFirst = Constant.FALSE;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * [分隔符将数组转换为字符串](The delimiter converts the array to a string)
     * @description: zh - 分隔符将数组转换为字符串
     * @description: en - The delimiter converts the array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 3:50 下午
     * @param array: 数组
     * @param conjunction: 分隔符
     * @return java.lang.String
    */
    public static String join(short[] array, CharSequence conjunction) {
        if (Constant.NULL == array) {
            return Constant.STRING_NULL;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = Constant.TRUE;
        for (short item : array) {
            if (isFirst) {
                isFirst = Constant.FALSE;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * [分隔符将数组转换为字符串](The delimiter converts the array to a string)
     * @description: zh - 分隔符将数组转换为字符串
     * @description: en - The delimiter converts the array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 3:50 下午
     * @param array: 数组
     * @param conjunction: 分隔符
     * @return java.lang.String
     */
    public static String join(char[] array, CharSequence conjunction) {
        if (Constant.NULL == array) {
            return Constant.STRING_NULL;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = Constant.TRUE;
        for (char item : array) {
            if (isFirst) {
                isFirst = Constant.FALSE;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * [分隔符将数组转换为字符串](The delimiter converts the array to a string)
     * @description: zh - 分隔符将数组转换为字符串
     * @description: en - The delimiter converts the array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 3:50 下午
     * @param array: 数组
     * @param conjunction: 分隔符
     * @return java.lang.String
     */
    public static String join(byte[] array, CharSequence conjunction) {
        if (Constant.NULL == array) {
            return Constant.STRING_NULL;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = Constant.TRUE;
        for (byte item : array) {
            if (isFirst) {
                isFirst = Constant.FALSE;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * [分隔符将数组转换为字符串](The delimiter converts the array to a string)
     * @description: zh - 分隔符将数组转换为字符串
     * @description: en - The delimiter converts the array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 3:50 下午
     * @param array: 数组
     * @param conjunction: 分隔符
     * @return java.lang.String
     */
    public static String join(boolean[] array, CharSequence conjunction) {
        if (Constant.NULL == array) {
            return Constant.STRING_NULL;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = Constant.TRUE;
        for (boolean item : array) {
            if (isFirst) {
                isFirst = Constant.FALSE;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * [分隔符将数组转换为字符串](The delimiter converts the array to a string)
     * @description: zh - 分隔符将数组转换为字符串
     * @description: en - The delimiter converts the array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 3:50 下午
     * @param array: 数组
     * @param conjunction: 分隔符
     * @return java.lang.String
     */
    public static String join(float[] array, CharSequence conjunction) {
        if (Constant.NULL == array) {
            return Constant.STRING_NULL;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = Constant.TRUE;
        for (float item : array) {
            if (isFirst) {
                isFirst = Constant.FALSE;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * [分隔符将数组转换为字符串](The delimiter converts the array to a string)
     * @description: zh - 分隔符将数组转换为字符串
     * @description: en - The delimiter converts the array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 3:50 下午
     * @param array: 数组
     * @param conjunction: 分隔符
     * @return java.lang.String
     */
    public static String join(double[] array, CharSequence conjunction) {
        if (Constant.NULL == array) {
            return Constant.STRING_NULL;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = Constant.TRUE;
        for (double item : array) {
            if (isFirst) {
                isFirst = Constant.FALSE;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /* 移除数组中对应位置的元素 ------------------------------------------------------------------------------- remove */

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:02 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return long[]
    */
    public static long[] remove(long[] array, int index) throws IllegalArgumentException {
        return (long[]) remove((Object) array, index);
    }

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:14 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return int[]
    */
    public static int[] remove(int[] array, int index) throws IllegalArgumentException {
        return (int[]) remove((Object) array, index);
    }

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:14 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return short[]
    */
    public static short[] remove(short[] array, int index) throws IllegalArgumentException {
        return (short[]) remove((Object) array, index);
    }

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:13 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return char[]
    */
    public static char[] remove(char[] array, int index) throws IllegalArgumentException {
        return (char[]) remove((Object) array, index);
    }

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:13 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return byte[]
    */
    public static byte[] remove(byte[] array, int index) throws IllegalArgumentException {
        return (byte[]) remove((Object) array, index);
    }

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:12 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return float[]
    */
    public static float[] remove(float[] array, int index) throws IllegalArgumentException {
        return (float[]) remove((Object) array, index);
    }

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:12 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return double[]
    */
    public static double[] remove(double[] array, int index) throws IllegalArgumentException {
        return (double[]) remove((Object) array, index);
    }

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:05 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return boolean[]
    */
    public static boolean[] remove(boolean[] array, int index) throws IllegalArgumentException {
        return (boolean[]) remove((Object) array, index);
    }

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description: zh - 移除数组中对应位置的元素
     * @description: en - Remove the element at the corresponding position in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/15 4:04 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param index: 位置，如果位置小于0或者大于长度，返回原数组
     * @return java.lang.Object
    */
    public static Object remove(Object array, int index) throws IllegalArgumentException {
        if (Constant.NULL == array) { return Constant.NULL; }
        int length = Array.getLength(array);
        if (index < Constant.ZERO || index >= length) { return array; }
        final Object result = Array.newInstance(array.getClass().getComponentType(), length - Constant.ONE);
        System.arraycopy(array, Constant.ZERO, result, Constant.ZERO, index);
        if (index < length - Constant.ONE) {
            System.arraycopy(array, index + Constant.ONE, result, index, length - index - Constant.ONE);
        }
        return result;
    }

    /* 移除数组中指定的元素 ------------------------------------------------------------------------------- remove element */

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description: zh - 移除数组中指定的元素
     * @description: en - Removes the specified element from the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 12:50 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param element: 要移除的元素
     * @return long[]
    */
    public static long[] removeEle(long[] array, long element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description: zh - 移除数组中指定的元素
     * @description: en - Removes the specified element from the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 12:52 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param element: 要移除的元素
     * @return int[]
    */
    public static int[] removeEle(int[] array, int element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description: zh - 移除数组中指定的元素
     * @description: en - Removes the specified element from the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 12:53 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param element: 要移除的元素
     * @return short[]
    */
    public static short[] removeEle(short[] array, short element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description: zh - 移除数组中指定的元素
     * @description: en - Removes the specified element from the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 12:55 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param element: 要移除的元素
     * @return char[]
    */
    public static char[] removeEle(char[] array, char element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description: zh - 移除数组中指定的元素
     * @description: en - Removes the specified element from the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 12:57 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param element: 要移除的元素
     * @return byte[]
    */
    public static byte[] removeEle(byte[] array, byte element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description: zh - 移除数组中指定的元素
     * @description: en - Removes the specified element from the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 12:57 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param element: 要移除的元素
     * @return double[]
    */
    public static double[] removeEle(double[] array, double element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description: zh - 移除数组中指定的元素
     * @description: en - Removes the specified element from the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 12:57 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param element: 要移除的元素
     * @return float[]
    */
    public static float[] removeEle(float[] array, float element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description: zh - 移除数组中指定的元素
     * @description: en - Removes the specified element from the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 12:58 下午
     * @param array: 数组对象，可以是对象数组，也可以原始类型数组
     * @param element: 要移除的元素
     * @return boolean[]
    */
    public static boolean[] removeEle(boolean[] array, boolean element) throws IllegalArgumentException {
        return remove(array, indexOf(array, element));
    }

    /* 反转数组 ------------------------------------------------------------------------------- reverse */

    /**
     * [反转数组](Invert array)
     * @description: zh - 反转数组
     * @description: en - Invert array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 1:06 下午
     * @param array : 数组，会变更
     * @param start : 其实位置（包含）
     * @param end : 结束位置（不包含）
     * @return long[]
    */
    public static long[] reverse(long[] array, final int start, final int end) {
        if (isEmpty(array)) {
            return array;
        }
        int i = NumUtil.max(start, Constant.ZERO);
        int j = NumUtil.min(array.length, end) - Constant.ONE;
        long tmp;
        while (j > i) {
            swap(array, i, j);
            j--;
            i++;
        }
        return array;
    }

    /**
     * [反转数组](Invert array)
     * @description: zh - 反转数组
     * @description: en - Invert array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/16 1:08 下午
     * @param array : 数组
     * @return long[]
    */
    public static long[] reverse(long[] array) {
        return reverse(array, Constant.ZERO, array.length);
    }
    
    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 18:22:54
     * @param array 数组
     * @param start 起始位置 
     * @param end 结束位置
     * @return int[]
     */
    public static int[] reverse(int[] array, final int start, final int end) {
		if (isEmpty(array)) {
			return array;
		}
		int i = NumUtil.max(start, Constant.ZERO);
		int j = NumUtil.min(array.length, end) - Constant.ONE;
		int tmp;
		while (j > i) {
			swap(array, i, j);
			j--;
			i++;
		}
		return array;
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 18:30:14
     * @param array : 数组
     * @return int[]
     */
    public static int[] reverse(int[] array) {
		return reverse(array, Constant.ZERO, array.length);
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:05:20
     * @param array 数组
     * @param start 起始位置 
     * @param end 结束位置
     * @return short[]
     */
    public static short[] reverse(short[] array, final int start, final int end) {
		if (isEmpty(array)) {
			return array;
		}
		int i = NumUtil.max(start, Constant.ZERO);
		int j = NumUtil.min(array.length, end) - Constant.ONE;
		short tmp;
		while (j > i) {
			swap(array, i, j);
			j--;
			i++;
		}
		return array;
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:06:38
     * @param array : 数组
     * @return short[]
     */
    public static short[] reverse(short[] array) {
		return reverse(array, Constant.ZERO, array.length);
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:09:14
     * @param array 数组
     * @param start 起始位置 
     * @param end 结束位置
     * @return char[]
     */
    public static char[] reverse(char[] array, final int start, final int end) {
		if (isEmpty(array)) {
			return array;
		}
		int i = NumUtil.max(start, Constant.ZERO);
		int j = NumUtil.min(array.length, end) - Constant.ONE;
		char tmp;
		while (j > i) {
			swap(array, i, j);
			j--;
			i++;
		}
		return array;
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:12:03
     * @param array : 数组
     * @return char[]
     */
    public static char[] reverse(char[] array) {
		return reverse(array, Constant.ZERO, array.length);
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:13:41
     * @param array 数组
     * @param start 起始位置 
     * @param end 结束位置
     * @return byte[]
     */
    public static byte[] reverse(byte[] array, final int start, final int end) {
		if (isEmpty(array)) {
			return array;
		}
		int i = NumUtil.max(start, Constant.ZERO);
		int j = NumUtil.min(array.length, end) - Constant.ONE;
		byte tmp;
		while (j > i) {
			swap(array, i, j);
			j--;
			i++;
		}
		return array;
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:15:39
     * @param array : 数组
     * @return byte[]
     */
    public static byte[] reverse(byte[] array) {
		return reverse(array, Constant.ZERO, array.length);
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:17:04
     * @param array 数组
     * @param start 起始位置 
     * @param end 结束位置
     * @return double[]
     */
    public static double[] reverse(double[] array, final int start, final int end) {
		if (isEmpty(array)) {
			return array;
		}
		int i = NumUtil.max(start, Constant.ZERO);
		int j = NumUtil.min(array.length, end) - Constant.ONE;
		double tmp;
		while (j > i) {
			swap(array, i, j);
			j--;
			i++;
		}
		return array;
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:18:15
     * @param array : 数组
     * @return double[]
     */
    public static double[] reverse(double[] array) {
		return reverse(array, Constant.ZERO, array.length);
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:19:00
     * @param array 数组
     * @param start 起始位置 
     * @param end 结束位置
     * @return float[]
     */
    public static float[] reverse(float[] array, final int start, final int end) {
		if (isEmpty(array)) {
			return array;
		}
		int i = NumUtil.max(start, Constant.ZERO);
		int j = NumUtil.min(array.length, end) - Constant.ONE;
		float tmp;
		while (j > i) {
			swap(array, i, j);
			j--;
			i++;
		}
		return array;
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:21:56
     * @param array : 数组
     * @return float[]
     */
    public static float[] reverse(float[] array) {
		return reverse(array, Constant.ZERO, array.length);
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:22:39
     * @param array 数组
     * @param start 起始位置 
     * @param end 结束位置
     * @return boolean[]
     */
    public static boolean[] reverse(boolean[] array, final int start, final int end) {
		if (isEmpty(array)) {
			return array;
		}
		int i = NumUtil.max(start, Constant.ZERO);
		int j = NumUtil.min(array.length, end) - Constant.ONE;
		boolean tmp;
		while (j > i) {
			swap(array, i, j);
			j--;
			i++;
		}
		return array;
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-16 19:23:39
     * @param array : 数组
     * @return boolean[]
     */
    public static boolean[] reverse(boolean[] array) {
		return reverse(array, Constant.ZERO, array.length);
	}

    /* 最小值 ------------------------------------------------------------------------------- min */

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 13:12:56
     * @param arrays 数组
     * @return long
     */
    public static long min(long... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		long min = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (min > arrays[i]) {
				min = arrays[i];
			}
		}
		return min;
	}

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 13:16:13
     * @param arrays 数组
     * @return int
     */
    public static int min(int... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		int min = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (min > arrays[i]) {
				min = arrays[i];
			}
		}
		return min;
	}

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 13:19:21
     * @param arrays 数组
     * @return short
     */
    public static short min(short... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		short min = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (min > arrays[i]) {
				min = arrays[i];
			}
		}
		return min;
	}

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 13:21:03
     * @param arrays 数组
     * @return char
     */
    public static char min(char... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		char min = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (min > arrays[i]) {
				min = arrays[i];
			}
		}
		return min;
	}

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 13:22:07
     * @param arrays 数组
     * @return byte
     */
	public static byte min(byte... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		byte min = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (min > arrays[i]) {
				min = arrays[i];
			}
		}
		return min;
	}

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 13:24:29
     * @param arrays 数组
     * @return double
     */
	public static double min(double... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		double min = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (min > arrays[i]) {
				min = arrays[i];
			}
		}
		return min;
	}

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 13:24:54
     * @param arrays 数组
     * @return float
     */
	public static float min(float... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		float min = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (min > arrays[i]) {
				min = arrays[i];
			}
		}
		return min;
	}

    /* 最大值 ------------------------------------------------------------------------------- max */

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 17:36:46
     * @param arrays 数组
     * @return long
     */
    public static long max(long... arryas) {
		if (isEmpty(arryas)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		long max = arryas[Constant.ZERO];
		for (int i = Constant.ONE; i < arryas.length; i++) {
			if (max < arryas[i]) {
				max = arryas[i];
			}
		}
		return max;
	}

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 17:39:03
     * @param arrays 数组
     * @return int
     */
    public static int max(int... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		int max = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (max < arrays[i]) {
				max = arrays[i];
			}
		}
		return max;
	}

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 17:42:33
     * @param arrays 数组
     * @return short
     */
    public static short max(short... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		short max = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (max < arrays[i]) {
				max = arrays[i];
			}
		}
		return max;
	}

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 17:44:26
     * @param arrays 数组
     * @return char
     */
    public static char max(char... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		char max = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (max < arrays[i]) {
				max = arrays[i];
			}
		}
		return max;
	}

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 17:48:42
     * @param arrays 数组
     * @return byte
     */
    public static byte max(byte... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		byte max = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (max < arrays[i]) {
				max = arrays[i];
			}
		}
		return max;
	}

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 17:49:25
     * @param arrays 数组
     * @return double
     */
    public static double max(double... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		double max = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (max < arrays[i]) {
				max = arrays[i];
			}
		}
		return max;
	}

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 17:51:21
     * @param arrays 数组
     * @return float
    */
    public static float max(float... arrays) {
		if (isEmpty(arrays)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		float max = arrays[Constant.ZERO];
		for (int i = Constant.ONE; i < arrays.length; i++) {
			if (max < arrays[i]) {
				max = arrays[i];
			}
		}
		return max;
	}

    /* 打乱数组顺序 ------------------------------------------------------------------------------- disorder */

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 18:02:38
     * @param array 数组
     * @return int[]
     */
    public static int[] disorder(int[] array) {
		return disorder(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 18:04:35
     * @param array 数组
     * @param random 随机数生成器
     * @return int[]
     */
    public static int[] disorder(int[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - Constant.ONE, random.nextInt(i));
		}

		return array;
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 18:06:42
     * @param array 数组
     * @return long[]
     */
    public static long[] disorder(long[] array) {
		return disorder(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 18:07:54
     * @param array 数组
     * @param random 随机数生成器
     * @return long[]
     */
    public static long[] disorder(long[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - Constant.ONE, random.nextInt(i));
		}

		return array;
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 18:53:55
     * @param array 数组
     * @return double[]
     */
    public static double[] disorder(double[] array) {
		return disorder(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 18:55:32
     * @param array 数组
     * @param random 随机数生成器
     * @return double[]
     */
    public static double[] disorder(double[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - Constant.ONE, random.nextInt(i));
		}

		return array;
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 18:57:46
     * @param array 数组
     * @return float[]
     */
	public static float[] disorder(float[] array) {
		return disorder(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 18:58:10
     * @param array 数组
     * @param random 随机数生成器
     * @return double[]
     */
	public static float[] disorder(float[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - Constant.ONE, random.nextInt(i));
		}

		return array;
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:02:47
     * @param array 数组
     * @return float[]
     */
	public static boolean[] disorder(boolean[] array) {
		return disorder(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:03:08
     * @param array 数组
     * @param random 随机数生成器
     * @return double[]
     */
	public static boolean[] disorder(boolean[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - Constant.ONE, random.nextInt(i));
		}

		return array;
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:05:03
     * @param array 数组
     * @return byte[]
     */
	public static byte[] disorder(byte[] array) {
		return disorder(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:05:31
     * @param array 数组
     * @param random 随机数生成器
     * @return byte[]
     */
	public static byte[] disorder(byte[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - Constant.ONE, random.nextInt(i));
		}

		return array;
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:06:49
     * @param array 数组
     * @return char[]
     */
	public static char[] shuffle(char[] array) {
		return shuffle(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:07:21
     * @param array 数组
     * @param random 随机数生成器
     * @return char[]
     */
	public static char[] shuffle(char[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - Constant.ONE, random.nextInt(i));
		}

		return array;
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:10:54
     * @param array 数组
     * @return short[]
     */
	public static short[] shuffle(short[] array) {
		return shuffle(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:11:29
     * @param array 数组
     * @param random 随机数生成器
     * @return short[]
     */
	public static short[] shuffle(short[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - Constant.ONE, random.nextInt(i));
		}

		return array;
	}

    /* 交换数组中两个位置的值 ------------------------------------------------------------------------------- swap */

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:30:39
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return int[]
     */
    public static int[] swap(int[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		int tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:33:31
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return long[]
     */
    public static long[] swap(long[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		long tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:35:29
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return double[]
     */
    public static double[] swap(double[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		double tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:36:12
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return float[]
     */
    public static float[] swap(float[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		float tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:37:01
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return boolean[]
     */
    public static boolean[] swap(boolean[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		boolean tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:37:50
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return byte[]
     */
    public static byte[] swap(byte[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		byte tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:38:43
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return char[]
     */
    public static char[] swap(char[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		char tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:39:25
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return short[]
     */
    public static short[] swap(short[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("array must not empty !");
		}
		short tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /* 数组是否升序 ------------------------------------------------------------------------------- ASC */

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:52:53
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedASC(byte[] array) {
		if (array == Constant.NULL) { return Constant.FALSE; }
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] > array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:58:39
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedASC(short[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] > array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:01:16
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedASC(char[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] > array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:03:14
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedASC(int[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] > array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:04:39
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedASC(long[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] > array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:06:21
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedASC(double[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] > array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:07:46
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedASC(float[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] > array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /* 数组是否降序 ------------------------------------------------------------------------------- DESC */

    /**
     * [检查数组是否降序](Check whether the array is in descending order)
     * @description zh - 检查数组是否降序
     * @description en - Check whether the array is in descending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 19:56:55
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedDESC(byte[] array) {
		if (array == Constant.NULL) { return Constant.FALSE; }
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] < array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否降序](Check whether the array is in descending order)
     * @description zh - 检查数组是否降序
     * @description en - Check whether the array is in descending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:00:22
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedDESC(short[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] < array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否降序](Check whether the array is in descending order)
     * @description zh - 检查数组是否降序
     * @description en - Check whether the array is in descending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:01:51
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedDESC(char[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] < array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否降序](Check whether the array is in descending order)
     * @description zh - 检查数组是否降序
     * @description en - Check whether the array is in descending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:03:51
     * @param array 数组
     * @return boolean
     */ 
    public static boolean isSortedDESC(int[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] < array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否降序](Check whether the array is in descending order)
     * @description zh - 检查数组是否降序
     * @description en - Check whether the array is in descending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-17 20:05:34
     * @param array 数组
     * @return boolean
     */
    public static boolean isSortedDESC(long[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}
		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i] < array[i + Constant.ONE]) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}
}
