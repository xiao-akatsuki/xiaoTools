package com.xiaoTools.core.convert.uRIConverter;

import java.io.File;
import java.net.URI;
import java.net.URL;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [URI对象转换器](Uri Object Converter)
 * @description zh - URI对象转换器
 * @description en - Uri Object Converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-19 07:36:34
 */
public class URIConverter extends AbstractConverter<URI> {

	private static final long serialVersionUID = 1L;

	@Override
	protected URI convertInternal(Object value) {
		try {
			if(value instanceof File){
				return ((File)value).toURI();
			}

			if(value instanceof URL){
				return ((URL)value).toURI();
			}
			return new URI(convertToStr(value));
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}

}
