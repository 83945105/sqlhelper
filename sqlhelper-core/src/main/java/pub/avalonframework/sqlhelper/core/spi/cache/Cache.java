package pub.avalonframework.sqlhelper.core.spi.cache;

import java.util.Map;
import java.util.Set;

/**
 * @author baichao
 */
public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    boolean containsKey(K key);

    void remove(K key);

    Map<K, V> getAll(Set<? extends K> keys);

    void putAll(Map<? extends K, ? extends V> entries);

    void removeAll(Set<? extends K> keys);

    void clear();

    V putIfAbsent(K key, V value);

    boolean remove(K key, V value);

    V replace(K key, V value);

    boolean replace(K key, V oldValue, V newValue);
}
