package com.xiaoTools.entity.beanPath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.text.stringBuilder.StrBuilder;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.beanUtil.BeanUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [Bean路径表达式](Bean path expression)
 * @description zh - Bean路径表达式
 * @description en - Bean path expression
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-26 20:28:02
 */
public class BeanPath implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 表达式边界符号数组
	 */
	private static final char[] EXP_CHARS = { Constant.CHAR_SPOT, Constant.CHAR_LEFT_MIDDLE_BRACKETS, Constant.CHAR_RIGHT_MIDDLE_BRACKETS };

	private boolean isStartWith = Constant.FALSE;

	protected List<String> patternParts;

	public BeanPath(String expression) {
		init(expression);
	}

	/**
	 * [解析Bean路径表达式为Bean模式](Resolve bean path expression to bean pattern)
	 * @description zh - 解析Bean路径表达式为Bean模式
	 * @description en - Resolve bean path expression to bean pattern
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:31:34
	 * @param expression 表达式
	 * @return com.xiaoTools.entity.beanPath.BeanPath
	 */
	public static BeanPath create(String expression) {
		return new BeanPath(expression);
	}

	/**
	 * [获取Bean中对应表达式的值](Gets the value of the corresponding expression in the bean)
	 * @description zh - 获取Bean中对应表达式的值
	 * @description en - Gets the value of the corresponding expression in the bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:32:41
	 * @param bean 实体类
	 * @return java.lang.Object
	 */
	public Object get(Object bean) {
		return get(this.patternParts, bean, Constant.FALSE);
	}

	/**
	 * [设置表达式指定位置](Sets the position specified by the expression)
	 * @description zh - 设置表达式指定位置
	 * @description en - Sets the position specified by the expression
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:34:35
	 * @param bean 实体类
	 * @param value 值
	 */
	public void set(Object bean, Object value) {
		set(bean, this.patternParts, value);
	}

	/**
	 * [设置表达式指定位置](Sets the position specified by the expression)
	 * @description zh - 设置表达式指定位置
	 * @description en - Sets the position specified by the expression
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:36:37
	 * @param bean 实体类
	 * @param patternParts 表达式块列表
	 * @param value 值
	 */
	private void set(Object bean, List<String> patternParts, Object value) {
		Object subBean = get(patternParts, bean, Constant.TRUE);
		if(null == subBean) {
			set(bean, patternParts.subList(Constant.ZERO, patternParts.size() - Constant.ONE), new HashMap<>());
			subBean = get(patternParts, bean, Constant.TRUE);
		}
		BeanUtil.setFieldValue(subBean, patternParts.get(patternParts.size() - Constant.ONE), value);
	}

	/**
	 * [获取Bean中对应表达式的值](Gets the value of the corresponding expression in the bean)
	 * @description zh - 获取Bean中对应表达式的值
	 * @description en - Gets the value of the corresponding expression in the bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:41:15
	 * @param patternParts 表达式分段列表
	 * @param bean 实体类
	 * @param ignoreLast 是否忽略最后一个值
	 * @return java.lang.Object
	 */
	private Object get(List<String> patternParts, Object bean, boolean ignoreLast) {
		int length = patternParts.size();
		if (ignoreLast) {
			length--;
		}
		Object subBean = bean;
		boolean isFirst = Constant.TRUE;
		String patternPart;
		for (int i = 0; i < length; i++) {
			patternPart = patternParts.get(i);
			subBean = getFieldValue(subBean, patternPart);
			if (null == subBean) {
				if (isFirst && Constant.FALSE == this.isStartWith && BeanUtil.isMatchName(bean, patternPart, Constant.TRUE)) {
					subBean = bean;
					isFirst = Constant.FALSE;
				} else {
					return null;
				}
			}
		}
		return subBean;
	}

	@SuppressWarnings("unchecked")
	private static Object getFieldValue(Object bean, String expression) {
		if (StrUtil.isBlank(expression)) {
			return null;
		}

		if (StrUtil.contains(expression, ':')) {
			final List<String> parts = StrUtil.splitTrim(expression, ':');
			int start = Integer.parseInt(parts.get(Constant.ZERO));
			int end = Integer.parseInt(parts.get(Constant.ONE));
			int step = Constant.ONE;
			if (Constant.THREE == parts.size()) {
				step = Integer.parseInt(parts.get(Constant.TWO));
			}
			if (bean instanceof Collection) {
				return CollUtil.sub((Collection<?>) bean, start, end, step);
			} else if (ArrayUtil.isArray(bean)) {
				return ArrayUtil.subarray(bean, start, end, step);
			}
		} else if (StrUtil.contains(expression, ',')) {
			final List<String> keys = StrUtil.splitTrim(expression, ',');
			if (bean instanceof Collection) {
				return CollUtil.getAny((Collection<?>) bean, Convert.convert(int[].class, keys));
			} else if (ArrayUtil.isArray(bean)) {
				return ArrayUtil.getAny(bean, Convert.convert(int[].class, keys));
			} else {
				final String[] unWrappedKeys = new String[keys.size()];
				for (int i = Constant.ZERO; i < unWrappedKeys.length; i++) {
					unWrappedKeys[i] = StrUtil.unWrap(keys.get(i), '\'');
				}
				if (bean instanceof Map) {
					return MapUtil.getAny((Map<String, ?>) bean, unWrappedKeys);
				} else {
					final Map<String, Object> map = BeanUtil.beanToMap(bean);
					return MapUtil.getAny(map, unWrappedKeys);
				}
			}
		} else {
			return BeanUtil.getFieldValue(bean, expression);
		}

		return null;
	}

	private void init(String expression) {
		List<String> localPatternParts = new ArrayList<>();
		int length = expression.length();

		final StrBuilder builder = StrUtil.strBuilder();
		char c;
		boolean isNumStart = Constant.FALSE;
		for (int i = Constant.ZERO; i < length; i++) {
			c = expression.charAt(i);
			if (0 == i && '$' == c) {
				isStartWith = true;
				continue;
			}

			if (ArrayUtil.contains(EXP_CHARS, c)) {
				if (Constant.CHAR_RIGHT_MIDDLE_BRACKETS == c) {
					if (Constant.FALSE == isNumStart) {
						throw new IllegalArgumentException(StrUtil.format("Bad expression '{}':{}, we find ']' but no '[' !", expression, i));
					}
					isNumStart = false;
				} else {
					if (isNumStart) {
						throw new IllegalArgumentException(StrUtil.format("Bad expression '{}':{}, we find '[' but no ']' !", expression, i));
					} else if (Constant.CHAR_LEFT_MIDDLE_BRACKETS == c) {
						isNumStart = true;
					}
				}
				if (builder.length() > 0) {
					localPatternParts.add(unWrapIfPossible(builder));
				}
				builder.reset();
			} else {
				builder.append(c);
			}
		}

		if (isNumStart) {
			throw new IllegalArgumentException(StrUtil.format("Bad expression '{}':{}, we find '[' but no ']' !", expression, length - 1));
		} else {
			if (builder.length() > 0) {
				localPatternParts.add(unWrapIfPossible(builder));
			}
		}

		this.patternParts = Collections.unmodifiableList(localPatternParts);
	}

	private static String unWrapIfPossible(CharSequence expression) {
		if (StrUtil.containsAny(expression, " = ", " > ", " < ", " like ", ",")) {
			return expression.toString();
		}
		return StrUtil.unWrap(expression, '\'');
	}

}
