package com.xiaoTools.core.convert.atomicReferenceConverter;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.core.convert.converterRegistry.ConverterRegistry;
import com.xiaoTools.util.typeUtil.TypeUtil;

/**
 * [AtomicReference转换器](Atomicreference converter)
 * @description zh - AtomicReference转换器
 * @description en - Atomicreference converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:39:14
 */
@SuppressWarnings("rawtypes")
public class AtomicReferenceConverter extends AbstractConverter<AtomicReference> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AtomicReference<?> convertInternal(Object value) {

		Object targetValue = null;
		final Type paramType = TypeUtil.getTypeArgument(AtomicReference.class);
		if(false == TypeUtil.isUnknown(paramType)){
			targetValue = ConverterRegistry.getInstance().convert(paramType, value);
		}
		if(null == targetValue){
			targetValue = value;
		}

		return new AtomicReference<>(targetValue);
	}
}
