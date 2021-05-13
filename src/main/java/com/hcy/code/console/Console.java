package com.hcy.code.console;

/**
 * 经典输出
 * @author HCY
 * @since 2021/5/13 7:34 上午
*/
public class Console {

    /**
     * 空参
     * @author HCY
     * @since 2021/5/13 7:40 上午
     * @return null
    */
    public Console() {
    }

    /**
     * 空输出
     * @author HCY
     * @since 2021/5/13 8:45 上午
     * @return void
    */
    public static void log(){
        System.out.println();
    }

    /**
     * 输出的文本
     * @author HCY
     * @since 2021/5/13 8:45 上午
     * @param value: 输出的文本
     * @return void
    */
    public static void log(Object value){
        System.out.println(value);

    }

    /**
     * 输出类似数组
     * @author HCY
     * @since 2021/5/13 8:45 上午
     * @param values: 输出的数组
     * @return void
    */
    public static void log(Object...values){
        String result = "";
        for (Object value : values) {
            result += value;
        }
        System.out.println(result);
    }
}
