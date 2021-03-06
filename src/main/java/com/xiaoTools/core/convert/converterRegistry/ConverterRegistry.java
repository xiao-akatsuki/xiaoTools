package com.xiaoTools.core.convert.converterRegistry;

import com.xiaoTools.core.convert.arrayConverter.ArrayConverter;
import com.xiaoTools.core.convert.atomicBooleanConverter.AtomicBooleanConverter;
import com.xiaoTools.core.convert.atomicIntegerArrayConverter.AtomicIntegerArrayConverter;
import com.xiaoTools.core.convert.atomicLongArrayConverter.AtomicLongArrayConverter;
import com.xiaoTools.core.convert.atomicReferenceConverter.AtomicReferenceConverter;
import com.xiaoTools.core.convert.beanConverter.BeanConverter;
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
 * [???????????????????????????](Type converter registry)
 * @description: zh - ???????????????????????????
 * @description: en - Type converter registry
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 8:38 ??????
*/
public class ConverterRegistry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ?????????????????????
     */
    private Map<Type, Converter<?>> defaultConverterMap;

    /**
     * ??????????????????????????????
     */
    private volatile Map<Type, Converter<?>> customConverterMap;

    /**
     * [?????????????????????????????????????????????????????????????????????????????????????????????????????? ???????????????????????????????????????????????????????????????????????????????????????](Class level inner class, that is, static member type inner class. The instance of the inner class has no binding relationship with the instance of the outer class, and it will be loaded only when it is called, thus realizing delayed loading)
     * @description: zh - ?????????????????????????????????????????????????????????????????????????????????????????????????????? ???????????????????????????????????????????????????????????????????????????????????????
     * @description: en - Class level inner class, that is, static member type inner class. The instance of the inner class has no binding relationship with the instance of the outer class, and it will be loaded only when it is called, thus realizing delayed loading
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:45 ??????
    */
    private static class SingletonHolder {
        /**
         * ????????????????????????JVM?????????????????????
         */
        private static final ConverterRegistry INSTANCE = new ConverterRegistry();
    }

    /**
     * [??????????????? ConverterRegistry](Get the ConverterRegistry of singleton)
     * @description: zh - ??????????????? ConverterRegistry
     * @description: en - Get the ConverterRegistry of singleton
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:46 ??????
     * @return com.xiaoTools.core.convert.converterRegistry.ConverterRegistry
    */
    public static ConverterRegistry getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * [????????????](Nonparametric method)
     * @description: zh - ????????????
     * @description: en - Nonparametric method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:46 ??????
    */
    public ConverterRegistry() {
        defaultConverter();
        putCustomBySpi();
    }

    /*???????????????--------------------------------------------------------------------SPI*/

    /**
     * [??????SPI???????????????](Loading converter with SPI)
     * @description: zh - ??????SPI???????????????
     * @description: en - Loading converter with SPI
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:58 ??????
    */
    private void putCustomBySpi() {
        ServiceLoaderUtil.load(Converter.class).forEach(converter -> {
            try {
                Type type = TypeUtil.getTypeArgument(ClassUtil.getClass(converter));
                if(null != type){
                    putCustom(type, converter);
                }
            } catch (Exception e) {
                // ?????????????????????
            }
        });
    }

    /**
     * [????????????????????????](Register custom converter)
     * @description: zh - ????????????????????????
     * @description: en - Register custom converter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:57 ??????
     * @param type: ?????????????????????
     * @param converterClass: ??????????????????????????????????????????
     * @return com.xiaoTools.core.convert.converterRegistry.ConverterRegistry
    */
    public ConverterRegistry putCustom(Type type, Class<? extends Converter<?>> converterClass) {
        return putCustom(type, ReflectUtil.newInstance(converterClass));
    }

    /**
     * [????????????????????????](Register custom converter)
     * @description: zh - ????????????????????????
     * @description: en - Register custom converter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:55 ??????
     * @param type:  ?????????????????????
     * @param converter: ?????????
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
	 * [???????????????](Get converter)
	 * @description zh - ???????????????
	 * @description en - Get converter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:11:11
	 * @param type ??????
	 * @param isCustomFirst ??????????????????????????????
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
	 * [?????????????????????](Get default converter)
	 * @description zh - ?????????????????????
	 * @description en - Get default converter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:12:28
	 * @param type ??????
	 * @return com.xiaoTools.core.convert.converter.Converter<T>
	 */
	@SuppressWarnings("unchecked")
	public <T> Converter<T> getDefaultConverter(Type type) {
		return (null == defaultConverterMap) ? null : (Converter<T>) defaultConverterMap.get(type);
	}

	/**
	 * [????????????????????????](Get custom converter)
	 * @description zh - ????????????????????????
	 * @description en - Get custom converter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:13:26
	 * @param type ??????
	 * @return com.xiaoTools.core.convert.converter.Converter<T>
	 */
	@SuppressWarnings("unchecked")
	public <T> Converter<T> getCustomConverter(Type type) {
		return (null == customConverterMap) ? null : (Converter<T>) customConverterMap.get(type);
	}

	/**
	 * [????????????????????????](Converts the value to the specified type)
	 * @description zh - ????????????????????????
	 * @description en - Converts the value to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:15:07
	 * @param type ????????????
	 * @param value ????????????
	 * @param defaultValue ?????????
	 * @param isCustomFirst ??????????????????????????????
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T convert(Type type, Object value, T defaultValue, boolean isCustomFirst) throws ConvertException {
		if (TypeUtil.isUnknown(type) && null == defaultValue) {
			// ?????????????????????????????????????????????????????????
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

		// ???????????????
		final Converter<T> converter = getConverter(type, isCustomFirst);
		if (null != converter) {
			return converter.convert(value, defaultValue);
		}

		Class<T> rowType = (Class<T>) TypeUtil.getClass(type);
		if (null == rowType) {
			if (null != defaultValue) {
				rowType = (Class<T>) defaultValue.getClass();
			} else {
				// ????????????????????????????????????Object??????
				return (T) value;
			}
		}


		// ???????????????????????????Collection???Map????????????Array???
		final T result = convertSpecial(type, rowType, value, defaultValue);
		if (null != result) {
			return result;
		}

		// ?????????Bean
		if (BeanUtil.isBean(rowType)) {
			return new BeanConverter<T>(type).convert(value, defaultValue);
		}

		// ????????????
		throw new ConvertException("Can not Converter from [{}] to [{}]", value.getClass().getName(), type.getTypeName());
	}

	/**
	 * [????????????????????????](Converts the value to the specified type)
	 * @description zh - ????????????????????????
	 * @description en - Converts the value to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:16:51
	 * @param type ??????
	 * @param value ???
	 * @param defaultValue ?????????
	 * @throws com.xiaoTools.core.exception.convertException.ConvertException
	 * @return T
	 */
	public <T> T convert(Type type, Object value, T defaultValue) throws ConvertException {
		return convert(type, value, defaultValue, true);
	}

	/**
	 * [????????????????????????](Converts the value to the specified type)
	 * @description zh - ????????????????????????
	 * @description en - Converts the value to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:18:41
	 * @param type ??????
	 * @param value ???
	 * @throws com.xiaoTools.core.exception.convertException.ConvertException
	 * @return T
	 */
	public <T> T convert(Type type, Object value) throws ConvertException {
		return convert(type, value, null);
	}

	/**
	 * [??????????????????](Special type conversion)
	 * @description zh - ??????????????????
	 * @description en - Special type conversion
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 17:21:18
	 * @param type ??????
	 * @param value ???
	 * @param defaultValue ?????????
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	private <T> T convertSpecial(Type type, Class<T> rowType, Object value, T defaultValue) {
		if (null == rowType) {
			return null;
		}

		// ???????????????????????????????????????
		if (Collection.class.isAssignableFrom(rowType)) {
			final CollectionConverter collectionConverter = new CollectionConverter(type);
			return (T) collectionConverter.convert(value, (Collection<?>) defaultValue);
		}

		// Map?????????????????????????????????
		if (Map.class.isAssignableFrom(rowType)) {
			final MapConverter mapConverter = new MapConverter(type);
			return (T) mapConverter.convert(value, (Map<?, ?>) defaultValue);
		}

		// ????????????
		if (rowType.isInstance(value)) {
			return (T) value;
		}

		// ????????????
		if (rowType.isEnum()) {
			return (T) new EnumConverter(rowType).convert(value, defaultValue);
		}

		// ????????????
		if (rowType.isArray()) {
			final ArrayConverter arrayConverter = new ArrayConverter(rowType);
			return (T) arrayConverter.convert(value, defaultValue);
		}

		// ????????????????????????????????????
		return null;
	}

    /*???????????????--------------------------------------------------------------------defaultConverter*/

    private ConverterRegistry defaultConverter() {
        defaultConverterMap = new ConcurrentHashMap<>();

		// ?????????????????????
		defaultConverterMap.put(int.class, new PrimitiveConverter(int.class));
		defaultConverterMap.put(long.class, new PrimitiveConverter(long.class));
		defaultConverterMap.put(byte.class, new PrimitiveConverter(byte.class));
		defaultConverterMap.put(short.class, new PrimitiveConverter(short.class));
		defaultConverterMap.put(float.class, new PrimitiveConverter(float.class));
		defaultConverterMap.put(double.class, new PrimitiveConverter(double.class));
		defaultConverterMap.put(char.class, new PrimitiveConverter(char.class));
		defaultConverterMap.put(boolean.class, new PrimitiveConverter(boolean.class));

		// ??????????????????
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

		// ????????????
		defaultConverterMap.put(Calendar.class, new CalendarConverter());
		defaultConverterMap.put(java.util.Date.class, new DateConverter(java.util.Date.class));
		defaultConverterMap.put(DateTime.class, new DateConverter(DateTime.class));
		defaultConverterMap.put(java.sql.Date.class, new DateConverter(java.sql.Date.class));
		defaultConverterMap.put(java.sql.Time.class, new DateConverter(java.sql.Time.class));
		defaultConverterMap.put(java.sql.Timestamp.class, new DateConverter(java.sql.Timestamp.class));

		// ???????????? JDK8+
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

		// ????????????
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
