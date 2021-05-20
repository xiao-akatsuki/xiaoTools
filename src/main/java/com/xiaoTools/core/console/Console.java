package com.xiaoTools.core.console;

/**
 * [经典输出](Classic output)
 * @author HCY
 * @since 2021/5/13 7:34 上午
*/
public class Console {

    /**
     * [初始化方法](Initialization method)
     * @version: V1.0
     * @author HCY
     * @since 2021/5/13 7:40 上午
    */
    public Console() {
    }

    /**
     * [空输出](Null output)
     * @description: zh - 输出空行，可用于换行或者空输出
     * @description: en - Output empty line, can be used for line feed or empty output
     * @version: V1.0
     * @author HCY
     * @since 2021/5/13 8:45 上午
    */
    public static void log(){
        System.out.println();
    }

    /**
     * [输出的文本](Output text)
     * @description: zh - 输出我们所需要的大部分内容会直接输出
     * @description: en - Output most of what we need will be output directly
     * @version: V1.0
     * @author HCY
     * @since 2021/5/13 8:45 上午
     * @param value: [输出的文本](Output text)
    */
    public static void log(Object value){
        System.out.println(value);

    }

    /**
     * [输出类似数组](Output similar to array)
     * @description: zh - 将输入的数组进行循环输出
     * @description: en - Loop the input array
     * @version: V1.0
     * @author HCY
     * @since 2021/5/13 8:45 上午
     * @param values: [输出的数组](Array of output)
    */
    public static void log(Object...values){
        StringBuilder result = new StringBuilder();
        for (Object value : values) {
            result.append(value);
        }
        System.out.println(result);
    }
}
