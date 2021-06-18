package com.xiaoTools.date.leven;

import com.xiaoTools.lang.constant.Constant;

public enum Level {

    /**
     * 天
     */
    DAY(Constant.STRING_DAY),
    /**
     * 小时
     */
    HOUR(Constant.STRING_HOUSE),
    /**
     * 分钟
     */
    MINUTE(Constant.STRING_MINUTE),
    /**
     * 秒
     */
    SECOND(Constant.STRING_SECOND),
    /**
     * 毫秒
     */
    MILLISECOND(Constant.STRING_MILLISECOND);

    /**
     * 级别名称
     */
    private final String name;

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:21 下午
     * @param name: 级别名称
    */
    Level(String name) {
        this.name = name;
    }

    /**
     * [获取级别名称](Get level name)
     * @description: zh - 获取级别名称
     * @description: en - Get level name
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:21 下午
     * @return java.lang.String
    */
    public String getName() {
        return this.name;
    }
}
