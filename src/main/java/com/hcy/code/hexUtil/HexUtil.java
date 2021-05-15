package com.hcy.code.hexUtil;

/**
 * 16进制工具
 * @author HCY
 * @since 2021/5/15 12:10 下午
*/
public class HexUtil {
    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * 初始化项目
     * @author HCY
     * @since 2021/5/15 12:11 下午
     * @return null
    */
    public HexUtil() { }

    /**
     * 将字符串转换成为16进制
     * @author HCY
     * @since 2021/5/15 2:38 下午
     * @param value: 需要编码的字符串
     * @return java.lang.String
    */
    public static String encodeHexStr(String value){
        StringBuilder sb =  new  StringBuilder( "" );
        byte [] bs = value.getBytes();
        int  bit;
        for ( int i = 0 ; i < bs.length; i++) {
             bit = (bs[i] & 0x0f0 ) >> 4 ;
             sb.append(DIGITS_LOWER[bit]);
             bit = bs[i] & 0x0f ;
             sb.append(DIGITS_LOWER[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 将十六进制的字符串转换成为字符串
     * @author HCY
     * @since 2021/5/15 2:54 下午
     * @param value: 十六进制的字符串
     * @return java.lang.String
    */
    public static String decodeHexStr(String value){
        String str =  "0123456789abcdef" ;
        char [] hexs = value.toCharArray();
        byte [] bytes =  new  byte [value.length() /  2 ];
        int  n;
        for  ( int  i =  0 ; i < bytes.length; i++) {
            n = str.indexOf(hexs[ 2  * i]) *  16 ;
            n += str.indexOf(hexs[ 2  * i +  1 ]);
            bytes[i] = ( byte ) (n &  0xff );
        }
        return  new  String(bytes);
    }
}
