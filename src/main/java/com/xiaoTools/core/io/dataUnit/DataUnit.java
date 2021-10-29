package com.xiaoTools.core.io.dataUnit;

import com.xiaoTools.core.io.dataSize.DataSize;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [数据单位封装](Data unit encapsulation)
 * @description zh - 数据单位封装
 * @description en - Data unit encapsulation
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 22:17:58
 */
public enum DataUnit {

	/**
	 * Bytes
	 */
	BYTES("B", DataSize.ofBytes(1)),

	/**
	 * Kilobytes
	 */
	KILOBYTES("KB", DataSize.ofKilobytes(1)),

	/**
	 * Megabytes
	 */
	MEGABYTES("MB", DataSize.ofMegabytes(1)),

	/**
	 * Gigabytes
	 */
	GIGABYTES("GB", DataSize.ofGigabytes(1)),

	/**
	 * Terabytes
	 */
	TERABYTES("TB", DataSize.ofTerabytes(1));

	public static final String[] UNIT_NAMES = new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB"};

	private final String suffix;

	private final DataSize size;

	DataUnit(String suffix, DataSize size) {
		this.suffix = suffix;
		this.size = size;
	}

	public DataSize size() {
		return this.size;
	}

	/**
	 * [通过后缀返回对应的DataUnit](Return the corresponding dataunit through the suffix)
	 * @description zh - 通过后缀返回对应的DataUnit
	 * @description en - Return the corresponding dataunit through the suffix
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:19:54
	 * @param suffix 单位后缀
	 * @return com.xiaoTools.core.io.dataUnit.DataUnit
	 */
	public static DataUnit fromSuffix(String suffix) {
		for (DataUnit candidate : values()) {
			if (StrUtil.startWithIgnoreCase(candidate.suffix, suffix)) {
				return candidate;
			}
		}
		throw new IllegalArgumentException("Unknown data unit suffix '" + suffix + "'");
	}


}
