package com.xiaoTools.entity.beanCopier;

import java.io.Serializable;
import java.lang.constant.Constable;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;

import com.xiaoTools.core.exception.beanException.BeanException;
import com.xiaoTools.entity.copyOptions.CopyOptions;
import com.xiaoTools.entity.dynaBean.DynaBean;
import com.xiaoTools.entity.mapValueProvider.MapValueProvider;
import com.xiaoTools.entity.valueProvider.ValueProvider;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.lang.copier.Copier;
import com.xiaoTools.util.beanUtil.BeanUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.strUtil.StrUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;

public class BeanCopier<T> implements Copier<T>, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 源对象
	 */
	private final Object source;

	/**
	 * 目标对象
	 */
	private final T dest;

	/**
	 * 目标的类型（用于泛型类注入）
	 */
	private final Type destType;

	/**
	 * 拷贝选项
	 */
	private final CopyOptions copyOptions;

	/**
	 * [构造](structure)
	 * @description zh - 构造
	 * @description en - structure
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:12:34
	 * @param source 来源对象，可以是Bean或者Map
	 * @param dest 目标Bean对象
	 * @param destType 目标的泛型类型，用于标注有泛型参数的Bean对象
	 * @param copyOptions 拷贝属性选项
	 */
	public BeanCopier(Object source, T dest, Type destType, CopyOptions copyOptions) {
		this.source = source;
		this.dest = dest;
		this.destType = destType;
		this.copyOptions = copyOptions;
	}

	/**
	 * [创建BeanCopier](Create beancopier)
	 * @description zh - 创建BeanCopier
	 * @description en - Create beancopier
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:15:28
	 * @param source 来源对象，可以是Bean或者Map
	 * @param dest 目标Bean对象
	 * @param copyOptions 拷贝属性选项
	 * @return com.xiaoTools.entity.beanCopier.BeanCopier<T>
	 */
	public static <T> BeanCopier<T> create(Object source, T dest, CopyOptions copyOptions) {
		return create(source, dest, dest.getClass(), copyOptions);
	}

	/**
	 * [创建BeanCopier](Create beancopier)
	 * @description zh - 创建BeanCopier
	 * @description en - Create beancopier
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:16:34
	 * @param source 来源对象，可以是Bean或者Map
	 * @param dest 目标Bean对象
	 * @param destType 目标的泛型类型
	 * @param copyOptions 拷贝属性选项
	 * @return com.xiaoTools.entity.beanCopier.BeanCopier<T>
	 */
	public static <T> BeanCopier<T> create(Object source, T dest, Type destType, CopyOptions copyOptions) {
		return new BeanCopier<>(source, dest, destType, copyOptions);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T copy() {
		if (null != this.source) {
			if (this.source instanceof ValueProvider) {
				// 目标只支持Bean
				valueProviderToBean((ValueProvider<String>) this.source, this.dest);
			} else if (this.source instanceof DynaBean) {
				// 目标只支持Bean
				valueProviderToBean(new DynaBeanValueProvider((DynaBean) this.source, copyOptions.ignoreError), this.dest);
			} else if (this.source instanceof Map) {
				if (this.dest instanceof Map) {
					mapToMap((Map<?, ?>) this.source, (Map<?, ?>) this.dest);
				} else {
					mapToBean((Map<?, ?>) this.source, this.dest);
				}
			} else {
				if (this.dest instanceof Map) {
					beanToMap(this.source, (Map<?, ?>) this.dest);
				} else {
					beanToBean(this.source, this.dest);
				}
			}
		}

		return this.dest;
	}

	/**
	 * [Bean和Bean之间属性拷贝](Attribute copy between bean and bean)
	 * @description zh - Bean和Bean之间属性拷贝
	 * @description en - Attribute copy between bean and bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:18:36
	 * @param providerBean 来源Bean
	 * @param destBean 目标Bean
	 */
	private void beanToBean(Object providerBean, Object destBean) {
		valueProviderToBean(new BeanValueProvider(providerBean, this.copyOptions.ignoreCase, this.copyOptions.ignoreError), destBean);
	}

	/**
	 * [Map转Bean属性拷贝](Map to bean attribute copy)
	 * @description zh - Map转Bean属性拷贝
	 * @description en - Map to bean attribute copy
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:21:42
	 * @param map 集合
	 * @param bean  实体类
	 */
	private void mapToBean(Map<?, ?> map, Object bean) {
		valueProviderToBean(
				new MapValueProvider(map, this.copyOptions.ignoreCase, this.copyOptions.ignoreError),
				bean
		);
	}

	/**
	 * [Map 转 Map](Map to Map)
	 * @description zh - Map 转 Map
	 * @description en - Map to Map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:22:41
	 * @param source 原先的map
	 * @param dest 目标Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void mapToMap(Map source, Map dest) {
		if (null != dest && null != source) {
			dest.putAll(source);
		}
	}

	/**
	 * [对象转Map](Object to map)
	 * @description zh - 对象转Map
	 * @description en - Object to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:26:43
	 * @param bean 实体类
	 * @param targetMap 目标的Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void beanToMap(Object bean, Map targetMap) {
		final HashSet<String> ignoreSet = (null != copyOptions.ignoreProperties) ? CollUtil.newHashSet(copyOptions.ignoreProperties) : null;
		final CopyOptions copyOptions = this.copyOptions;

		BeanUtil.descForEach(bean.getClass(), (prop)->{
			if(Constant.FALSE == prop.isReadable(copyOptions.isTransientSupport())){
				return;
			}
			String key = prop.getFieldName();
			if (CollUtil.contains(ignoreSet, key)) {
				return;
			}

			key = copyOptions.editFieldName(copyOptions.getMappedFieldName(key, Constant.FALSE));
			if(null == key){
				return;
			}

			Object value;
			try {
				value = prop.getValue(bean);
			} catch (Exception e) {
				if (copyOptions.ignoreError) {
					return;
				} else {
					throw new BeanException(e, "Get value of [{}] error!", prop.getFieldName());
				}
			}
			if(null != copyOptions.propertiesFilter && Constant.FALSE == copyOptions.propertiesFilter.test(prop.getField(), value)) {
				return;
			}
			if ((null == value && copyOptions.ignoreNullValue) || bean == value) {
				return;
			}

			targetMap.put(key, value);
		});
	}

	/**
	 * [值提供器转Bean](Value provider to bean)
	 * @description zh - 值提供器转Bean
	 * @description en - Value provider to bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:28:27
	 * @param valueProvider 值提供器
	 * @param bean Bean
	 */
	private void valueProviderToBean(ValueProvider<String> valueProvider, Object bean) {
		if (null == valueProvider) {
			return;
		}

		final CopyOptions copyOptions = this.copyOptions;
		Class<?> actualEditable = bean.getClass();
		if (null != copyOptions.editable) {
			// 检查限制类是否为target的父类或接口
			if (Constant.FALSE == copyOptions.editable.isInstance(bean)) {
				throw new IllegalArgumentException(StrUtil.format("Target class [{}] not assignable to Editable class [{}]", bean.getClass().getName(), copyOptions.editable.getName()));
			}
			actualEditable = copyOptions.editable;
		}
		final HashSet<String> ignoreSet = (null != copyOptions.ignoreProperties) ? CollUtil.newHashSet(copyOptions.ignoreProperties) : null;

		BeanUtil.descForEach(actualEditable, (prop)->{
			if(Constant.FALSE == prop.isWritable(this.copyOptions.isTransientSupport())){
				return;
			}
			String fieldName = prop.getFieldName();
			if (CollUtil.contains(ignoreSet, fieldName)) {
				return;
			}

			final String providerKey = copyOptions.getMappedFieldName(fieldName, true);
			if (Constant.FALSE == valueProvider.containsKey(providerKey)) {
				return;
			}

			final Type fieldType = TypeUtil.getActualType(this.destType ,prop.getFieldType());

			Object value = valueProvider.value(providerKey, fieldType);
			if(null != copyOptions.propertiesFilter && Constant.FALSE == copyOptions.propertiesFilter.test(prop.getField(), value)) {
				return;
			}

			if ((null == value && copyOptions.ignoreNullValue) || bean == value) {
				return;
			}

			prop.setValue(bean, value, copyOptions.ignoreNullValue, copyOptions.ignoreError);
		});
	}
}
