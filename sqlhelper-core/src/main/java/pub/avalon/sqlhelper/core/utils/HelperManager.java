package pub.avalon.sqlhelper.core.utils;

import pub.avalon.sqlhelper.core.beans.TableColumn;
import pub.avalon.sqlhelper.core.cache.ClassCacheManager;
import pub.avalon.sqlhelper.core.cache.core.CacheConfigurationBuilder;
import pub.avalon.sqlhelper.core.cache.core.CacheManager;
import pub.avalon.sqlhelper.core.cache.core.CacheManagerBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.helper.TableHelper;
import pub.avalon.sqlhelper.core.spi.cache.Cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author baichao
 */
public class HelperManager {

    private final static String SINGLE_TABLE_HELPER_CACHE_NAME = "SINGLE_TABLE_HELPER_CACHE_NAME";
    private final static String DEFAULT_COLUMN_DATA_CACHE_NAME = "DEFAULT_COLUMN_DATA_CACHE_NAME";

    private final static Cache<Class, TableHelper> SINGLE_TABLE_HELPER_CACHE;

    private final static Cache<Class, ColumnDatumList> DEFAULT_COLUMN_DATA_CACHE;

    static {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        SINGLE_TABLE_HELPER_CACHE = cacheManager
                .createCache(SINGLE_TABLE_HELPER_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, TableHelper.class));
        DEFAULT_COLUMN_DATA_CACHE = cacheManager
                .createCache(DEFAULT_COLUMN_DATA_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, ColumnDatumList.class));
    }

    private HelperManager() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper> T singleTableHelper(Class<?> clazz) {
        TableHelper singleTableHelper = SINGLE_TABLE_HELPER_CACHE.get(clazz);
        if (singleTableHelper == null) {
            singleTableHelper = newTableHelper(clazz).getDefaultInstance();
            SINGLE_TABLE_HELPER_CACHE.put(clazz, singleTableHelper);
        }
        return (T) singleTableHelper;
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper> T newTableHelper(Class<?> clazz) {
        return (T) ClassCacheManager.getInstance().newInstance(clazz);
    }

    @SuppressWarnings("unchecked")
    public static List<ColumnDatum> defaultColumnData(Class<?> clazz) {
        List<ColumnDatum> columnData = DEFAULT_COLUMN_DATA_CACHE.get(clazz);
        if (columnData == null) {
            Set<TableColumn> tableColumns = singleTableHelper(clazz).getTableColumns();
            if (tableColumns == null) {
                return Collections.emptyList();
            }
            columnData = new ColumnDatumList(tableColumns.size());
            for (TableColumn tableColumn : tableColumns) {
                columnData.add(new ColumnDatum(tableColumn.getTableName(), tableColumn.getTableAlias(), tableColumn.getName(), tableColumn.getAlias(), tableColumn.getAlias()));
            }
        }
        return columnData;
    }

    private final static class ColumnDatumList extends ArrayList<ColumnDatum> {
        private ColumnDatumList(int initialCapacity) {
            super(initialCapacity);
        }
    }
}