package com.xiaoTools.core.io.fastByteBuffer;

/**
 * [快速缓冲](Fast buffer)
 * @description zh - 快速缓冲
 * @description en - Fast buffer
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-22 12:37:58
 */
public class FastByteBuffer {
	/**
	 * 缓冲集
	 */
	private byte[][] buffers = new byte[16][];
	/**
	 * 缓冲数
	 */
	private int buffersCount;
	/**
	 * 当前缓冲索引
	 */
	private int currentBufferIndex = -1;
	/**
	 * 当前缓冲
	 */
	private byte[] currentBuffer;
	/**
	 * 当前缓冲偏移量
	 */
	private int offset;
	/**
	 * 缓冲字节数
	 */
	private int size;
	/**
	 * 一个缓冲区的最小字节数
	 */
	private final int minChunkLen;

	public FastByteBuffer() {
		this.minChunkLen = 1024;
	}

	public FastByteBuffer(int size) {
		this.minChunkLen = Math.abs(size);
	}

	private void needNewBuffer(int newSize) {
		int delta = newSize - size;
		int newBufferSize = Math.max(minChunkLen, delta);

		currentBufferIndex++;
		currentBuffer = new byte[newBufferSize];
		offset = 0;

		if (currentBufferIndex >= buffers.length) {
			int newLen = buffers.length << 1;
			byte[][] newBuffers = new byte[newLen][];
			System.arraycopy(buffers, 0, newBuffers, 0, buffers.length);
			buffers = newBuffers;
		}
		buffers[currentBufferIndex] = currentBuffer;
		buffersCount++;
	}

	public FastByteBuffer append(byte[] array, int off, int len) {
		int end = off + len;
		if ((off < 0) || (len < 0) || (end > array.length)) {
			throw new IndexOutOfBoundsException();
		}
		if (len == 0) {
			return this;
		}
		int newSize = size + len;
		int remaining = len;

		if (currentBuffer != null) {
			int part = Math.min(remaining, currentBuffer.length - offset);
			System.arraycopy(array, end - remaining, currentBuffer, offset, part);
			remaining -= part;
			offset += part;
			size += part;
		}

		if (remaining > 0) {
			needNewBuffer(newSize);

			int part = Math.min(remaining, currentBuffer.length - offset);
			System.arraycopy(array, end - remaining, currentBuffer, offset, part);
			offset += part;
			size += part;
		}

		return this;
	}

	public FastByteBuffer append(byte[] array) {
		return append(array, 0, array.length);
	}

	public FastByteBuffer append(byte element) {
		if ((currentBuffer == null) || (offset == currentBuffer.length)) {
			needNewBuffer(size + 1);
		}

		currentBuffer[offset] = element;
		offset++;
		size++;

		return this;
	}

	public FastByteBuffer append(FastByteBuffer buff) {
		if (buff.size == 0) {
			return this;
		}
		for (int i = 0; i < buff.currentBufferIndex; i++) {
			append(buff.buffers[i]);
		}
		append(buff.currentBuffer, 0, buff.offset);
		return this;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int index() {
		return currentBufferIndex;
	}

	public int offset() {
		return offset;
	}

	public byte[] array(int index) {
		return buffers[index];
	}

	public void reset() {
		size = 0;
		offset = 0;
		currentBufferIndex = -1;
		currentBuffer = null;
		buffersCount = 0;
	}

	public byte[] toArray() {
		int pos = 0;
		byte[] array = new byte[size];

		if (currentBufferIndex == -1) {
			return array;
		}

		for (int i = 0; i < currentBufferIndex; i++) {
			int len = buffers[i].length;
			System.arraycopy(buffers[i], 0, array, pos, len);
			pos += len;
		}

		System.arraycopy(buffers[currentBufferIndex], 0, array, pos, offset);

		return array;
	}

	public byte[] toArray(int start, int len) {
		int remaining = len;
		int pos = 0;
		byte[] array = new byte[len];

		if (len == 0) {
			return array;
		}

		int i = 0;
		while (start >= buffers[i].length) {
			start -= buffers[i].length;
			i++;
		}

		while (i < buffersCount) {
			byte[] buf = buffers[i];
			int c = Math.min(buf.length - start, remaining);
			System.arraycopy(buf, start, array, pos, c);
			pos += c;
			remaining -= c;
			if (remaining == 0) {
				break;
			}
			start = 0;
			i++;
		}
		return array;
	}

	public byte get(int index) {
		if ((index >= size) || (index < 0)) {
			throw new IndexOutOfBoundsException();
		}
		int ndx = 0;
		while (true) {
			byte[] b = buffers[ndx];
			if (index < b.length) {
				return b[index];
			}
			ndx++;
			index -= b.length;
		}
	}
}
