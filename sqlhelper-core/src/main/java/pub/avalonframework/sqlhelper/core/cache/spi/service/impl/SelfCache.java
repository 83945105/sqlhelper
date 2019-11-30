package pub.avalonframework.sqlhelper.core.cache.spi.service.impl;

import pub.avalonframework.sqlhelper.core.spi.cache.Cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baichao
 */
public final class SelfCache<K, V> implements Cache<K, V> {

    private ConcurrentHashMap<K, V> store = new ConcurrentHashMap<>(32);

    @Override
    public V get(K key) {
        return store.get(key);
    }

    @Override
    public void put(K key, V value) {
        store.put(key, value);
    }

    @Override
    public boolean containsKey(K key) {
        return store.containsKey(key);
    }

    @Override
    public void remove(K key) {
        store.remove(key);
    }

    @Override
    public Map<K, V> getAll(Set<? extends K> keys) {
        Map<K, V> map = new LinkedHashMap<>(keys.size());
        for (K key : keys) {
            map.put(key, get(key));
        }
        return map;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> entries) {
        store.putAll(entries);
    }

    @Override
    public void removeAll(Set<? extends K> keys) {
        for (K key : keys) {
            remove(key);
        }
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return store.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(K key, V value) {
        return store.remove(key, value);
    }

    @Override
    public V replace(K key, V value) {
        return store.replace(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return store.replace(key, oldValue, newValue);
    }
}