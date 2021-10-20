package com.xiaoTools.core.convert.stackTraceElementConverter;

import java.util.Map;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

/**
 * [StackTraceElement转换器](StackTraceElement converter)
 * @description zh - StackTraceElement转换器
 * @description en - StackTraceElement converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 17:05:06
 */
public class StackTraceElementConverter extends AbstractConverter<StackTraceElement> {
	private static final long serialVersionUID = 1L;

	@Override
	protected StackTraceElement convertInternal(Object value) {
		if (value instanceof Map) {
			final Map<?, ?> map = (Map<?, ?>) value;

			final String declaringClass = MapUtil.getStr(map, "className");
			final String methodName = MapUtil.getStr(map, "methodName");
			final String fileName = MapUtil.getStr(map, "fileName");
			final Integer lineNumber = MapUtil.getInt(map, "lineNumber");

			return new StackTraceElement(declaringClass, methodName, fileName, ObjectUtil.defaultIfNull(lineNumber, 0));
		}
		return null;
	}

}
