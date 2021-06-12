package com.xiaoTools.core.arrayUtil.editor;

/**
 * [编辑器接口，常用于对于集合中的元素做统一编辑](Editor interface, commonly used for unified editing of elements in a collection)
 * @description: zh - 编辑器接口，常用于对于集合中的元素做统一编辑
 * @description: en - Editor interface, commonly used for unified editing of elements in a collection
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/12 5:34 下午
*/
@FunctionalInterface
public interface Editor<T> {
    /**
     * [修改过滤后的结果](Modify the filtered results)
     * @description: zh - 修改过滤后的结果
     * @description: en - Modify the filtered results
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 5:33 下午
     * @param t: 被过滤的对象
     * @return T
    */
    T edit(T t);
}
