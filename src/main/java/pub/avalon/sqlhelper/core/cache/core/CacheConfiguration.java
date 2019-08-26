package pub.avalon.sqlhelper.core.cache.core;

/**
 * @author baichao
 */
public interface CacheConfiguration<K, V> {

    Class<K> getKeyType();

    Class<V> getValueType();
}