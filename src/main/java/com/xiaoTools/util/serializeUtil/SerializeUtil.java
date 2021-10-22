package com.xiaoTools.util.serializeUtil;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import com.xiaoTools.core.io.fastByteArrayOutputStream.FastByteArrayOutputStream;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.ioUtil.IoUtil;

/**
 * [序列化工具类](Serialization tool class)
 * @description zh - 序列化工具类
 * @description en - Serialization tool class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-22 12:28:33
 */
public class SerializeUtil {

	/**
	 * [序列化后拷贝流的方式克隆](Clone by copying stream after serialization)
	 * @description zh - 序列化后拷贝流的方式克隆
	 * @description en - Clone by copying stream after serialization
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:28:59
	 * @param obj 对象
	 * @return T
	 */
	public static <T> T clone(T obj) {
		return Constant.FALSE == (obj instanceof Serializable) ? null :
			deserialize(serialize(obj));
	}

	/**
	 * [反序列化](Deserialization)
	 * @description zh - 反序列化
	 * @description en - Deserialization
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:29:37
	 * @param bytes 反序列化的字节码
	 * @return T
	 */
	public static <T> T deserialize(byte[] bytes) {
		return IoUtil.readObj(new ByteArrayInputStream(bytes));
	}

	/**
	 * [序列化](serialize)
	 * @description zh - 序列化
	 * @description en - serialize
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:31:22
	 * @parram obj 对象
	 * @return byte[]
	 */
	public static <T> byte[] serialize(T obj) {
		if (false == (obj instanceof Serializable)) {
			return null;
		}
		final FastByteArrayOutputStream byteOut = new FastByteArrayOutputStream();
		IoUtil.writeObjects(byteOut, false, (Serializable) obj);
		return byteOut.toByteArray();
	}
}
