package com.xiaoTools.core.convert.currencyConverter;

import java.util.Currency;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [货币Currency转换器](Currency converter)
 * @description zh - 货币Currency转换器
 * @description en - Currency converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 17:00:57
 */
public class CurrencyConverter extends AbstractConverter<Currency> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Currency convertInternal(Object value) {
		return Currency.getInstance(convertToStr(value));
	}

}
