package pub.avalonframework.sqlhelper.core.cache.core;

import pub.avalonframework.sqlhelper.core.spi.cache.Cache;

/**
 * @author baichao
 */
public interface CacheManager {

    <K, V> Cache<K, V> createCache(String alias, CacheConfiguration<K, V> config);

    <K, V> Cache<K, V> createCache(String alias, Builder<? extends CacheConfiguration<K, V>> configBuilder);

    <K, V> Cache<K, V> getCache(String alias, Class<K> keyType, Class<V> valueType);

    void removeCache(String alias);

    void close();
}