package com.xiaoTools.core.io.lineIter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.util.ioUtil.IoUtil;

/**
 * [将Reader包装为一个按照行读取的Iterator](将Reader包装为一个按照行读取的Iterator)
 * @description zh - 将Reader包装为一个按照行读取的Iterator
 * @description en - 将Reader包装为一个按照行读取的Iterator
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-24 10:55:30
 */
public class LineIter implements Iterator<String>, Iterable<String>, Closeable, Serializable {

	private static final long serialVersionUID = 1L;


	private final BufferedReader bufferedReader;

	private String cachedLine;

	private boolean finished = false;

	public LineIter(InputStream in, Charset charset) throws IllegalArgumentException {
		this(IoUtil.getReader(in, charset));
	}

	public LineIter(Reader reader) throws IllegalArgumentException {
		Assertion.notNull(reader, "Reader must not be null");
		this.bufferedReader = IoUtil.getReader(reader);
	}

	@Override
	public boolean hasNext() throws IORuntimeException {
		if (cachedLine != null) {
			return true;
		} else if (finished) {
			return false;
		} else {
			try {
				while (true) {
					String line = bufferedReader.readLine();
					if (line == null) {
						finished = true;
						return false;
					} else if (isValidLine(line)) {
						cachedLine = line;
						return true;
					}
				}
			} catch (IOException ioe) {
				close();
				throw new IORuntimeException(ioe);
			}
		}
	}

	@Override
	public String next() throws NoSuchElementException {
		return nextLine();
	}

	public String nextLine() throws NoSuchElementException {
		if (false == hasNext()) {
			throw new NoSuchElementException("No more lines");
		}
		String currentLine = this.cachedLine;
		this.cachedLine = null;
		return currentLine;
	}

	@Override
	public void close() {
		finished = true;
		IoUtil.close(bufferedReader);
		cachedLine = null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Remove unsupported on LineIterator");
	}

	protected boolean isValidLine(String line) {
		return true;
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}
}
