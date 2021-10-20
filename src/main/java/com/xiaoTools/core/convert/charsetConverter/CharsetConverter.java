package com.xiaoTools.core.convert.charsetConverter;

import java.nio.charset.Charset;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.util.charsetUtil.CharsetUtil;

/**
 * [编码对象转换器](Encoding object converter)
 * @description zh - 编码对象转换器
 * @description en - Encoding object converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:55:27
 */
public class CharsetConverter extends AbstractConverter<Charset>{
	private static final long serialVersionUID = 1L;

	@Override
	protected Charset convertInternal(Object value) {
		return CharsetUtil.charset(convertToStr(value));
	}

}
