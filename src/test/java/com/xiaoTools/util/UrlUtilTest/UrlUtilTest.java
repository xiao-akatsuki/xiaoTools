package com.xiaoTools.util.UrlUtilTest;

import java.net.URL;

import com.xiaoTools.util.urlUtil.URLUtil;

import org.junit.Test;

public class UrlUtilTest {

	@Test
	public void test_url() throws Exception{
		String a = "https://github.com/xiao-organization/xiaoTools/wiki/2.-StrUtil";
		URL url = URLUtil.url(a);
		// github.com
		System.out.println(url.getAuthority());
	}

	@Test
	public void test_normalize(){
		String a = "http://github.com//xiao-organization/\\xiaoTools";
		String b = URLUtil.normalize(a);
		// http://github.com//xiao-organization//xiaoTools
		System.out.println(b);
	}

	@Test
	public void test_encode_and_decode(){
		String a = "xiaoTools的图标.jpg";
		String b = URLUtil.encode(a);
		// xiaoTools%E7%9A%84%E5%9B%BE%E6%A0%87.jpg
		System.out.println(b);

		String c = URLUtil.decode(b);
		// xiaoTools的图标.jpg
		System.out.println(c);
	}

}
