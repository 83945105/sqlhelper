package pub.avalon.sqlhelper.core.cache;

import lombok.extern.slf4j.Slf4j;
import pub.avalon.sqlhelper.core.cache.spi.service.impl.SelfCache;
import pub.avalon.sqlhelper.spi.cache.Cache;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author baichao
 */
@Slf4j
public final class CacheLoader {

    private final static CacheLoader CACHE_LOADER = new CacheLoader();

    public CacheHolder load(Class<?> keyType, Class<?> valueType) {
        Iterator<Cache> caches = ServiceLoader.load(Cache.class).iterator();
        if (!caches.hasNext()) {
            return new CacheHolder(keyType, valueType, new SelfCache<>());
        }
        Cache<?, ?> cache = caches.next();
        if (caches.hasNext()) {
            log.warn("There are more than one cache loaders existing, chosen first one by default.");
        }
        return new CacheHolder(keyType, valueType, cache);
    }

    public static CacheLoader getInstance() {
        return CACHE_LOADER;
    }
}