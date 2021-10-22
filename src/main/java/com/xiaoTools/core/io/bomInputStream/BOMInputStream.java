package com.xiaoTools.core.io.bomInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.util.charsetUtil.CharsetUtil;

/**
 * [读取带BOM头的流内容](Read stream content with BOM header)
 * @description zh - 读取带BOM头的流内容
 * @description en - Read stream content with BOM header
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-22 22:49:08
 */
public class BOMInputStream extends InputStream {

	private final PushbackInputStream in;
	private boolean isInited = false;
	private final String defaultCharset;
	private String charset;

	private static final int BOM_SIZE = 4;

	public BOMInputStream(InputStream in) {
		this(in, CharsetUtil.UTF_8);
	}

	public BOMInputStream(InputStream in, String defaultCharset) {
		this.in = new PushbackInputStream(in, BOM_SIZE);
		this.defaultCharset = defaultCharset;
	}

	public String getDefaultCharset() {
		return defaultCharset;
	}

	public String getCharset() {
		if (!isInited) {
			try {
				init();
			} catch (IOException ex) {
				throw new IORuntimeException(ex);
			}
		}
		return charset;
	}

	@Override
	public void close() throws IOException {
		isInited = true;
		in.close();
	}

	@Override
	public int read() throws IOException {
		isInited = true;
		return in.read();
	}

	protected void init() throws IOException {
		if (isInited) {
			return;
		}

		byte[] bom = new byte[BOM_SIZE];
		int n, unread;
		n = in.read(bom, 0, bom.length);

		if ((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00) && (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF)) {
			charset = "UTF-32BE";
			unread = n - 4;
		} else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE) && (bom[2] == (byte) 0x00) && (bom[3] == (byte) 0x00)) {
			charset = "UTF-32LE";
			unread = n - 4;
		} else if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB) && (bom[2] == (byte) 0xBF)) {
			charset = "UTF-8";
			unread = n - 3;
		} else if ((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF)) {
			charset = "UTF-16BE";
			unread = n - 2;
		} else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)) {
			charset = "UTF-16LE";
			unread = n - 2;
		} else {
			charset = defaultCharset;
			unread = n;
		}

		if (unread > 0) {
			in.unread(bom, (n - unread), unread);
		}

		isInited = true;
	}
}
