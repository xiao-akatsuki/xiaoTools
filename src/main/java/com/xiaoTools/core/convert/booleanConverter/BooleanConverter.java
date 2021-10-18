package com.xiaoTools.core.convert.booleanConverter;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.util.booleanUtil.BooleanUtil;

/**
 * [Boolean转换器](Boolean converter)
 * @description zh - Boolean转换器
 * @description en - Boolean converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-19 07:24:37
 */
public class BooleanConverter extends AbstractConverter<Boolean> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Boolean convertInternal(Object value) {
		return value instanceof Number ?
			0 != ((Number) value).doubleValue() :
			BooleanUtil.toBoolean(convertToStr(value));
	}

}
