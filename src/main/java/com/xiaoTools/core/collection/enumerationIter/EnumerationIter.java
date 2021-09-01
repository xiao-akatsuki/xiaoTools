package com.xiaoTools.core.collection.enumerationIter;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * [Enumeration 对象转 Iterator 对象](Enumeration object to iterator object)
 * @description zh - Enumeration 对象转 Iterator 对象
 * @description en - Enumeration object to iterator object
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-01 17:11:39
 */
public class EnumerationIter<E> implements Iterator<E>, Iterable<E>, Serializable {
    private static final long serialVersionUID = 1L;

    private final Enumeration<E> enumeration;

    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 17:13:07
     * @param enumeration Enumeration
     */
	public EnumerationIter(Enumeration<E> enumeration) {
		this.enumeration = enumeration;
	}

	@Override
	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}

	@Override
	public E next() {
		return enumeration.nextElement();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		return this;
	}

    
}
