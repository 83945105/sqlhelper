package pub.avalonframework.sqlhelper.core.cache.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.core.spi.cache.Cache;

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

    @Test
    void Test_removeCache() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .build();
        Cache<String, String> cache = cacheManager.createCache("String", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class));
        cache.put("1", "1");
        cacheManager.removeCache("String");
        Assertions.assertThrows(RuntimeException.class, () -> cacheManager.getCache("String", String.class, String.class));
    }

    @Test
    void Test_closeCache() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .build();
        Cache<String, String> cache = cacheManager.createCache("String", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class));
        cache.put("1", "1");
        cacheManager.close();
        Assertions.assertThrows(RuntimeException.class, () -> cacheManager.getCache("String", String.class, String.class));
    }
}