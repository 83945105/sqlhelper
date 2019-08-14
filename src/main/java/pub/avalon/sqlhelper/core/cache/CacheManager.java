package pub.avalon.sqlhelper.core.cache;

/**
 * 缓存管理器
 *
 * @author baichao
 * @date 2019/7/22
 */
public interface CacheManager {

    <K, V> Cache<K, V> createCache(String alias);

    <K, V> Cache<K, V> getCache(String alias, Class<K> keyType, Class<V> valueType);

    void removeCache(String alias);
}
