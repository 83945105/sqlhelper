package pub.avalonframework.sqlhelper.core.cache.core;

/**
 * @author baichao
 */
public final class DefaultCacheConfiguration<K, V> implements CacheConfiguration<K, V> {

    private final Class<K> keyType;
    private final Class<V> valueType;

    public DefaultCacheConfiguration(Class<K> keyType, Class<V> valueType) {
        if (keyType == null) {
            throw new NullPointerException("keyType cannot be null");
        }
        if (valueType == null) {
            throw new NullPointerException("valueType cannot be null");
        }
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public Class<K> getKeyType() {
        return this.keyType;
    }

    @Override
    public Class<V> getValueType() {
        return this.valueType;
    }
}