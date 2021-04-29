package com.hcy.code.example;

import com.hcy.code.util.Result;

/**
 * Result 实例类
 * @author HCY
 * @since 2021/4/29 8:34 上午
*/
public class ResultExample {
    public static void main(String[] args) {
        Result result = result200();
    }

    /**
     * 其他都类似的
     * 只是修改了Result().result`200`()中的部分引号部分
     * @author HCY
     * @since 2021/4/29 8:42 上午
     * @return com.hcy.code.util.Result
    */
    private static Result result200() {
        return new Result().result200("[返回的具体信息](可以是万物)","[调用Controller的路径](API路径)");
    }
}
