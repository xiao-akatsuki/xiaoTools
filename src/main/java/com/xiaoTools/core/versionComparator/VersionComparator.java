package com.xiaoTools.core.versionComparator;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.objectUtil.ObjectUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class VersionComparator implements Comparator<String>, Serializable {

    @Serial
    private static final long serialVersionUID = 8083701245147495562L;

    /**
     * [单例模式](Singleton mode)
     * @description: zh - 单例模式
     * @description: en - Singleton mode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:23 下午
    */
    public static final VersionComparator INSTANCE = new VersionComparator();

    /**
     * [默认构造](Default construction)
     * @description: zh - 默认构造
     * @description: en - Default construction
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:23 下午
    */
    public VersionComparator() { }

    /**
     * [比较两个版本](Compare the two versions)
     * @description: zh - 比较两个版本
     * @description: en - Compare the two versions
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:29 下午
     * @param version1: 版本1
     * @param version2: 版本2
     * @return int
    */
    @Override
    public int compare(String version1, String version2) {
        if(ObjectUtil.equal(version1, version2)) {
            return Constant.ZERO;
        }
        if (version1 == Constant.NULL && version2 == Constant.NULL) {
            return Constant.ZERO;
        } else if (version1 == Constant.NULL) {
            // null视为最小版本，排在前
            return Constant.NEGATIVE_ONE;
        } else if (version2 == Constant.NULL) {
            return Constant.ONE;
        }

        final List<String> v1s = StrUtil.split(version1, Constant.CHAR_SPOT);
        final List<String> v2s = StrUtil.split(version2, Constant.CHAR_SPOT);

        int diff = Constant.ZERO;
        // 取最小长度值
        int minLength = Math.min(v1s.size(), v2s.size());
        String v1;
        String v2;
        for (int i = Constant.ZERO; i < minLength; i++) {
            v1 = v1s.get(i);
            v2 = v2s.get(i);
            // 先比较长度
            diff = v1.length() - v2.length();
            if (Constant.ZERO == diff) { diff = v1.compareTo(v2); }
            if(diff != Constant.ZERO) {
                //已有结果，结束
                break;
            }
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        return (diff != Constant.ZERO) ? diff : v1s.size() - v2s.size();
    }
}
