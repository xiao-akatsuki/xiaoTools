package com.xiaoTools.core.IdUtil.uuid;

import java.io.Serial;
import java.io.Serializable;

/**
 * [重写创建UUID的生成策略](Override the build policy for creating UUIDs)
 * @description: zh - 重写创建UUID的生成策略
 * @description: en - Override the build policy for creating UUIDs
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/3 4:01 下午
*/
public class UUID implements Serializable {

    @Serial
    private static final long serialVersionUID = -1185015143654744140L;

    /**
     * [最大信号位](Maximum signal bit)
     */
    private final long mostSigBits;
    /**
     * [最小信号位](Minimum signal bit)
     */
    private final long leastSigBits;

    /**
     * [产生的UUID有参方法。](The generated UUID is a parametric method.)
     * @description: zh - 产生的UUID有参方法。
     * @description: en - The generated UUID is a parametric method.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/4 8:43 上午
     * @param mostSigBits: [最大信号位](most Sig Bits)
     * @param leastSigBits: [最小信号位](least Sig Bits)
    */
    public UUID(long mostSigBits, long leastSigBits) {
        this.mostSigBits = mostSigBits;
        this.leastSigBits = leastSigBits;
    }
}
