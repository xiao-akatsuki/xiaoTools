package com.xiaoTools.entity.pinyinComparator;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * [按照GBK拼音顺序对给定的汉字字符串排序](Sort the given Chinese character string according to GBK Pinyin order)
 * @description: zh - 按照GBK拼音顺序对给定的汉字字符串排序
 * @description: en - Sort the given Chinese character string according to GBK Pinyin order
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 9:27 上午
*/
public class PinyinComparator implements Comparator<String>, Serializable {
    private static final long serialVersionUID = 1L;

    final Collator collator;

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:27 上午
    */
    public PinyinComparator() {
        collator = Collator.getInstance(Locale.CHINESE);
    }

    @Override
    public int compare(String o1, String o2) {
        return collator.compare(o1, o2);
    }
}
