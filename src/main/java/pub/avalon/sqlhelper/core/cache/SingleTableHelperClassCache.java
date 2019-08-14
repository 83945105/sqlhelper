package pub.avalon.sqlhelper.core.cache;

import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baichao
 * @date 2019/7/22
 */
public class SingleTableHelperClassCache implements Cache<Class, TableHelper> {

    private final static ConcurrentHashMap<Class, TableHelper> CLASS_TABLE_HELPER_MAP = new ConcurrentHashMap<>(64);

    @Override
    public TableHelper get(Class key) {
        return CLASS_TABLE_HELPER_MAP.get(key);
    }

    @Override
    public void put(Class key, TableHelper value) {
        CLASS_TABLE_HELPER_MAP.put(key, value);
    }

    @Override
    public boolean containsKey(Class key) {
        return CLASS_TABLE_HELPER_MAP.containsKey(key);
    }

    @Override
    public void remove(Class key) {
        CLASS_TABLE_HELPER_MAP.remove(key);
    }

    @Override
    public void clear() {
        CLASS_TABLE_HELPER_MAP.clear();
    }
}
