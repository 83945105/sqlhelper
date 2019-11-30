package pub.avalonframework.sqlhelper.core.cache.core;

/**
 * @author baichao
 */
public final class CacheManagerBuilder<T extends CacheManager> implements Builder<T> {

    private CacheManagerBuilder() {
    }

    public static CacheManagerBuilder<CacheManager> newCacheManagerBuilder() {
        return new CacheManagerBuilder<>();
    }

    @SuppressWarnings("unchecked")
    private T newCacheManager() {
        return (T) new DefaultCacheManager();
    }

    @Override
    public T build() {
        return newCacheManager();
    }
}