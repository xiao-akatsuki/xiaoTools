package com.xiaoTools.core.IdUtil;

import java.util.UUID;

/**
 * [产生的随机ID工具类](Generated random ID tool class)
 * @description: zh - 产生的随机ID工具类
 * @description: en - Generated random ID tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/3 1:28 下午
*/
public class IdUtil {

    /**
     * [产生正常的UUID](Generate normal UUID)
     * @description: zh - 产生正常的UUID
     * @description: en - Generate normal UUID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/3 1:44 下午
     * @return java.lang.String
     */
    public static String createUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * [产生UUID模式](Generate UUID mode)
     * @description: zh - 产生全部小写UUID模式
     * @description: en - Generate all lowercase UUID patterns
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/3 1:31 下午
     * @return java.lang.String
    */
    public static String lowercaseUUID(){
        return createUUID()
                .replace("-","")
                .toLowerCase();
    }

    /**
     * [产生全部大写UUID模式](Generate all uppercase UUID mode)
     * @description: zh - 产生全部大写UUID模式
     * @description: en - Generate all uppercase UUID mode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/3 1:32 下午
     * @return java.lang.String
    */
    public static String capitalizedUUID(){
        return createUUID()
                .replace("-","")
                .toUpperCase();
    }






}
