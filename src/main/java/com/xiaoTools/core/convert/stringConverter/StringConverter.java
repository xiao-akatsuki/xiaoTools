package com.xiaoTools.core.convert.stringConverter;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.TimeZone;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.core.exception.convertException.ConvertException;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.ioUtil.IoUtil;

/**
 * [字符串转换器](String converter)
 * @description zh - 字符串转换器
 * @description en - String converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-19 07:30:31
 */
public class StringConverter extends AbstractConverter<String> {

	private static final long serialVersionUID = 1L;

	@Override
	protected String convertInternal(Object value) {
		if (value instanceof TimeZone) {
			return ((TimeZone) value).getID();
		} else if (value instanceof org.w3c.dom.Node) {
			return XmlUtil.toStr((org.w3c.dom.Node) value);
		} else if (value instanceof Clob) {
			return clobToStr((Clob) value);
		} else if (value instanceof Blob) {
			return blobToStr((Blob) value);
		}

		// 其它情况
		return convertToStr(value);
	}

	/**
	 * [Clob字段值转字符串](Clob field value to string)
	 * @description zh - Clob字段值转字符串
	 * @description en - Clob field value to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-19 07:32:07
	 * @param clob Clob
	 * @return java.lang.String
	 */
	private static String clobToStr(Clob clob) {
		Reader reader = null;
		try {
			reader = clob.getCharacterStream();
			return IoUtil.read(reader);
		} catch (SQLException e) {
			throw new ConvertException(e);
		} finally {
			IoUtil.close(reader);
		}
	}

	/**
	 * [Clob字段值转字符串](Clob field value to string)
	 * @description zh - Clob字段值转字符串
	 * @description en - Clob field value to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-19 07:33:07
	 * @param blob Blob
	 * @return java.lang.String
	 */
	private static String blobToStr(Blob blob) {
		InputStream in = null;
		try {
			in = blob.getBinaryStream();
			return IoUtil.read(in, CharsetUtil.CHARSET_UTF_8);
		} catch (SQLException e) {
			throw new ConvertException(e);
		} finally {
			IoUtil.close(in);
		}
	}

}
