package com.xiaoTools.core.collection.iteratorEnumeration;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * [Iterator 对象转 Enumeration](Iterator object to Enumeration)
 * @description zh - Iterator 对象转 Enumeration
 * @description en - Iterator object to Enumeration
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-01 16:48:30
 */
public class IteratorEnumeration<E> implements Enumeration<E>, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Iterator
     */
    private final Iterator<E> iterator;

    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 16:50:26
     * @param iterator Iterator
     */
    public IteratorEnumeration(Iterator<E> iterator) {
		this.iterator = iterator;
	}

    @Override
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	@Override
	public E nextElement() {
		return iterator.next();
	}
}
