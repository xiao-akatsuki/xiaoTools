package com.xiaoTools.core.convert.characterConverter;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.util.booleanUtil.BooleanUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [字符转换器](Character converter)
 * @description zh - 字符转换器
 * @description en - Character converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-18 20:14:30
 */
public class CharacterConverter extends AbstractConverter<Character> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Character convertInternal(Object value) {
		if (value instanceof Boolean) {
			return BooleanUtil.toCharacter((Boolean) value);
		} else {
			final String valueStr = convertToStr(value);
			if (StrUtil.isNotBlank(valueStr)) {
				return valueStr.charAt(0);
			}
		}
		return null;
	}

}
