package com.xiaoTools.core.io.dataSize;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.io.dataUnit.DataUnit;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [数据大小](data size)
 * @description zh - 数据大小
 * @description en - data size
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 22:10:33
 */
public class DataSize implements Comparable<DataSize>  {

	private static final Pattern PATTERN = Pattern.compile("^([+-]?\\d+(\\.\\d+)?)([a-zA-Z]{0,2})$");

	private static final long BYTES_PER_KB = 1024;

	private static final long BYTES_PER_MB = BYTES_PER_KB * 1024;

	private static final long BYTES_PER_GB = BYTES_PER_MB * 1024;

	private static final long BYTES_PER_TB = BYTES_PER_GB * 1024;

	private final long bytes;

	private DataSize(long bytes) {
		this.bytes = bytes;
	}

	/**
	 * [获得对应bytes的DataSize](Get the datasize of the corresponding bytes)
	 * @description zh - 获得对应bytes的DataSize
	 * @description en - Get the datasize of the corresponding bytes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:31:47
	 * @param bytes bytes大小
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize ofBytes(long bytes) {
		return new DataSize(bytes);
	}

	/**
	 * [获得对应kilobytes的DataSize](Obtain the datasize of the corresponding kilobytes)
	 * @description zh - 获得对应kilobytes的DataSize
	 * @description en - Obtain the datasize of the corresponding kilobytes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:30:46
	 * @param kilobytes 大小
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize ofKilobytes(long kilobytes) {
		return new DataSize(Math.multiplyExact(kilobytes, BYTES_PER_KB));
	}

	/**
	 * [获得对应megabytes的DataSize](Get the datasize corresponding to megabytes)
	 * @description zh - 获得对应megabytes的DataSize
	 * @description en - Get the datasize corresponding to megabytes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:29:40
	 * @param megabytes megabytes大小
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize ofMegabytes(long megabytes) {
		return new DataSize(Math.multiplyExact(megabytes, BYTES_PER_MB));
	}

	/**
	 * [获得对应gigabytes的DataSize](Obtain the datasize of the corresponding gigabytes)
	 * @description zh - 获得对应gigabytes的DataSize
	 * @description en - Obtain the datasize of the corresponding gigabytes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:27:39
	 * @param gigabytes gigabytes大小
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize ofGigabytes(long gigabytes) {
		return new DataSize(Math.multiplyExact(gigabytes, BYTES_PER_GB));
	}

	/**
	 * [获得对应terabytes的DataSize](Obtain the datasize of the corresponding terabytes)
	 * @description zh - 获得对应terabytes的DataSize
	 * @description en - Obtain the datasize of the corresponding terabytes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:26:37
	 * @param terabytes terabytes大小
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize ofTerabytes(long terabytes) {
		return new DataSize(Math.multiplyExact(terabytes, BYTES_PER_TB));
	}

	/**
	 * [获得指定 DataUnit 对应的DataSize](Gets the datasize corresponding to the specified dataunit)
	 * @description zh - 获得指定 DataUnit 对应的DataSize
	 * @description en - Gets the datasize corresponding to the specified dataunit
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:26:03
	 * @param amount 大小
	 * @param unit 数据大小单位
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize of(long amount, DataUnit unit) {
		if(null == unit){
			unit = DataUnit.BYTES;
		}
		return new DataSize(Math.multiplyExact(amount, unit.size().toBytes()));
	}

	/**
	 * [获得指定 DataUnit 对应的DataSize](Gets the datasize corresponding to the specified dataunit)
	 * @description zh - 获得指定 DataUnit 对应的DataSize
	 * @description en - Gets the datasize corresponding to the specified dataunit
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:24:54
	 * @param amount 大小
	 * @param unit 数据大小单位
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize of(BigDecimal amount, DataUnit unit) {
		if(null == unit){
			unit = DataUnit.BYTES;
		}
		return new DataSize(amount.multiply(new BigDecimal(unit.size().toBytes())).longValue());
	}

	/**
	 * [解析](analysis)
	 * @description zh - 解析
	 * @description en - analysis
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:24:14
	 * @param text 文本
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize parse(CharSequence text) {
		return parse(text, null);
	}

	/**
	 * [解析](analysis)
	 * @description zh - 解析
	 * @description en - analysis
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:23:12
	 * @param text 数据
	 * @param defaultUnit 默认的数据单位
	 * @return com.xiaoTools.core.io.dataSize.DataSize
	 */
	public static DataSize parse(CharSequence text, DataUnit defaultUnit) {
		Assertion.notNull(text, "Text must not be null");
		try {
			final Matcher matcher = PATTERN.matcher(text);
			Assertion.state(matcher.matches(), "Does not match data size pattern");

			final DataUnit unit = determineDataUnit(matcher.group(3), defaultUnit);
			return DataSize.of(new BigDecimal(matcher.group(1)), unit);
		} catch (Exception ex) {
			throw new IllegalArgumentException("'" + text + "' is not a valid data size", ex);
		}
	}

	/**
	 * [决定数据单位](Determine data units)
	 * @description zh - 决定数据单位
	 * @description en - Determine data units
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:16:23
	 * @param suffix 后缀
	 * @param defaultUnit 默认单位
	 * @return com.xiaoTools.core.io.dataUnit.DataUnit
	 */
	private static DataUnit determineDataUnit(String suffix, DataUnit defaultUnit) {
		DataUnit defaultUnitToUse = (defaultUnit != null ? defaultUnit : DataUnit.BYTES);
		return (StrUtil.isNotEmpty(suffix) ? DataUnit.fromSuffix(suffix) : defaultUnitToUse);
	}

	/**
	 * [是否为负数](Is it negative)
	 * @description zh - 是否为负数
	 * @description en - Is it negative
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:15:13
	 * @return boolean
	 */
	public boolean isNegative() {
		return this.bytes < 0;
	}

	/**
	 * [返回bytes大小](Returns the size of bytes)
	 * @description zh - 返回bytes大小
	 * @description en - Returns the size of bytes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:14:31
	 * @return long
	 */
	public long toBytes() {
		return this.bytes;
	}

	/**
	 * [返回KB大小](Return KB size)
	 * @description zh - 返回KB大小
	 * @description en - Return KB size
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:13:52
	 * @return long
	 */
	public long toKilobytes() {
		return this.bytes / BYTES_PER_KB;
	}

	/**
	 * [返回MB大小](Return MB size)
	 * @description zh - 返回MB大小
	 * @description en - Return MB size
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:13:19
	 * @return long
	 */
	public long toMegabytes() {
		return this.bytes / BYTES_PER_MB;
	}

	/**
	 * [返回GB大小](Return GB size)
	 * @description zh - 返回GB大小
	 * @description zh - Return GB size
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:12:51
	 * @return long
	 */
	public long toGigabytes() {
		return this.bytes / BYTES_PER_GB;
	}

	/**
	 * [返回TB大小](Return TB size)
	 * @description zh - 返回TB大小
	 * @description en - Return TB size
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 22:12:20
	 * @return long
	 */
	public long toTerabytes() {
		return this.bytes / BYTES_PER_TB;
	}

	@Override
	public int compareTo(DataSize other) {
		return Long.compare(this.bytes, other.bytes);
	}

	@Override
	public String toString() {
		return String.format("%dB", this.bytes);
	}


	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		DataSize otherSize = (DataSize) other;
		return (this.bytes == otherSize.bytes);
	}

	@Override
	public int hashCode() {
		return Long.hashCode(this.bytes);
	}
}
