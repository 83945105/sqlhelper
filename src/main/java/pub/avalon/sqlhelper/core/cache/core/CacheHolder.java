package pub.avalon.sqlhelper.core.cache.core;

import pub.avalon.sqlhelper.spi.cache.Cache;

/**
 * @author baichao
 */
public final class CacheHolder {

    private final Class<?> keyType;

    private final Class<?> valueType;

    private Cache<?, ?> cache;

    @SuppressWarnings("unchecked")
    public <K, V> Cache<K, V> retrieve(Class<K> refKeyType, Class<V> refValueType) {
        if (keyType == refKeyType && valueType == refValueType) {
            return (Cache<K, V>) cache;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public CacheHolder(Class<?> keyType, Class<?> valueType, final Cache<?, ?> cache) {
        this.keyType = keyType;
        this.valueType = valueType;
        this.cache = cache;
    }
}