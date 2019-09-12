package pub.avalon.sqlhelper.core.utils;

import pub.avalon.sqlhelper.core.cache.ClassCacheManager;
import pub.avalon.sqlhelper.core.cache.core.CacheConfigurationBuilder;
import pub.avalon.sqlhelper.core.cache.core.CacheManagerBuilder;
import pub.avalon.sqlhelper.core.helper.TableHelper;
import pub.avalon.sqlhelper.core.spi.cache.Cache;

/**
 * @author baichao
 */
public class HelperManager {

    private final static String SINGLE_TABLE_HELPER_CACHE_NAME = "SINGLE_TABLE_HELPER_CACHE_NAME";

    private final static Cache<Class, TableHelper> SINGLE_TABLE_HELPER_CACHE;

    static {
        SINGLE_TABLE_HELPER_CACHE = CacheManagerBuilder.newCacheManagerBuilder().build()
                .createCache(SINGLE_TABLE_HELPER_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, TableHelper.class));
    }

    private HelperManager() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper> T singleTableHelper(Class<T> clazz) {
        TableHelper singleTableHelper = SINGLE_TABLE_HELPER_CACHE.get(clazz);
        if (singleTableHelper == null) {
            singleTableHelper = newTableHelper(clazz).getSingleHelper();
            SINGLE_TABLE_HELPER_CACHE.put(clazz, singleTableHelper);
        }
        return (T) singleTableHelper;
    }

    public static <T extends TableHelper> T newTableHelper(Class<T> clazz) {
        return ClassCacheManager.getInstance().newInstance(clazz);
    }
}