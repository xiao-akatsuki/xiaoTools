package com.xiaoTools.core.convert.localeConverte;

import java.util.Locale;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [Locale对象转换器](Locale Object Converter)
 * @description zh - Locale对象转换器
 * @description en - Locale Object Converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:52:11
 */
public class LocaleConverter extends AbstractConverter<Locale> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Locale convertInternal(Object value) {
		try {
			String str = convertToStr(value);
			if (StrUtil.isEmpty(str)) {
				return null;
			}

			final String[] items = str.split("_");
			if (items.length == Constant.ONE) {
				return new Locale(items[Constant.ZERO]);
			}
			if (items.length == Constant.TWO) {
				return new Locale(items[Constant.ZERO], items[Constant.ONE]);
			}
			return new Locale(items[Constant.ZERO], items[Constant.ONE], items[Constant.TWO]);
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}

}
