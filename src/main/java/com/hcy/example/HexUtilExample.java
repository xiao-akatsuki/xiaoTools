package com.hcy.example;

import com.hcy.code.hexUtil.HexUtil;

/**
 * 十六进制工具类转换
 * @author HCY
 * @since 2021/5/15 2:43 下午
*/
public class HexUtilExample {
    public static void main(String[] args) {
        String str = "我是一个字符串";
        String hexStr = HexUtil.encodeHexStr(str);
        //e68891e698afe4b880e4b8aae5ad97e7aca6e4b8b2
        System.out.println(hexStr);
        //我是一个字符串
        System.out.println(HexUtil.decodeHexStr(hexStr));
    }
}
