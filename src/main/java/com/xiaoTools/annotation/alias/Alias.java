package com.xiaoTools.annotation.alias;

import java.lang.annotation.*;

/**
 *
 * @description: zh - Bean注解
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/16 3:12 下午
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Alias {

    /**
     *
     * @description: zh - 别名值，即使用此注解要替换成的别名名称
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 3:13 下午
     * @return java.lang.String
    */
    String value();
}
