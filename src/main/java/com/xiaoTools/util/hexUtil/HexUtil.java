package com.xiaoTools.util.hexUtil;

import com.xiaoTools.lang.constant.Constant;

/**
 * [16进制工具](Hex tool)
 * @author XiaoXunYao
 * @since 2021/5/15 12:10 下午
*/
public class HexUtil {
    /**
     * zh - 十六进制的单个字符串
     * en - Single string in hexadecimal
     */
    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String HEXADECIMAL_STRING= "0123456789abcdef";

    /**
     * [初始化项目](Initialize project)
     * @author XiaoXunYao
     * @since 2021/5/15 12:11 下午
    */
    public HexUtil() { }

    /**
     * [将字符串转换成为16进制](Converts a string to hexadecimal)
     * @description: zh - 输入字符串将字符串转换成为16进制的字符串。
     * @description: en - Input string converts the string to a hexadecimal string.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/15 2:38 下午
     * @param value: [需要编码的字符串](String to encode)
     * @return java.lang.String
    */
    public static String encodeHexStr(String value){
        StringBuilder sb =  new  StringBuilder( Constant.EMPTY );
        byte [] bs = value.getBytes();
        int  bit;
        for (byte b : bs) {
            bit = (b & Constant.BYTE_ONE) >> Constant.FOUR;
            sb.append(DIGITS_LOWER[bit]);
            bit = b & Constant.BYTE_ZERO;
            sb.append(DIGITS_LOWER[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * [将十六进制的字符串转换成为字符串](Converts a hexadecimal string to a string)
     * @description: zh - 输入十六进制的字符串将该字符串转换成为字符串
     * @description: en - Enter a string in hexadecimal to convert the string to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/15 2:54 下午
     * @param value: [十六进制的字符串](Hexadecimal string)
     * @return java.lang.String
    */
    public static String decodeHexStr(String value){
        char [] hexs = value.toCharArray();
        byte [] bytes =  new  byte [value.length() /  Constant.TWO ];
        int  n;
        for  ( int  i =  Constant.ZERO ; i < bytes.length; i++) {
            n = HEXADECIMAL_STRING.indexOf(hexs[ Constant.TWO  * i]) *  Constant.SIXTEEN ;
            n += HEXADECIMAL_STRING.indexOf(hexs[ Constant.TWO  * i +  Constant.ONE ]);
            bytes[i] = ( byte ) (n &  Constant.BYTE_TWO );
        }
        return  new  String(bytes);
    }
}