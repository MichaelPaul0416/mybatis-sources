package com.wq.mybatis.source.demo;

import org.apache.ibatis.cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/22
 * @Description:
 * @Resource:
 */
public class SimpleCustomCacheImpl implements Cache {

    private Map<Object, Object> cache;

    private String cacheName;

    public SimpleCustomCacheImpl(String cacheName) {
        this.cacheName = cacheName;
        cache = new HashMap<>();
    }

    @Override
    public String getId() {
        return this.cacheName;
    }

    @Override
    public void putObject(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return cache.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
