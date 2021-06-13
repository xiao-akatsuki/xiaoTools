package com.xiaoTools.core.object;

import com.xiaoTools.core.numUtil.NumUtil;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * [对象工具类，包括判空、克隆、序列化等操作](Object tool class, including null, clone, serialization and other operations)
 * @description: zh - 对象工具类，包括判空、克隆、序列化等操作
 * @description: en - Object tool class, including null, clone, serialization and other operations
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/13 11:03 上午
*/
public class ObjectUtil {
    public static boolean equals(Object obj1, Object obj2){
        if (obj1 instanceof BigDecimal && obj2 instanceof BigDecimal) {
            return NumUtil.equals((BigDecimal) obj1, (BigDecimal) obj2);
        }
        return Objects.equals(obj1, obj2);
    }
}
