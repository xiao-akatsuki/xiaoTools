package com.xiaoTools.date.format.basic;

import java.util.Locale;
import java.util.TimeZone;

/**
 * [日期基本信息获取接口](Date basic information acquisition interface)
 * @description: zh - 日期基本信息获取接口
 * @description: en - Date basic information acquisition interface
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 7:58 上午
*/
public interface DateBasic {

    /**
     * [获得日期格式化或者转换的格式](Get the format of date formatting or conversion)
     * @description: zh - 获得日期格式化或者转换的格式
     * @description: en - Get the format of date formatting or conversion
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:58 上午
     * @return java.lang.String
    */
    String getPattern();

    /**
     * [获得时区](Get time zone)
     * @description: zh - 获得时区
     * @description: en - Get time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 8:05 上午
     * @return java.util.TimeZone
    */
    TimeZone getTimeZone();

    /**
     * [获得 日期地理位置](Get date location)
     * @description: zh - 获得 日期地理位置
     * @description: en - Get date location
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 8:05 上午
     * @return java.util.Locale
    */
    Locale getLocale();
}
