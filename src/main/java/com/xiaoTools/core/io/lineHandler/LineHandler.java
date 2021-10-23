package com.xiaoTools.core.io.lineHandler;

/**
 * [行处理器](Line processor)
 * @description zh - 行处理器
 * @description en - Line processor
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-23 17:55:40
 */
@FunctionalInterface
public interface LineHandler {

	/**
	 * [处理一行数据，可以编辑后存入指定地方](Process a row of data, which can be edited and stored in the specified place)
	 * @description zh - 处理一行数据，可以编辑后存入指定地方
	 * @description en - Process a row of data, which can be edited and stored in the specified place
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:55:13
	 * @param line 行
	 */
	void handle(String line);

}
