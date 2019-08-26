package pub.avalon.sqlhelper.core.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.spi.cache.Cache;

/**
 * Cache Test
 *
 * @author baichao
 */
public class CacheTest {

    @Test
    void Test_createCache() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .build();
        Cache<String, String> cache = cacheManager.createCache("String", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class));
        cache.put("1", "1");
        Assertions.assertEquals("1", cache.get("1"));
    }

    @Test
    void Test_getCache() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .build();
        Cache<String, String> cache = cacheManager.createCache("String", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class));
        cache.put("1", "1");
        cache = cacheManager.getCache("String", String.class, String.class);
        Assertions.assertEquals("1", cache.get("1"));
    }
}