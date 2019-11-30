package pub.avalonframework.sqlhelper.core.cache.core;

import pub.avalonframework.sqlhelper.core.spi.cache.Cache;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author baichao
 */
public final class DefaultCacheManager implements CacheManager {

    private final ConcurrentMap<String, CacheHolder> caches = new ConcurrentHashMap<>();

    @Override
    public <K, V> Cache<K, V> createCache(String alias, CacheConfiguration<K, V> config) {
        if (alias == null) {
            throw new NullPointerException("Alias cannot be null");
        }
        if (caches.containsKey(alias)) {
            ExceptionUtils.aliasAlreadyExistException(alias);
        }
        CacheHolder cacheHolder = CacheLoader.getInstance().load(config.getKeyType(), config.getValueType());
        caches.put(alias, cacheHolder);
        return cacheHolder.retrieve(config.getKeyType(), config.getValueType());
    }

    @Override
    public <K, V> Cache<K, V> createCache(String alias, Builder<? extends CacheConfiguration<K, V>> configBuilder) {
        return createCache(alias, configBuilder.build());
    }

    @Override
    public <K, V> Cache<K, V> getCache(String alias, Class<K> keyType, Class<V> valueType) {
        if (alias == null) {
            throw new NullPointerException("Alias cannot be null");
        }
        CacheHolder cacheHolder = caches.get(alias);
        if (cacheHolder == null) {
            ExceptionUtils.aliasCacheDoesNotExistException(alias);
        }
        return cacheHolder.retrieve(keyType, valueType);
    }

    @Override
    public void removeCache(String alias) {
        if (alias == null) {
            throw new NullPointerException("Alias cannot be null");
        }
        caches.remove(alias);
    }

    @Override
    public void close() {
        caches.clear();
    }
}