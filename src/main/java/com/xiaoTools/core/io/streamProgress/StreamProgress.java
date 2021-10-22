package com.xiaoTools.core.io.streamProgress;

/**
 * [Stream进度条]Z(Stream progress bar)
 * @description zh - Stream进度条
 * @description en - Stream progress bar
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-22 12:49:15
 */
public interface StreamProgress {

	/**
	 * [开始](start)
	 * @description zh - 开始
	 * @description en - start
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:48:12
	 */
	void start();

	void progress(long progressSize);

	/**
	 * [结束](end)
	 * @description zh - 结束
	 * @description en - end
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:48:40
	 */
	void finish();
}
