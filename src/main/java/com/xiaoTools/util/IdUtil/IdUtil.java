package com.xiaoTools.util.IdUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.IdUtil.objectId.ObjectId;
import com.xiaoTools.util.IdUtil.snowflake.Snowflake;
import com.xiaoTools.util.IdUtil.uuid.UUID;

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
        return UUID.randomUUID().toString(Constant.TRUE);
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
        return UUID.fastUUID().toString(Constant.TRUE);
    }

    /**
     * [创建一个简单的ObjectID](Create a simple ObjectID)
     * @description: zh - 创建一个简单的ObjectID
     * @description: en - Create a simple ObjectID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 9:40 上午
     * @return java.lang.String
    */
    public static String objectId() {
        return ObjectId.nextId();
    }

    /**
     * [创建Twitter的Snowflake 算法生成器。](Create Twitter's snowflake algorithm generator.)
     * @description: zh - 创建Twitter的Snowflake 算法生成器。
     * @description: en - Create Twitter's snowflake algorithm generator.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 9:03 上午
     * @param workId: [终端ID](Terminal ID)
     * @param datacenterId: [数据中心ID](Data center ID)
     * @return com.xiaoTools.util.IdUtil.snowflake.Snowflake
    */
    public static Snowflake createSnowflakeId(long workId,long datacenterId){
        return new Snowflake(workId,datacenterId);
    }

}
