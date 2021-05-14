package com.hcy.code.example;

import com.hcy.code.strUtil.StrUtil;

/**
 * 字符串工具类
 * @author HCY
 * @since 2021/5/14 6:20 下午
*/
public class StrUtilExample {
    public static void main(String[] args) {
        //判断字符串是否为空
        System.out.println(StrUtil.isEmpty("123"));
        System.out.println(StrUtil.isEmpty("1 2 3"));
        System.out.println(StrUtil.isEmpty(" 123"));
        System.out.println(StrUtil.isEmpty("123 "));
        System.out.println(StrUtil.isEmpty(""));
        System.out.println(StrUtil.isEmpty(" "));
        System.out.println(StrUtil.isEmpty(null));

        System.out.println("------------------------------");

        System.out.println(StrUtil.isBlank(" "));
        System.out.println(StrUtil.isBlank(" 1"));

        System.out.println("------------------------------");

        //截取字符串
        String str = "abcdefgh";
        //strSub1 -> c
        System.out.println(StrUtil.sub(str, 2, 3));
        //strSub2 -> cde
        System.out.println(StrUtil.sub(str, 2, -3));
        //strSub2 -> c
        System.out.println(StrUtil.sub(str, 3, 2));

        System.out.println("------------------------------");

        System.out.println(StrUtil.delSuffix("pretty_girl.jpg", ".jpg"));
        System.out.println(StrUtil.delPrefix("abc", "a"));
    }
}
