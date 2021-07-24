package com.xiaoTools.core.convert.numberConverter;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.booleanUtil.BooleanUtil;
import com.xiaoTools.util.byteUtil.ByteUtil;
import com.xiaoTools.util.dateUtil.DateUtil;
import com.xiaoTools.util.numUtil.NumUtil;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;

/**
 * [数字转换器](Digital converter)
 * @description: zh - 数字转换器
 * @description: en - Digital converter
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 4:01 下午
*/
public class NumberConverter extends AbstractConverter<Number> {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Class<? extends Number> targetType;

    /**
     * [无参构造](Nonparametric structure)
     * @description: zh - 无参构造
     * @description: en - Nonparametric structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:15 下午
    */
    public NumberConverter() {
        this.targetType = Number.class;
    }

    /**
     * [无参构造](Nonparametric structure)
     * @description: zh - 无参构造
     * @description: en - Nonparametric structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:16 下午
     * @param clazz: 类
    */
    public NumberConverter(Class<? extends Number> clazz) {
        this.targetType = (Constant.NULL == clazz) ? Number.class : clazz;
    }

    @Override
    protected Number convertInternal(Object value) {
        return convert(value, this.targetType, this::convertToStr);
    }

    /**
     * [转换对象为数字](Convert objects to numbers)
     * @description: zh - 转换对象为数字
     * @description: en - Convert objects to numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:21 下午
     * @param value: 对象值
     * @param targetType: 目标的数字类型
     * @param toStrFunc: 转换为字符串的函数
     * @return java.lang.Number
    */
    protected static Number convert(Object value, Class<?> targetType, Function<Object, String> toStrFunc) {
        // 枚举转换为数字默认为其顺序
        if (value instanceof Enum) {
            return convert(((Enum<?>) value).ordinal(), targetType, toStrFunc);
        }

        if (Byte.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).byteValue();
            } else if (value instanceof Boolean) {
                return BooleanUtil.toByteObj((Boolean) value);
            }
            final String valueStr = toStrFunc.apply(value);
            try{
                return StrUtil.isBlank(valueStr) ? null : Byte.valueOf(valueStr);
            } catch (NumberFormatException e){
                return NumUtil.parseNumber(valueStr).byteValue();
            }
        } else if (Short.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).shortValue();
            } else if (value instanceof Boolean) {
                return BooleanUtil.toShortObj((Boolean) value);
            } else if (value instanceof byte[]){
                return ByteUtil.bytesToShort((byte[]) value);
            }
            final String valueStr = toStrFunc.apply((value));
            try{
                return StrUtil.isBlank(valueStr) ? null : Short.valueOf(valueStr);
            } catch (NumberFormatException e){
                return NumUtil.parseNumber(valueStr).shortValue();
            }
        } else if (Integer.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).intValue();
            } else if (value instanceof Boolean) {
                return BooleanUtil.toInteger((Boolean) value);
            } else if (value instanceof Date) {
                return (int) ((Date) value).getTime();
            } else if (value instanceof Calendar) {
                return (int) ((Calendar) value).getTimeInMillis();
            } else if (value instanceof TemporalAccessor) {
                return (int) DateUtil.toInstant((TemporalAccessor) value).toEpochMilli();
            } else if (value instanceof byte[]){
                return ByteUtil.bytesToInt((byte[]) value);
            }
            final String valueStr = toStrFunc.apply((value));
            return StrUtil.isBlank(valueStr) ? null : NumUtil.parseInt(valueStr);
        } else if (AtomicInteger.class == targetType) {
            final Number number = convert(value, Integer.class, toStrFunc);
            if (null != number) {
                final AtomicInteger intValue = new AtomicInteger();
                intValue.set(number.intValue());
                return intValue;
            }
        } else if (Long.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).longValue();
            } else if (value instanceof Boolean) {
                return BooleanUtil.toLongObj((Boolean) value);
            } else if (value instanceof Date) {
                return ((Date) value).getTime();
            } else if (value instanceof Calendar) {
                return ((Calendar) value).getTimeInMillis();
            } else if (value instanceof TemporalAccessor) {
                return DateUtil.toInstant((TemporalAccessor) value).toEpochMilli();
            }else if (value instanceof byte[]){
                return ByteUtil.bytesToLong((byte[]) value);
            }
            final String valueStr = toStrFunc.apply((value));
            return StrUtil.isBlank(valueStr) ? null : NumUtil.parseLong(valueStr);
        } else if (AtomicLong.class == targetType) {
            final Number number = convert(value, Long.class, toStrFunc);
            if (null != number) {
                final AtomicLong longValue = new AtomicLong();
                longValue.set(number.longValue());
                return longValue;
            }
        } else if (LongAdder.class == targetType) {
            //jdk8 新增
            final Number number = convert(value, Long.class, toStrFunc);
            if (null != number) {
                final LongAdder longValue = new LongAdder();
                longValue.add(number.longValue());
                return longValue;
            }
        } else if (Float.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).floatValue();
            } else if (value instanceof Boolean) {
                return BooleanUtil.toFloatObj((Boolean) value);
            } else if (value instanceof byte[]){
                return (float)ByteUtil.bytesToDouble((byte[]) value);
            }
            final String valueStr = toStrFunc.apply((value));
            return StrUtil.isBlank(valueStr) ? null : NumUtil.parseFloat(valueStr);
        } else if (Double.class == targetType) {
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value instanceof Boolean) {
                return BooleanUtil.toDoubleObj((Boolean) value);
            } else if (value instanceof byte[]){
                return ByteUtil.bytesToDouble((byte[]) value);
            }
            final String valueStr = toStrFunc.apply((value));
            return StrUtil.isBlank(valueStr) ? null : NumUtil.parseDouble(valueStr);
        } else if (DoubleAdder.class == targetType) {
            //jdk8 新增
            final Number number = convert(value, Long.class, toStrFunc);
            if (null != number) {
                final DoubleAdder doubleAdder = new DoubleAdder();
                doubleAdder.add(number.doubleValue());
                return doubleAdder;
            }
        } else if (BigDecimal.class == targetType) {
            return toBigDecimal(value, toStrFunc);
        } else if (BigInteger.class == targetType) {
            return toBigInteger(value, toStrFunc);
        } else if (Number.class == targetType) {
            if (value instanceof Number) {
                return (Number) value;
            } else if (value instanceof Boolean) {
                return BooleanUtil.toInteger((Boolean) value);
            }
            final String valueStr = toStrFunc.apply((value));
            return StrUtil.isBlank(valueStr) ? Constant.NUMBER_NULL : NumUtil.parseNumber(valueStr);
        }

        throw new UnsupportedOperationException(StrUtil.format("Unsupport Number type: {}", targetType.getName()));
    }
}
