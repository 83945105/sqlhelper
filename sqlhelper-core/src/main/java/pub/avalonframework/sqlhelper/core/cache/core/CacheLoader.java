package pub.avalonframework.sqlhelper.core.cache.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.avalonframework.sqlhelper.core.cache.spi.service.impl.SelfCache;
import pub.avalonframework.sqlhelper.core.spi.cache.Cache;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author baichao
 */
public final class CacheLoader {

    private final Logger logger = LoggerFactory.getLogger(CacheLoader.class);

    private final static CacheLoader CACHE_LOADER = new CacheLoader();

    public CacheHolder load(Class<?> keyType, Class<?> valueType) {
        Iterator<Cache> caches = ServiceLoader.load(Cache.class).iterator();
        if (!caches.hasNext()) {
            return new CacheHolder(keyType, valueType, new SelfCache<>());
        }
        Cache<?, ?> cache = caches.next();
        if (caches.hasNext()) {
            logger.warn("There are more than one cache loaders existing, chosen first one by default.");
        }
        return new CacheHolder(keyType, valueType, cache);
    }

    public static CacheLoader getInstance() {
        return CACHE_LOADER;
    }
}