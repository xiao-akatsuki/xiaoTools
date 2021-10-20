package com.xiaoTools.core.convert.referenceConverter;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.core.convert.converterRegistry.ConverterRegistry;
import com.xiaoTools.util.strUtil.StrUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;

@SuppressWarnings("rawtypes")
public class ReferenceConverter extends AbstractConverter<Reference> {
	private static final long serialVersionUID = 1L;

	private final Class<? extends Reference> targetType;

	/**
	 * [构造](structure)
	 * @description zh - 构造
	 * @description en - structure
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 16:33:31
	 * @param targetType Reference实现类型
	 */
	public ReferenceConverter(Class<? extends Reference> targetType) {
		this.targetType = targetType;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Reference<?> convertInternal(Object value) {

		Object targetValue = null;
		final Type paramType = TypeUtil.getTypeArgument(targetType);
		if(false == TypeUtil.isUnknown(paramType)){
			targetValue = ConverterRegistry.getInstance().convert(paramType, value);
		}
		if(null == targetValue){
			targetValue = value;
		}

		if(this.targetType == WeakReference.class){
			return new WeakReference(targetValue);
		}else if(this.targetType == SoftReference.class){
			return new SoftReference(targetValue);
		}

		throw new UnsupportedOperationException(StrUtil.format("Unsupport Reference type: {}", this.targetType.getName()));
	}
}
