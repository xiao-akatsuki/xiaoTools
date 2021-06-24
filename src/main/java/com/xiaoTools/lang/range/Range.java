package com.xiaoTools.lang.range;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.thread.lock.noLock.NoLock;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * [范围生成器。根据给定的初始值、结束值和步进生成一个步进列表生成器](Scope generator. According to the given initial value, end value and step to generate a step list generator)
 * @description: zh - 范围生成器。根据给定的初始值、结束值和步进生成一个步进列表生成器
 * @description: en - Scope generator. According to the given initial value, end value and step to generate a step list generator
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/24 1:30 下午
*/
public class Range<T> implements Iterable<T>, Iterator<T>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 锁保证线程安全
     */
    private Lock lock = new ReentrantLock();
    /**
     * 起始对象
     */
    private final T start;
    /**
     * 结束对象
     */
    private final T end;
    /**
     * 当前对象
     */
    private T current;
    /**
     * 下一个对象
     */
    private T next;
    /**
     * 步进
     */
    private final Steper<T> steper;

    /**
     * [步进接口，此接口用于实现如何对一个对象按照指定步进增加步进](Step interface, this interface is used to realize how to add step to an object according to the specified step)
     * @description: zh - 步进接口，此接口用于实现如何对一个对象按照指定步进增加步进
     * @description: en - Step interface, this interface is used to realize how to add step to an object according to the specified step
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 1:32 下午
    */
    public interface Steper<T> {
        /**
         * [增加步进](Increase step)
         * @description: zh - 增加步进
         * @description: en - Increase step
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/24 1:32 下午
         * @param current: 上一次增加步进后的基础对象
         * @param end: 结束对象
         * @param index: 当前索引（步进到第几个元素），从0开始计数
         * @return T
        */
        T step(T current, T end, int index);
    }
    /**
     * 索引
     */
    private int index = Constant.ZERO;
    /**
     * 是否包含第一个元素
     */
    private final boolean includeStart;
    /**
     * 是否包含最后一个元素
     */
    private boolean includeEnd;

    /**
     * [构造方法](Construction method)
     * @description: zh - 构造方法
     * @description: en - Construction method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 1:34 下午
     * @param start: 起始对象
     * @param steper: 步进
    */
    public Range(T start, Steper<T> steper) {
        this(start, null, steper);
    }

    /**
     * [构造方法](Construction method)
     * @description: zh - 构造方法
     * @description: en - Construction method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 1:45 下午
     * @param start: 起始对象（包含）
     * @param end: 结束对象（包含）
     * @param steper: 步进
    */
    public Range(T start, T end, Steper<T> steper) {
        this(start, end, steper, Constant.TRUE, Constant.TRUE);
    }

    /**
     * [构造方法](Construction method)
     * @description: zh - 构造方法
     * @description: en - Construction method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 1:45 下午
     * @param start: 起始对象
     * @param end: 结束对象
     * @param steper: 步进
     * @param isIncludeStart: 是否包含第一个元素
     * @param isIncludeEnd: 是否包含最后一个元素
    */
    public Range(T start, T end, Steper<T> steper, boolean isIncludeStart, boolean isIncludeEnd) {
        this.start = start;
        this.current = start;
        this.end = end;
        this.steper = steper;
        this.next = safeStep(this.current);
        this.includeStart = isIncludeStart;
        includeEnd = true;
        this.includeEnd = isIncludeEnd;
    }

    /**
     * [禁用锁，调用此方法后不在 使用锁保护](Disable lock. Lock protection is not used after calling this method)
     * @description: zh - 禁用锁，调用此方法后不在 使用锁保护
     * @description: en - Disable lock. Lock protection is not used after calling this method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 2:11 下午
     * @return com.xiaoTools.lang.range.Range<T>
    */
    public Range<T> disableLock() {
        this.lock = new NoLock();
        return this;
    }

    @Override
    public boolean hasNext() {
        lock.lock();
        try {
            if(Constant.ZERO == this.index && this.includeStart) {
                return Constant.TRUE;
            }
            if (Constant.NULL == this.next) {
                return Constant.FALSE;
            } else if (!includeEnd && this.next.equals(this.end)) {
                return Constant.FALSE;
            }
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public T next() {
        lock.lock();
        try {
            if (!this.hasNext()) {
                throw new NoSuchElementException("Has no next range!");
            }
            return nextUncheck();
        } finally {
            lock.unlock();
        }
    }

    /**
     * [获取下一个元素，并将下下个元素准备好](Get the next element and get it ready)
     * @description: zh - 获取下一个元素，并将下下个元素准备好
     * @description: en - Get the next element and get it ready
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 2:13 下午
     * @return T
    */
    private T nextUncheck() {
        if (Constant.ZERO != this.index || !this.includeStart) {
            // 非第一个元素或不包含第一个元素增加步进
            this.current = this.next;
            if (Constant.NULL != this.current) {
                this.next = safeStep(this.next);
            }
        }
        index++;
        return this.current;
    }

    /**
     * [不抛异常的获取下一步进的元素，如果获取失败返回null](Get the element of the next step without throwing an exception. If the get fails, return null)
     * @description: zh - 不抛异常的获取下一步进的元素，如果获取失败返回null
     * @description: en - Get the element of the next step without throwing an exception. If the get fails, return null
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 2:15 下午
     * @param base: 上一个元素
     * @return T
    */
    private T safeStep(T base) {
        T next = null;
        try {
            next = steper.step(base, this.end, this.index);
        } catch (Exception e) {
            // ignore
        }
        return next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Can not remove ranged element!");
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    /**
     * [重置Range](Reset range)
     * @description: zh - 重置Range
     * @description: en - Reset range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 2:16 下午
     * @return com.xiaoTools.lang.range.Range<T>
    */
    public Range<T> reset() {
        lock.lock();
        try {
            this.current = this.start;
            this.index = Constant.ZERO;
        } finally {
            lock.unlock();
        }
        return this;
    }
}
