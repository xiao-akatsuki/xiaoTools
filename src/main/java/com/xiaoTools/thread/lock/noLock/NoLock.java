package com.xiaoTools.thread.lock.noLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * [无锁实现](Lock free implementation)
 * @description: zh - 无锁实现
 * @description: en - Lock free implementation
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/24 2:19 下午
*/
public class NoLock implements Lock {

    @Override
    public void lock() {
    }

    @Override
    public void lockInterruptibly() {
    }

    @Override
    public boolean tryLock() {
        return true;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return true;
    }

    @Override
    public void unlock() {
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("NoLock`s newCondition method is unsupported");
    }

}
