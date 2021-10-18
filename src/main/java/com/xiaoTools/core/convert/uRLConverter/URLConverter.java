package com.xiaoTools.core.convert.uRLConverter;

import java.io.File;
import java.net.URI;
import java.net.URL;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [URL对象转换器](URL Object Converter)
 * @description zh - URL对象转换器
 * @description en - URL Object Converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-19 07:40:55
 */
public class URLConverter extends AbstractConverter<URL> {
	private static final long serialVersionUID = 1L;

	@Override
	protected URL convertInternal(Object value) {
		try {
			if(value instanceof File){
				return ((File)value).toURI().toURL();
			}

			if(value instanceof URI){
				return ((URI)value).toURL();
			}
			return new URL(convertToStr(value));
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}
}
