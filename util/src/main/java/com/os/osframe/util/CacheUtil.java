package com.os.osframe.util;

import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存工具类
 * Created by wangchun on 16/4/3.
 */
public class CacheUtil {
    //是否调试
    private boolean debug = false;
    //缓存对象
    private HashMap<String, Object> cache = null;
    //锁
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notEmpty = lock.newCondition();

    public CacheUtil(boolean debug) {
        this.debug = debug;
        cache = new HashMap<String, Object>();
    }

    public CacheUtil() {
        this.debug = false;
        cache = new HashMap<String, Object>();
    }
    /**
     * 设置debug便于打印
     * @param debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    /**
     * 将值缓存
     * @param key 缓存key
     * @param value 缓存值
     */
    public void put(String key, Object value) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        cache.put(key, value);
        notEmpty.signal();
        lock.unlock();
        if (debug) {
            System.out.println("CacheUtil缓存了:"+key+" 的值为： " + value);
        }

    }
    /**
     * 获取缓存值
     * @param key
     * @return
     */
    public Object getValue(String key) {
        Object value = cache.get(key);
        if (value == null) {
            return null;
        }
        if (debug) {
            System.out.println("从CacheUtil中获取到:"+key+" 的值为： " + value);
        }
        return value;
    }
    /**
     * 获取缓存值
     * @param key
     * @return
     */
    public Object get(String key) {
        return getValue(key);
    }
    /**
     * 删除缓存
     * @param key
     * @return
     */
    public Object remove(String key) {
        Object value = (Object) cache.get(key);
        if (value == null) {
            return null;
        }
        final ReentrantLock lock = this.lock;
        lock.lock();
        value = (Object) cache.remove(key);
        if (debug) {
            System.out.println("从CacheUtil中移除了:"+key);
        }
        notEmpty.signal();
        lock.unlock();
        return value;
    }
    /**
     * 清空缓存
     */
    public void clearCache() {
        cache.clear();
        System.gc();
    }
}
