package com.xiaoTools.core.basicType;

import com.xiaoTools.lang.constant.Constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @description: zh - 基本变量类型的枚举
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/15 8:17 下午
*/
public enum BasicType {
    /**
     * [列举出的基本数据类型](Basic data types listed)
     * @description: zh - 列举出的基本数据类型
     * @description: en - Basic data types listed
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:19 下午
    */
    BYTE, SHORT, INT, INTEGER, LONG, DOUBLE, FLOAT, BOOLEAN, CHAR, CHARACTER, STRING;

    /**
     * [包装类型为Key，原始类型为Value，例如： Integer.class => int.class.](The packing type is key and the original type is value, for example: integer. Class = > int.class)
     * @description: zh - 包装类型为Key，原始类型为Value，例如： Integer.class => int.class.
     * @description: en - The packing type is key and the original type is value, for example: integer. Class = > int.class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:18 下午
    */
    public static final Map<Class<?>, Class<?>> WRAPPER_PRIMITIVE_MAP = new ConcurrentHashMap<>(8);
    /** 原始类型为Key，包装类型为Value，例如： int.class =》 Integer.class. */
    public static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_MAP = new ConcurrentHashMap<>(8);

    static {
        WRAPPER_PRIMITIVE_MAP.put(Boolean.class, boolean.class);
        WRAPPER_PRIMITIVE_MAP.put(Byte.class, byte.class);
        WRAPPER_PRIMITIVE_MAP.put(Character.class, char.class);
        WRAPPER_PRIMITIVE_MAP.put(Double.class, double.class);
        WRAPPER_PRIMITIVE_MAP.put(Float.class, float.class);
        WRAPPER_PRIMITIVE_MAP.put(Integer.class, int.class);
        WRAPPER_PRIMITIVE_MAP.put(Long.class, long.class);
        WRAPPER_PRIMITIVE_MAP.put(Short.class, short.class);

        for (Map.Entry<Class<?>, Class<?>> entry : WRAPPER_PRIMITIVE_MAP.entrySet()) {
            PRIMITIVE_WRAPPER_MAP.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * [原始类转为包装类，非原始类返回原类](The original class is converted to a wrapper class, and the non original class returns the original class)
     * @description: zh - 原始类转为包装类，非原始类返回原类
     * @description: en - The original class is converted to a wrapper class, and the non original class returns the original class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:20 下午
     * @param clazz: 原始类
     * @return java.lang.Class<?>
    */
    public static Class<?> wrap(Class<?> clazz){
        if(Constant.NULL == clazz || !clazz.isPrimitive()){
            return clazz;
        }
        Class<?> result = PRIMITIVE_WRAPPER_MAP.get(clazz);
        return (Constant.NULL == result) ? clazz : result;
    }

    /**
     * [包装类转为原始类，非包装类返回原类](The packing class becomes the original class, and the non packing class returns the original class)
     * @description: zh - 包装类转为原始类，非包装类返回原类
     * @description: en - The packing class becomes the original class, and the non packing class returns the original class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:19 下午
     * @param clazz: 类
     * @return java.lang.Class<?>
    */
    public static Class<?> unWrap(Class<?> clazz){
        if(Constant.NULL == clazz || clazz.isPrimitive()){
            return clazz;
        }
        Class<?> result = WRAPPER_PRIMITIVE_MAP.get(clazz);
        return (Constant.NULL == result) ? clazz : result;
    }
}
