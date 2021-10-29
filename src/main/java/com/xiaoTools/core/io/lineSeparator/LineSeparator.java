package com.xiaoTools.core.io.lineSeparator;

/**
 * [换行符枚举](Linefeed enumeration)
 * @description zh - 换行符枚举
 * @description en - Linefeed enumeration
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 22:04:50
 */
public enum LineSeparator {
	/** Mac系统换行符："\r" */
	MAC("\r"),
	/** Linux系统换行符："\n" */
	LINUX("\n"),
	/** Windows系统换行符："\r\n" */
	WINDOWS("\r\n");

	private final String value;

	LineSeparator(String lineSeparator) {
		this.value = lineSeparator;
	}

	public String getValue() {
		return this.value;
	}
}
