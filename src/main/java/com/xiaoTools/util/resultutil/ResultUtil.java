package com.xiaoTools.util.resultutil;

import com.xiaoTools.core.result.Result;

/**
 * [返回值工具类](return value tools)
 * @description: zh - 返回值工具类
 * @description: en - return value tools
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/7/24 8:33 下午
*/
public class ResultUtil {

    /**
     * [成功的 return value](Successful return value)
     * @description: zh - 成功的 return value
     * @description: en - Successful return value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:35 下午
     * @param value: message Value
     * @param path: URL path
     * @return com.xiaoTools.core.result.Result
    */
    public Result success(Object value,String path){
        return new Result().result200(value,path);
    }

    /**
     * [请求的地方为多处](Multiple places requested)
     * @description: zh - 请求的地方为多处
     * @description: en - Multiple places requested
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:38 下午
     * @param value: message Value
     * @param path: URL path
     * @return com.xiaoTools.core.result.Result
    */
    public Result complete(Object value,String path){
        return new Result().result300(value,path);
    }

    /**
     * [客户端请求有语法错误。](The client request has a syntax error.)
     * @description: 客户端请求有语法错误。
     * @description: The client request has a syntax error.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:43 下午
     * @param value: message Value
     * @param path: URL path
     * @return com.xiaoTools.core.result.Result
    */
    public Result syntax(Object value,String path){
        return new Result().result400(value,path);
    }

    /**
     * [服务器错误。](Server error.)
     * @description: zh - 服务器错误。
     * @description: en -Server error.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:44 下午
     * @param value: message Value
     * @param path: URL path
     * @return com.xiaoTools.core.result.Result
    */
    public Result error(Object value,String path){
        return new Result().result500(value,path);
    }


}
