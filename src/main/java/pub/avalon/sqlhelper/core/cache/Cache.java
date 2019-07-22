package pub.avalon.sqlhelper.core.cache;

/**
 * @author 白超
 * @date 2019/7/22
 */
public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    boolean containsKey(K key);

    void remove(K key);

    void clear();

}
