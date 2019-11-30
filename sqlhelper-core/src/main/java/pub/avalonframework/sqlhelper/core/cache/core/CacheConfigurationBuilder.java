package pub.avalonframework.sqlhelper.core.cache.core;

/**
 * @author baichao
 */
public final class CacheConfigurationBuilder<K, V> implements Builder<CacheConfiguration<K, V>> {

    private final Class<K> keyType;

    private final Class<V> valueType;

    private CacheConfigurationBuilder(Class<K> keyType, Class<V> valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    public static <K, V> CacheConfigurationBuilder<K, V> newCacheConfigurationBuilder(Class<K> keyType, Class<V> valueType) {
        return new CacheConfigurationBuilder<>(keyType, valueType);
    }

    @Override
    public CacheConfiguration<K, V> build() {
        return new DefaultCacheConfiguration<>(keyType, valueType);
    }
}