package com.xiaoTools.core.convert.classConverter;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.util.classLoaderUtil.ClassLoaderUtil;

/**
 * [类转换器](Class converter)
 * @description zh - 类转换器
 * @description en - Class converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:48:27
 */
public class ClassConverter extends AbstractConverter<Class<?>> {
	private static final long serialVersionUID = 1L;

	private final boolean isInitialized;

	public ClassConverter() {
		this(true);
	}

	public ClassConverter(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}

	@Override
	protected Class<?> convertInternal(Object value) {
		return ClassLoaderUtil.loadClass(convertToStr(value), isInitialized);
	}

}
