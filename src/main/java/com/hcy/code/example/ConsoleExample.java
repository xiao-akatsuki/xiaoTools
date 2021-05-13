package com.hcy.code.example;

import com.hcy.code.console.Console;

/**
 * 完成Console工具类的测试
 * @author HCY
 * @since 2021/5/13 7:37 上午
*/
public class ConsoleExample {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Console.log();
        Console.log("Hello World");

        Integer[] arr = {0,1,2};
        System.out.println(arr);
        Console.log(arr);

    }
}
