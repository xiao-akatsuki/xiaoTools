package com.xiaoTools.entity.mapValueProvider;

import java.util.Map;
import java.lang.reflect.Type;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.map.caseInsensitiveMap.CaseInsensitiveMap;
import com.xiaoTools.entity.valueProvider.ValueProvider;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [Map值提供者](Map value provider)
 * @description zh - Map值提供者
 * @description en - Map value provider
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-27 18:43:31
 */
public class MapValueProvider implements ValueProvider<String> {

	private final Map<?, ?> map;

	private final boolean ignoreError;

	public MapValueProvider(Map<?, ?> map, boolean ignoreCase) {
		this(map, ignoreCase, Constant.FALSE);
	}

	public MapValueProvider(Map<?, ?> map, boolean ignoreCase, boolean ignoreError) {
		if (Constant.FALSE == ignoreCase || map instanceof CaseInsensitiveMap) {
			this.map = map;
		} else {
			this.map = new CaseInsensitiveMap<>(map);
		}
		this.ignoreError = ignoreError;
	}

	@Override
	public Object value(String key, Type valueType) {
		final String key1 = getKey(key, valueType);
		if (null == key1) {
			return null;
		}

		final Object value = map.get(key1);
		return Convert.convertWithCheck(valueType, value, null, this.ignoreError);
	}

	@Override
	public boolean containsKey(String key) {
		return null != getKey(key, null);
	}

	/**
	 * [获得map中可能包含的key](Get the key that may be contained in the map)
	 * @description zh - 获得map中可能包含的key
	 * @description en - Get the key that may be contained in the map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:44:47
	 * @param key map中可能包含的key
	 * @param valueType 值类型
	 * @return java.lang.String
	 */
	private String getKey(String key, Type valueType) {
		if (map.containsKey(key)) {
			return key;
		}

		String customKey = StrUtil.toUnderlineCase(key);
		if (map.containsKey(customKey)) {
			return customKey;
		}

		if (null == valueType || Boolean.class == valueType || boolean.class == valueType) {
			customKey = StrUtil.upperFirstAndAddPre(key, "is");
			if (map.containsKey(customKey)) {
				return customKey;
			}

			customKey = StrUtil.toUnderlineCase(customKey);
			if (map.containsKey(customKey)) {
				return customKey;
			}
		}
		return null;
	}
}
