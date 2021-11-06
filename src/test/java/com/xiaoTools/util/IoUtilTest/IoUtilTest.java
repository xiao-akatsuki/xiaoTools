package com.xiaoTools.util.IoUtilTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.ioUtil.IoUtil;

import org.junit.Test;

public class IoUtilTest {

	@Test
	public void test_demo(){
		BufferedInputStream in = FileUtil.getInputStream("d:/test.txt");
		BufferedOutputStream out = FileUtil.getOutputStream("d:/test2.txt");
		long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
	}

}
