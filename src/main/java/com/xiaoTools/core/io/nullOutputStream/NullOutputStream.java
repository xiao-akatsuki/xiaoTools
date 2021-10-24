package com.xiaoTools.core.io.nullOutputStream;


import java.io.IOException;
import java.io.OutputStream;

/**
 * [此OutputStream写出数据到null](This OutputStream writes out data to null)
 * @description zh - 此OutputStream写出数据到null
 * @description en - This OutputStream writes out data to null
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-24 10:56:10
 */
public class NullOutputStream extends OutputStream {
	public static final NullOutputStream NULL_OUTPUT_STREAM = new NullOutputStream();

	@Override
	public void write(byte[] b, int off, int len) {
		// to /dev/null
	}

	@Override
	public void write(int b) {
		// to /dev/null
	}

	@Override
	public void write(byte[] b) throws IOException {
		// to /dev/null
	}
}
