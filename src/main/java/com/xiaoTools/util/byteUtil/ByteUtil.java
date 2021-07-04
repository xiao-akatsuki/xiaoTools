package com.xiaoTools.util.byteUtil;

import com.xiaoTools.lang.constant.Constant;

import java.nio.ByteOrder;

/**
 * [对数字和字节进行转换](Converting numbers and bytes)
 * @description: zh - 对数字和字节进行转换
 * @description: en - Converting numbers and bytes
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/7/4 4:58 下午
*/
public class ByteUtil {

    /**
     * [int转byte](Int to byte)
     * @description: zh - int转byte
     * @description: en - Int to byte
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 4:59 下午
     * @param intValue: int值
     * @return byte
    */
    public static byte intToByte(int intValue) {
        return (byte) intValue;
    }

    /**
     * [byte转无符号int](Byte to unsigned int)
     * @description: zh - byte转无符号int
     * @description: en - Byte to unsigned int
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 5:05 下午
     * @param byteValue:
     * @return int
    */
    public static int byteToUnsignedInt(byte byteValue) {
        // Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return byteValue & Constant.ZERO_X_F_F;
    }

    /**
     * [byte数组转short](Byte array to short)
     * @description: zh - byte数组转short
     * @description: en - Byte array to short
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 5:07 下午
     * @param bytes: byte数组
     * @return short
    */
    public static short bytesToShort(byte[] bytes) {
        return bytesToShort(bytes, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [byte数组转short](Byte array to short)
     * @description: zh - byte数组转short
     * @description: en - Byte array to short
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 5:11 下午
     * @param bytes: byte数组
     * @param byteOrder: 端序
     * @return short
    */
    public static short bytesToShort(byte[] bytes, ByteOrder byteOrder) {
        return ByteOrder.LITTLE_ENDIAN == byteOrder ?
                (short) (bytes[Constant.ONE] & Constant.BYTE_TWO | (bytes[Constant.ZERO] & Constant.BYTE_TWO) << Byte.SIZE) :
                (short) (bytes[Constant.ZERO] & Constant.BYTE_TWO | (bytes[Constant.ONE] & Constant.BYTE_TWO) << Byte.SIZE);
    }

    /**
     * [short转byte数组](Short to byte array)
     * @description: zh - short转byte数组
     * @description: en - Short to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 5:22 下午
     * @param shortValue: short值
     * @return byte[]
    */
    public static byte[] shortToBytes(short shortValue) {
        return shortToBytes(shortValue, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [short转byte数组](Short to byte array)
     * @description: zh - short转byte数组
     * @description: en - Short to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 5:29 下午
     * @param shortValue: short值
     * @param byteOrder: 端序
     * @return byte[]
    */
    public static byte[] shortToBytes(short shortValue, ByteOrder byteOrder) {
        byte[] b = new byte[Short.BYTES];
        if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
            b[Constant.ONE] = (byte) (shortValue & Constant.BYTE_TWO);
            b[Constant.ZERO] = (byte) ((shortValue >> Byte.SIZE) & Constant.BYTE_TWO);
        } else {
            b[Constant.ZERO] = (byte) (shortValue & Constant.BYTE_TWO);
            b[Constant.ONE] = (byte) ((shortValue >> Byte.SIZE) & Constant.BYTE_TWO);
        }
        return b;
    }

    /**
     * [byte[]转int值](Byte [] to int)
     * @description: zh - byte[]转int值
     * @description: en - Byte [] to int
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 5:34 下午
     * @param bytes: byte数组
     * @return int
    */
    public static int bytesToInt(byte[] bytes) {
        return bytesToInt(bytes, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [byte[]转int值](Byte [] to int)
     * @description: zh - byte[]转int值
     * @description: en - Byte [] to int
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 5:40 下午
     * @param bytes: byte数组
     * @param byteOrder: 端序
     * @return int
    */
    public static int bytesToInt(byte[] bytes, ByteOrder byteOrder) {
        if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
            return bytes[Constant.THREE] & Constant.ZERO_X_F_F |
                    (bytes[Constant.TWO] & Constant.ZERO_X_F_F) << Constant.EIGHT |
                    (bytes[Constant.ONE] & Constant.ZERO_X_F_F) << Constant.SIXTEEN |
                    (bytes[Constant.ZERO] & Constant.ZERO_X_F_F) << Constant.TWENTY_FOUR;
        } else {
            return bytes[Constant.ZERO] & Constant.ZERO_X_F_F |
                    (bytes[Constant.ONE] & Constant.ZERO_X_F_F) << Constant.EIGHT |
                    (bytes[Constant.TWO] & Constant.ZERO_X_F_F) << Constant.SIXTEEN |
                    (bytes[Constant.THREE] & Constant.ZERO_X_F_F) << Constant.TWENTY_FOUR;
        }
    }

    /**
     * [int转byte数组](Int to byte array)
     * @description: zh - int转byte数组
     * @description: en - Int to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:09 下午
     * @param intValue: int值
     * @return byte[]
    */
    public static byte[] intToBytes(int intValue) {
        return intToBytes(intValue, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [int转byte数组](Int to byte array)
     * @description: zh - int转byte数组
     * @description: en - Int to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:14 下午
     * @param intValue: int值
     * @param byteOrder: 端序
     * @return byte[]
    */
    public static byte[] intToBytes(int intValue, ByteOrder byteOrder) {
        return ByteOrder.LITTLE_ENDIAN == byteOrder ?
                new byte[]{
                        (byte) ((intValue >> Constant.TWENTY_FOUR) & Constant.ZERO_X_F_F),
                        (byte) ((intValue >> Constant.SIXTEEN) & Constant.ZERO_X_F_F),
                        (byte) ((intValue >> Constant.EIGHT) & Constant.ZERO_X_F_F),
                        (byte) (intValue & Constant.ZERO_X_F_F)
                } :
                new byte[]{
                        (byte) (intValue & Constant.ZERO_X_F_F),
                        (byte) ((intValue >> Constant.EIGHT) & Constant.ZERO_X_F_F),
                        (byte) ((intValue >> Constant.SIXTEEN) & Constant.ZERO_X_F_F),
                        (byte) ((intValue >> Constant.TWENTY_FOUR) & Constant.ZERO_X_F_F)
                };
    }

    /**
     * [long转byte数组](Long to byte array)
     * @description: zh - long转byte数组
     * @description: en - Long to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:15 下午
     * @param longValue: long值
     * @return byte[]
    */
    public static byte[] longToBytes(long longValue) {
        return longToBytes(longValue, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [long转byte数组](Long to byte array)
     * @description: zh - long转byte数组
     * @description: en - Long to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:31 下午
     * @param longValue: long值
     * @param byteOrder: 端序
     * @return byte[]
    */
    public static byte[] longToBytes(long longValue, ByteOrder byteOrder) {
        byte[] result = new byte[Long.BYTES];
        if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
            for (int i = (result.length - Constant.ONE); i >= Constant.ZERO; i--) {
                result[i] = (byte) (longValue & Constant.ZERO_X_F_F);
                longValue >>= Byte.SIZE;
            }
        } else {
            for (int i = Constant.ZERO; i < result.length; i++) {
                result[i] = (byte) (longValue & Constant.ZERO_X_F_F);
                longValue >>= Byte.SIZE;
            }
        }
        return result;
    }

    /**
     * [byte数组转long](Convert byte array to long)
     * @description: zh - byte数组转long
     * @description: en - Convert byte array to long
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:32 下午
     * @param bytes: byte数组
     * @return long
    */
    public static long bytesToLong(byte[] bytes) {
        return bytesToLong(bytes, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [byte数组转long](Convert byte array to long)
     * @description: zh - byte数组转long
     * @description: en - Convert byte array to long
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:34 下午
     * @param bytes: byte数组
     * @param byteOrder: 端序
     * @return long
    */
    public static long bytesToLong(byte[] bytes, ByteOrder byteOrder) {
        long values = Constant.ZERO;
        if (ByteOrder.LITTLE_ENDIAN == byteOrder) {
            for (int i = Constant.ZERO; i < Long.BYTES; i++) {
                values <<= Byte.SIZE;
                values |= (bytes[i] & Constant.BYTE_TWO);
            }
        } else {
            for (int i = (Long.BYTES - Constant.ONE); i >= Constant.ZERO; i--) {
                values <<= Byte.SIZE;
                values |= (bytes[i] & Constant.BYTE_TWO);
            }
        }
        return values;
    }

    /**
     * [double转byte数组](Double to byte array)
     * @description: zh - double转byte数组
     * @description: en - Double to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:36 下午
     * @param doubleValue: double值
     * @return byte[]
    */
    public static byte[] doubleToBytes(double doubleValue) {
        return doubleToBytes(doubleValue, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [double转byte数组](Double to byte array)
     * @description: zh - double转byte数组
     * @description: en - Double to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:37 下午
     * @param doubleValue: double值
     * @param byteOrder: 端序
     * @return byte[]
    */
    public static byte[] doubleToBytes(double doubleValue, ByteOrder byteOrder) {
        return longToBytes(Double.doubleToLongBits(doubleValue), byteOrder);
    }

    /**
     * [byte数组转Double](Convert byte array to double)
     * @description: zh - byte数组转Double
     * @description: en - Convert byte array to double
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:38 下午
     * @param bytes: byte数组
     * @return double
    */
    public static double bytesToDouble(byte[] bytes) {
        return bytesToDouble(bytes, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [byte数组转double](Convert byte array to double)
     * @description: zh - byte数组转double
     * @description: en - Convert byte array to double
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:39 下午
     * @param bytes: byte数组
     * @param byteOrder: 端序
     * @return double
    */
    public static double bytesToDouble(byte[] bytes, ByteOrder byteOrder) {
        return Double.longBitsToDouble(bytesToLong(bytes, byteOrder));
    }

    /**
     * [将Number转换为byte数组](Convert number to byte array)
     * @description: zh - 将Number转换为byte数组
     * @description: en - Convert number to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:39 下午
     * @param number: 数字
     * @return byte[]
    */
    public static byte[] numberToBytes(Number number){
        return numberToBytes(number, ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * [将Number转换为byte数组](Convert number to byte array)
     * @description: zh - 将Number转换为byte数组
     * @description: en - Convert number to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:40 下午
     * @param number: 数字
     * @param byteOrder: 端序
     * @return byte[]
    */
    public static byte[] numberToBytes(Number number, ByteOrder byteOrder){
        if(number instanceof Double){
            return doubleToBytes((Double) number, byteOrder);
        } else if(number instanceof Long){
            return longToBytes((Long) number, byteOrder);
        } else if(number instanceof Integer){
            return intToBytes((Integer) number, byteOrder);
        } else if(number instanceof Short){
            return shortToBytes((Short) number, byteOrder);
        } else{
            return doubleToBytes(number.doubleValue(), byteOrder);
        }
    }

}
