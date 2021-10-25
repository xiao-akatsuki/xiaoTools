package com.xiaoTools.core.convert.converterRegistry;

import com.xiaoTools.core.convert.arrayConverter.ArrayConverter;
import com.xiaoTools.core.convert.atomicBooleanConverter.AtomicBooleanConverter;
import com.xiaoTools.core.convert.atomicIntegerArrayConverter.AtomicIntegerArrayConverter;
import com.xiaoTools.core.convert.atomicLongArrayConverter.AtomicLongArrayConverter;
import com.xiaoTools.core.convert.atomicReferenceConverter.AtomicReferenceConverter;
import com.xiaoTools.core.convert.booleanConverter.BooleanConverter;
import com.xiaoTools.core.convert.calendarConverter.CalendarConverter;
import com.xiaoTools.core.convert.characterConverter.CharacterConverter;
import com.xiaoTools.core.convert.charsetConverter.CharsetConverter;
import com.xiaoTools.core.convert.classConverter.ClassConverter;
import com.xiaoTools.core.convert.collectionConverter.CollectionConverter;
import com.xiaoTools.core.convert.converter.Converter;
import com.xiaoTools.core.convert.currencyConverter.CurrencyConverter;
import com.xiaoTools.core.convert.dateConverter.DateConverter;
import com.xiaoTools.core.convert.durationConverter.DurationConverter;
import com.xiaoTools.core.convert.enumConverter.EnumConverter;
import com.xiaoTools.core.convert.localeConverte.LocaleConverter;
import com.xiaoTools.core.convert.mapConverter.MapConverter;
import com.xiaoTools.core.convert.numberConverter.NumberConverter;
import com.xiaoTools.core.convert.optionalConverter.OptionalConverter;
import com.xiaoTools.core.convert.pathConverter.PathConverter;
import com.xiaoTools.core.convert.periodConverter.PeriodConverter;
import com.xiaoTools.core.convert.primitiveConverter.PrimitiveConverter;
import com.xiaoTools.core.convert.referenceConverter.ReferenceConverter;
import com.xiaoTools.core.convert.stackTraceElementConverter.StackTraceElementConverter;
import com.xiaoTools.core.convert.stringConverter.StringConverter;
import com.xiaoTools.core.convert.temporalAccessorConverter.TemporalAccessorConverter;
import com.xiaoTools.core.convert.timeZoneConverter.TimeZoneConverter;
import com.xiaoTools.core.convert.typeReference.TypeReference;
import com.xiaoTools.core.convert.uRIConverter.URIConverter;
import com.xiaoTools.core.convert.uRLConverter.URLConverter;
import com.xiaoTools.core.convert.uUIDConverter.UUIDConverter;
import com.xiaoTools.core.exception.convertException.ConvertException;
import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.beanUtil.BeanUtil;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.serviceLoaderUtil.ServiceLoaderUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;


import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
/**
 * [类型转换器登记中心](Type converter registry)
 * @description: zh - 类型转换器登记中心
 * @description: en - Type converter registry
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 8:38 上午
*/
public class ConverterRegistry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认类型转换器
     */
    private Map<Type, Converter<?>> defaultConverterMap;

    /**
     * 用户自定义类型转换器
     */
    private volatile Map<Type, Converter<?>> customConverterMap;

    /**
     * [类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载](Class level inner class, that is, static member type inner class. The instance of the inner class has no binding relationship with the instance of the outer class, and it will be loaded only when it is called, thus realizing delayed loading)
     * @description: zh - 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     * @description: en - Class level inner class, that is, static member type inner class. The instance of the inner class has no binding relationship with the instance of the outer class, and it will be loaded only when it is called, thus realizing delayed loading
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:45 上午
    */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static final ConverterRegistry INSTANCE = new ConverterRegistry();
    }

    /**
     * [获得单例的 ConverterRegistry](Get the ConverterRegistry of singleton)
     * @description: zh - 获得单例的 ConverterRegistry
     * @description: en - Get the ConverterRegistry of singleton
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:46 上午
     * @return com.xiaoTools.core.convert.converterRegistry.ConverterRegistry
    */
    public static ConverterRegistry getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * [无参方法](Nonparametric method)
     * @description: zh - 无参方法
     * @description: en - Nonparametric method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:46 上午
    */
    public ConverterRegistry() {
        defaultConverter();
        putCustomBySpi();
    }

    /*加载转换器--------------------------------------------------------------------SPI*/

    /**
     * [使用SPI加载转换器](Loading converter with SPI)
     * @description: zh - 使用SPI加载转换器
     * @description: en - Loading converter with SPI
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:58 上午
    */
    private void putCustomBySpi() {
        ServiceLoaderUtil.load(Converter.class).forEach(converter -> {
            try {
                Type type = TypeUtil.getTypeArgument(ClassUtil.getClass(converter));
                if(null != type){
                    putCustom(type, converter);
                }
            } catch (Exception e) {
                // 忽略注册失败的
            }
        });
    }

    /**
     * [登记自定义转换器](Register custom converter)
     * @description: zh - 登记自定义转换器
     * @description: en - Register custom converter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:57 上午
     * @param type: 转换的目标类型
     * @param converterClass: 转换器类，必须有默认构造方法
     * @return com.xiaoTools.core.convert.converterRegistry.ConverterRegistry
    */
    public ConverterRegistry putCustom(Type type, Class<? extends Converter<?>> converterClass) {
        return putCustom(type, ReflectUtil.newInstance(converterClass));
    }

    /**
     * [登记自定义转换器](Register custom converter)
     * @description: zh - 登记自定义转换器
     * @description: en - Register custom converter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:55 上午
     * @param type:  转换的目标类型
     * @param converter: 转换器
     * @return com.xiaoTools.core.convert.converterRegistry.ConverterRegistry
    */
    public ConverterRegistry putCustom(Type type, Converter<?> converter) {
        if (Constant.NULL == customConverterMap) {
            synchronized (this) {
                if (Constant.NULL == customConverterMap) {
                    customConverterMap = new ConcurrentHashMap<>();
                }
            }
        }
        customConverterMap.put(type, converter);
        return this;
    }

	/**
	 * [获得转换器](Get converter)
	 * @description zh - 获得转换器
	 * @description en - Get converter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:11:11
	 * @param type 类型
	 * @param isCustomFirst 是否自定义转换器优先
	 * @return com.xiaoTools.core.convert.converter.Converter<T>
	 */
	public <T> Converter<T> getConverter(Type type, boolean isCustomFirst) {
		Converter<T> converter;
		if (isCustomFirst) {
			converter = this.getCustomConverter(type);
			if (null == converter) {
				converter = this.getDefaultConverter(type);
			}
		} else {
			converter = this.getDefaultConverter(type);
			if (null == converter) {
				converter = this.getCustomConverter(type);
			}
		}
		return converter;
	}

	/**
	 * [获得默认转换器](Get default converter)
	 * @description zh - 获得默认转换器
	 * @description en - Get default converter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:12:28
	 * @param type 类型
	 * @return com.xiaoTools.core.convert.converter.Converter<T>
	 */
	@SuppressWarnings("unchecked")
	public <T> Converter<T> getDefaultConverter(Type type) {
		return (null == defaultConverterMap) ? null : (Converter<T>) defaultConverterMap.get(type);
	}

	/**
	 * [获得自定义转换器](Get custom converter)
	 * @description zh - 获得自定义转换器
	 * @description en - Get custom converter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:13:26
	 * @param type 类型
	 * @return com.xiaoTools.core.convert.converter.Converter<T>
	 */
	@SuppressWarnings("unchecked")
	public <T> Converter<T> getCustomConverter(Type type) {
		return (null == customConverterMap) ? null : (Converter<T>) customConverterMap.get(type);
	}

	/**
	 * [转换值为指定类型](Converts the value to the specified type)
	 * @description zh - 转换值为指定类型
	 * @description en - Converts the value to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:15:07
	 * @param type 类型目标
	 * @param value 被转换值
	 * @param defaultValue 默认值
	 * @param isCustomFirst 是否自定义转换器优先
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T convert(Type type, Object value, T defaultValue, boolean isCustomFirst) throws ConvertException {
		if (TypeUtil.isUnknown(type) && null == defaultValue) {
			// 对于用户不指定目标类型的情况，返回原值
			return (T) value;
		}
		if (ObjectUtil.isNull(value)) {
			return defaultValue;
		}
		if (TypeUtil.isUnknown(type)) {
			type = defaultValue.getClass();
		}

		if (type instanceof TypeReference) {
			type = ((TypeReference<?>) type).getType();
		}

		// 标准转换器
		final Converter<T> converter = getConverter(type, isCustomFirst);
		if (null != converter) {
			return converter.convert(value, defaultValue);
		}

		Class<T> rowType = (Class<T>) TypeUtil.getClass(type);
		if (null == rowType) {
			if (null != defaultValue) {
				rowType = (Class<T>) defaultValue.getClass();
			} else {
				// 无法识别的泛型类型，按照Object处理
				return (T) value;
			}
		}


		// 特殊类型转换，包括Collection、Map、强转、Array等
		final T result = convertSpecial(type, rowType, value, defaultValue);
		if (null != result) {
			return result;
		}

		// 尝试转Bean
		if (BeanUtil.isBean(rowType)) {
			return new BeanConverter<T>(type).convert(value, defaultValue);
		}

		// 无法转换
		throw new ConvertException("Can not Converter from [{}] to [{}]", value.getClass().getName(), type.getTypeName());
	}

	/**
	 * [转换值为指定类型](Converts the value to the specified type)
	 * @description zh - 转换值为指定类型
	 * @description en - Converts the value to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:16:51
	 * @param type 类型
	 * @param value 值
	 * @param defaultValue 默认值
	 * @throws com.xiaoTools.core.exception.convertException.ConvertException
	 * @return T
	 */
	public <T> T convert(Type type, Object value, T defaultValue) throws ConvertException {
		return convert(type, value, defaultValue, true);
	}

	/**
	 * [转换值为指定类型](Converts the value to the specified type)
	 * @description zh - 转换值为指定类型
	 * @description en - Converts the value to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:18:41
	 * @param type 类型
	 * @param value 值
	 * @throws com.xiaoTools.core.exception.convertException.ConvertException
	 * @return T
	 */
	public <T> T convert(Type type, Object value) throws ConvertException {
		return convert(type, value, null);
	}

	/**
	 * [特殊类型转换](Special type conversion)
	 * @description zh - 特殊类型转换
	 * @description en - Special type conversion
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:21:18
	 * @param type 类型
	 * @param value 值
	 * @param defaultValue 默认值
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	private <T> T convertSpecial(Type type, Class<T> rowType, Object value, T defaultValue) {
		if (null == rowType) {
			return null;
		}

		// 集合转换（不可以默认强转）
		if (Collection.class.isAssignableFrom(rowType)) {
			final CollectionConverter collectionConverter = new CollectionConverter(type);
			return (T) collectionConverter.convert(value, (Collection<?>) defaultValue);
		}

		// Map类型（不可以默认强转）
		if (Map.class.isAssignableFrom(rowType)) {
			final MapConverter mapConverter = new MapConverter(type);
			return (T) mapConverter.convert(value, (Map<?, ?>) defaultValue);
		}

		// 默认强转
		if (rowType.isInstance(value)) {
			return (T) value;
		}

		// 枚举转换
		if (rowType.isEnum()) {
			return (T) new EnumConverter(rowType).convert(value, defaultValue);
		}

		// 数组转换
		if (rowType.isArray()) {
			final ArrayConverter arrayConverter = new ArrayConverter(rowType);
			return (T) arrayConverter.convert(value, defaultValue);
		}

		// 表示非需要特殊转换的对象
		return null;
	}

    /*默认转换器--------------------------------------------------------------------defaultConverter*/

    private ConverterRegistry defaultConverter() {
        defaultConverterMap = new ConcurrentHashMap<>();

		// 原始类型转换器
		defaultConverterMap.put(int.class, new PrimitiveConverter(int.class));
		defaultConverterMap.put(long.class, new PrimitiveConverter(long.class));
		defaultConverterMap.put(byte.class, new PrimitiveConverter(byte.class));
		defaultConverterMap.put(short.class, new PrimitiveConverter(short.class));
		defaultConverterMap.put(float.class, new PrimitiveConverter(float.class));
		defaultConverterMap.put(double.class, new PrimitiveConverter(double.class));
		defaultConverterMap.put(char.class, new PrimitiveConverter(char.class));
		defaultConverterMap.put(boolean.class, new PrimitiveConverter(boolean.class));

		// 包装类转换器
		defaultConverterMap.put(Number.class, new NumberConverter());
		defaultConverterMap.put(Integer.class, new NumberConverter(Integer.class));
		defaultConverterMap.put(AtomicInteger.class, new NumberConverter(AtomicInteger.class));
		defaultConverterMap.put(Long.class, new NumberConverter(Long.class));
		defaultConverterMap.put(AtomicLong.class, new NumberConverter(AtomicLong.class));
		defaultConverterMap.put(Byte.class, new NumberConverter(Byte.class));
		defaultConverterMap.put(Short.class, new NumberConverter(Short.class));
		defaultConverterMap.put(Float.class, new NumberConverter(Float.class));
		defaultConverterMap.put(Double.class, new NumberConverter(Double.class));
		defaultConverterMap.put(Character.class, new CharacterConverter());
		defaultConverterMap.put(Boolean.class, new BooleanConverter());
		defaultConverterMap.put(AtomicBoolean.class, new AtomicBooleanConverter());
		defaultConverterMap.put(BigDecimal.class, new NumberConverter(BigDecimal.class));
		defaultConverterMap.put(BigInteger.class, new NumberConverter(BigInteger.class));

		defaultConverterMap.put(CharSequence.class, new StringConverter());
		defaultConverterMap.put(String.class, new StringConverter());

		// URI and URL
		defaultConverterMap.put(URI.class, new URIConverter());
		defaultConverterMap.put(URL.class, new URLConverter());

		// 日期时间
		defaultConverterMap.put(Calendar.class, new CalendarConverter());
		defaultConverterMap.put(java.util.Date.class, new DateConverter(java.util.Date.class));
		defaultConverterMap.put(DateTime.class, new DateConverter(DateTime.class));
		defaultConverterMap.put(java.sql.Date.class, new DateConverter(java.sql.Date.class));
		defaultConverterMap.put(java.sql.Time.class, new DateConverter(java.sql.Time.class));
		defaultConverterMap.put(java.sql.Timestamp.class, new DateConverter(java.sql.Timestamp.class));

		// 日期时间 JDK8+
		defaultConverterMap.put(TemporalAccessor.class, new TemporalAccessorConverter(Instant.class));
		defaultConverterMap.put(Instant.class, new TemporalAccessorConverter(Instant.class));
		defaultConverterMap.put(LocalDateTime.class, new TemporalAccessorConverter(LocalDateTime.class));
		defaultConverterMap.put(LocalDate.class, new TemporalAccessorConverter(LocalDate.class));
		defaultConverterMap.put(LocalTime.class, new TemporalAccessorConverter(LocalTime.class));
		defaultConverterMap.put(ZonedDateTime.class, new TemporalAccessorConverter(ZonedDateTime.class));
		defaultConverterMap.put(OffsetDateTime.class, new TemporalAccessorConverter(OffsetDateTime.class));
		defaultConverterMap.put(OffsetTime.class, new TemporalAccessorConverter(OffsetTime.class));
		defaultConverterMap.put(Period.class, new PeriodConverter());
		defaultConverterMap.put(Duration.class, new DurationConverter());

		// Reference
		defaultConverterMap.put(WeakReference.class, new ReferenceConverter(WeakReference.class));
		defaultConverterMap.put(SoftReference.class, new ReferenceConverter(SoftReference.class));
		defaultConverterMap.put(AtomicReference.class, new AtomicReferenceConverter());

		//AtomicXXXArray
		defaultConverterMap.put(AtomicIntegerArray.class, new AtomicIntegerArrayConverter());
		defaultConverterMap.put(AtomicLongArray.class, new AtomicLongArrayConverter());

		// 其它类型
		defaultConverterMap.put(Class.class, new ClassConverter());

		defaultConverterMap.put(TimeZone.class, new TimeZoneConverter());
		defaultConverterMap.put(Locale.class, new LocaleConverter());
		defaultConverterMap.put(Charset.class, new CharsetConverter());
		defaultConverterMap.put(Path.class, new PathConverter());
		defaultConverterMap.put(Currency.class, new CurrencyConverter());
		defaultConverterMap.put(UUID.class, new UUIDConverter());
		defaultConverterMap.put(StackTraceElement.class, new StackTraceElementConverter());
		defaultConverterMap.put(Optional.class, new OptionalConverter());

		return this;

    }
}
