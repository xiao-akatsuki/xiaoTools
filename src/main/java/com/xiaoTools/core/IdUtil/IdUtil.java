package com.xiaoTools.core.IdUtil;

import com.xiaoTools.core.IdUtil.uuid.UUID;

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
     * [产生UUID，是基础的UUID，携带`-`的随机字符串](Generate UUID, is the basis of UUID, carry '-'random string)
     * @description: zh - 产生UUID，是基础的UUID，携带`-`的随机字符串
     * @description: en - Generate UUID, is the basis of UUID, carry '-'random string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 3:17 下午
     * @return java.lang.String
    */
    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * [产生了简化的UUID，去除了`-`的随机字符串](A simplified UUID is generated and the random string of '-' is removed)
     * @description: zh - 产生了简化的UUID，去除了`-`的随机字符串
     * @description: en - A simplified UUID is generated and the random string of '-' is removed
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 3:21 下午
     * @return java.lang.String
    */
    public static String simpleUUID(){
        return UUID.randomUUID().toString(true);
    }

    /**
     * [产生一个性能更快更稳定的UUID](Generate a faster and more stable UUID)
     * @description: zh - 产生一个性能更快更稳定的UUID
     * @description: en - Generate a faster and more stable UUID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 3:26 下午
     * @return java.lang.String
    */
    public static String fastUUID(){
        return UUID.fastUUID().toString();
    }

    /**
     * [产生性能更快并且没有`-`的UUID](Produce faster performance UUID without '-')
     * @description: zh - 产生性能更快并且没有`-`的UUID
     * @description: en - Produce faster performance UUID without '-'
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 3:28 下午
     * @return java.lang.String
    */
    public static String fastSimpleUUID(){
        return UUID.fastUUID().toString(true);
    }
}
