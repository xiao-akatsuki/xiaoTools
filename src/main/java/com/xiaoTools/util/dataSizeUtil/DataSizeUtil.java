package com.xiaoTools.util.dataSizeUtil;

import java.text.DecimalFormat;

import com.xiaoTools.core.io.dataSize.DataSize;
import com.xiaoTools.core.io.dataUnit.DataUnit;

/**
 * [数据大小工具类](Data size utility class)
 * @description zh - 数据大小工具类
 * @description en - Data size utility class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 22:07:02
 */
public class DataSizeUtil {

	/**
	 * [解析数据大小字符串](Parse data size string)
	 * @description zh - 解析数据大小字符串
	 * @description en - Parse data size string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:07:25
	 * @param text 数据大小字符串
	 * @return long
	 */
	public static long parse(String text) {
		return DataSize.parse(text).toBytes();
	}

	/**
	 * [可读的文件大小](Readable file size)
	 * @description zh - 可读的文件大小
	 * @description en - Readable file size
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:07:59
	 * @param size Long类型大小
	 * @return java.lang.String
	 */
	public static String format(long size) {
		if (size <= 0) {
			return "0";
		}
		int digitGroups = Math.min(DataUnit.UNIT_NAMES.length-1, (int) (Math.log10(size) / Math.log10(1024)));
		return new DecimalFormat("#,##0.##")
				.format(size / Math.pow(1024, digitGroups)) + " " + DataUnit.UNIT_NAMES[digitGroups];
	}
}
