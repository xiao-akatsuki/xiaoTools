package com.xiaoTools.util.charsetUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.strUtil.StrUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/**
 * [字符集工具类](Character set tools)
 * @description: zh - 字符集工具类
 * @description: en - Character set tools
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/7/25 3:56 下午
*/
public class CharsetUtil {

    /**
     * ISO-8859-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * GBK
     */
    public static final String GBK = "GBK";

    /**
     * ISO-8859-1
     */
    public static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;

    /**
     * UTF-8
     */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;

    /**
     * GBK
     */
    public static final Charset CHARSET_GBK;

    /*静态代码------------------------------------------------------------ static*/

    static {
        //避免不支持GBK的系统中运行报错 issue#731
        Charset gbk = Constant.CHARSET_NULL;
        try {
            gbk = Charset.forName(GBK);
        } catch (UnsupportedCharsetException e) {
            //ignore
        }
        CHARSET_GBK = gbk;
    }

    /*转换为Charset对象------------------------------------------------------------ charset*/

    /**
     * [转换为Charset对象](Convert to charset object)
     * @description: zh - 转换为Charset对象
     * @description: en - Convert to charset object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/25 4:01 下午
     * @param charsetName: 字符集，为空则返回默认字符集
     * @return java.nio.charset.Charset
    */
    public static Charset charset(String charsetName) throws UnsupportedCharsetException {
        return StrUtil.isBlank(charsetName) ? Charset.defaultCharset() : Charset.forName(charsetName);
    }


}
