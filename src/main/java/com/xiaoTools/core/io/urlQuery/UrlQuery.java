package com.xiaoTools.core.io.urlQuery;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.map.tableMap.TableMap;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.iterUtil.IterUtil;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.strUtil.StrUtil;
import com.xiaoTools.util.urlUtil.URLUtil;



public class UrlQuery {

	private final TableMap<CharSequence, CharSequence> query;

	public UrlQuery() {
		this(null);
	}

	public UrlQuery(Map<? extends CharSequence, ?> queryMap) {
		if (MapUtil.isNotEmpty(queryMap)) {
			query = new TableMap<>(queryMap.size());
			addAll(queryMap);
		} else {
			query = new TableMap<>(MapUtil.DEFAULT_INITIAL_CAPACITY);
		}
	}

	/**
	 * [构建UrlQuery](Build urlquery)
	 * @description zh - 构建UrlQuery
	 * @description en - Build urlquery
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:50:25
	 * @param queryMap 初始化的查询键值对
	 * @return com.xiaoTools.core.io.urlQuery.UrlQuery
	 */
	public static UrlQuery of(Map<? extends CharSequence, ?> queryMap) {
		return new UrlQuery(queryMap);
	}

	/**
	 * [构建UrlQuery](Build urlquery)
	 * @description zh - 构建UrlQuery
	 * @description en - Build urlquery
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:51:14
	 * @param queryMap 初始化的查询键值对
	 * @param charset 字符集
	 * @return com.xiaoTools.core.io.urlQuery.UrlQuery
	 */
	public static UrlQuery of(String queryStr, Charset charset) {
		return of(queryStr, charset, Constant.TRUE);
	}

	/**
	 * [构建UrlQuery](Build urlquery)
	 * @description zh - 构建UrlQuery
	 * @description en - Build urlquery
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:50:20
	 * @param queryMap 初始化的查询键值对
	 * @param charset 字符集
	 * @param autoRemovePath 是否自动去除path部分
	 * @return com.xiaoTools.core.io.urlQuery.UrlQuery
	 */
	public static UrlQuery of(String queryStr, Charset charset, boolean autoRemovePath) {
		final UrlQuery urlQuery = new UrlQuery();
		urlQuery.parse(queryStr, charset, autoRemovePath);
		return urlQuery;
	}

	/**
	 * [增加键值对](Add key value pair)
	 * @description zh - 增加键值对
	 * @description en - Add key value pair
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:52:37
	 * @param key 键
	 * @param value 值
	 * @return com.xiaoTools.core.io.urlQuery.UrlQuery
	 */
	public UrlQuery add(CharSequence key, Object value) {
		this.query.put(key, toStr(value));
		return this;
	}

	/**
	 * [批量增加键值对](Batch add key value pairs)
	 * @description zh - 批量增加键值对
	 * @description en - Batch add key value pairs
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:53:30
	 * @param queryMap query中的键值对
	 * @return com.xiaoTools.core.io.urlQuery.UrlQuery
	 */
	public UrlQuery addAll(Map<? extends CharSequence, ?> queryMap) {
		if (MapUtil.isNotEmpty(queryMap)) {
			queryMap.forEach(this::add);
		}
		return this;
	}

	/**
	 * [解析URL中的查询字符串](Parse query string in URL)
	 * @description zh - 解析URL中的查询字符串
	 * @description en - Parse query string in URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:54:41
	 * @param queryStr 查询字符串
	 * @param charset 编码
	 * @return com.xiaoTools.core.io.urlQuery.UrlQuery
	 */
	public UrlQuery parse(String queryStr, Charset charset) {
		return parse(queryStr, charset, true);
	}

	/**
	 * [解析URL中的查询字符串](Parse query string in URL)
	 * @description zh - 解析URL中的查询字符串
	 * @description en - Parse query string in URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:56:11
	 * @param queryStr 查询字符串
	 * @param charset 编码
	 * @param autoRemovePath 是否自动去除path部分
	 * @return com.xiaoTools.core.io.urlQuery.UrlQuery
	 */
	public UrlQuery parse(String queryStr, Charset charset, boolean autoRemovePath) {
		if (StrUtil.isBlank(queryStr)) {
			return this;
		}

		if (autoRemovePath) {
			int pathEndPos = queryStr.indexOf('?');
			if (pathEndPos > -1) {
				queryStr = StrUtil.subStringSuf(queryStr, pathEndPos + 1);
				if (StrUtil.isBlank(queryStr)) {
					return this;
				}
			}
		}

		final int len = queryStr.length();
		String name = null;
		int pos = 0;
		int i;
		char c;
		for (i = 0; i < len; i++) {
			c = queryStr.charAt(i);
			switch (c) {
				case '=':
					if (null == name) {
						name = queryStr.substring(pos, i);
						pos = i + 1;
					}
					break;
				case '&':
					addParam(name, queryStr.substring(pos, i), charset);
					name = null;
					if (i + 4 < len && "amp;".equals(queryStr.substring(i + 1, i + 5))) {
						i += 4;
					}
					pos = i + 1;
					break;
			}
		}

		addParam(name, queryStr.substring(pos, i), charset);
		return this;
	}

	/**
	 * [获得查询的Map](Get the map of the query)
	 * @description zh - 获得查询的Map
	 * @description en - Get the map of the query
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:57:04
	 * @return java.util.Map<java.lang.CharSequence, java.lang.CharSequence>
	 */
	public Map<CharSequence, CharSequence> getQueryMap() {
		return MapUtil.unmodifiable(this.query);
	}

	/**
	 * [获取查询值](Get query value)
	 * @description zh - 获取查询值
	 * @description en - Get query value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:58:03
	 * @param key 键
	 * @return java.lang.CharSequence
	 */
	public CharSequence get(CharSequence key) {
		return MapUtil.isEmpty(this.query) ? null : this.query.get(key);
	}

	/**
	 * [构建URL查询字符串](Build URL query string)
	 * @description zh - 构建URL查询字符串
	 * @description en - Build URL query string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:58:57
	 * @param charset encode编码
	 * @return java.lang.String
	 */
	public String build(Charset charset) {
		if (MapUtil.isEmpty(this.query)) {
			return Constant.EMPTY;
		}

		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		CharSequence key;
		CharSequence value;
		for (Map.Entry<CharSequence, CharSequence> entry : this.query) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append("&");
			}
			key = entry.getKey();
			if (null != key) {
				sb.append(URLUtil.encodeAll(StrUtil.str(key), charset));
				value = entry.getValue();
				if (null != value) {
					sb.append("=").append(URLUtil.encodeAll(StrUtil.str(value), charset));
				}
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return build(null);
	}

	/**
	 * [对象转换为字符串](Object to string)
	 * @description zh - 对象转换为字符串
	 * @description en - Object to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:00:56
	 * @param value 值
	 * @return java.lang.String
	 */
	private static String toStr(Object value) {
		return value instanceof Iterable ? CollUtil.join((Iterable<?>) value, ",") :
			value instanceof Iterator ? IterUtil.join((Iterator<?>) value, ",") :
				Convert.toStr(value);
	}

	/**
	 * [将键值对加入到值为List类型的Map中](Add key value pairs to a map with a value of list type)
	 * @description zh - 将键值对加入到值为List类型的Map中
	 * @description en - Add key value pairs to a map with a value of list type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:01:47
	 * @param key 键
	 * @param value 值
	 * @param charset 字符集
	 */
	private void addParam(String key, String value, Charset charset) {
		if (null != key) {
			final String actualKey = URLUtil.decode(key, charset);
			this.query.put(actualKey, StrUtil.nullToEmpty(URLUtil.decode(value, charset)));
		} else if (null != value) {
			this.query.put(URLUtil.decode(value, charset), null);
		}
	}

}
