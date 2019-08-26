package pub.avalon.sqlhelper.core.utils;

import pub.avalon.sqlhelper.core.cache.ClassCacheManager;
import pub.avalon.sqlhelper.core.helper.TableHelper;

/**
 * @author baichao
 */
public class HelperManager {

    private HelperManager() {
    }

    public static <T extends TableHelper> T newTableHelper(Class<T> clazz) {
        return ClassCacheManager.getInstance().newInstance(clazz);
    }
}