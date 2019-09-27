package pub.avalon.sqlhelper.core.utils;

import pub.avalon.holygrail.utils.GenericsUtils;
import pub.avalon.sqlhelper.core.beans.TableColumn;
import pub.avalon.sqlhelper.core.cache.ClassCacheManager;
import pub.avalon.sqlhelper.core.cache.core.CacheConfigurationBuilder;
import pub.avalon.sqlhelper.core.cache.core.CacheManager;
import pub.avalon.sqlhelper.core.cache.core.CacheManagerBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.engine.builder.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.spi.cache.Cache;

import java.util.*;

/**
 * @author baichao
 */
public class HelperManager {

    private final static String DEFAULT_TABLE_HELPER_CACHE_NAME = "DEFAULT_TABLE_HELPER_CACHE";
    private final static String DEFAULT_COLUMN_DATA_CACHE_NAME = "DEFAULT_COLUMN_DATA_CACHE";

    private final static Cache<Class, TableHelper> DEFAULT_TABLE_HELPER_CACHE;

    private final static Cache<Class, ColumnDataCache> DEFAULT_COLUMN_DATA_CACHE;

    static {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        DEFAULT_TABLE_HELPER_CACHE = cacheManager
                .createCache(DEFAULT_TABLE_HELPER_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, TableHelper.class));
        DEFAULT_COLUMN_DATA_CACHE = cacheManager
                .createCache(DEFAULT_COLUMN_DATA_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, ColumnDataCache.class));
    }

    private HelperManager() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper> T defaultTableHelper(Class<?> clazz) {
        TableHelper singleTableHelper = DEFAULT_TABLE_HELPER_CACHE.get(clazz);
        if (singleTableHelper == null) {
            singleTableHelper = newTableHelper(clazz).getDefaultInstance();
            DEFAULT_TABLE_HELPER_CACHE.put(clazz, singleTableHelper);
        }
        return (T) singleTableHelper;
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper> T newTableHelper(Class<?> clazz) {
        return (T) ClassCacheManager.getInstance().newInstance(clazz);
    }

    @SuppressWarnings("unchecked")
    public static List<ColumnDatum> defaultColumnData(Class<?> clazz, String tableAlias) {
        ColumnDataCache columnDataCache = DEFAULT_COLUMN_DATA_CACHE.get(clazz);
        if (columnDataCache == null) {
            columnDataCache = new ColumnDataCache();
        }
        ColumnDatumList columnData = columnDataCache.get(tableAlias);
        if (columnData == null) {
            Set<TableColumn> tableColumns = defaultTableHelper(clazz).getTableColumns();
            if (tableColumns == null) {
                return Collections.emptyList();
            }
            columnData = new ColumnDatumList(tableColumns.size());
            for (TableColumn tableColumn : tableColumns) {
                ColumnDatum columnDatum = new ColumnDatum(tableColumn.getTableName(), tableColumn.getTableAlias(), tableColumn.getName(), tableColumn.getAlias(), tableColumn.getAlias());
                columnDatum.setTableAlias(tableAlias);
                columnData.add(columnDatum);
            }
            columnDataCache.put(tableAlias, columnData);
        }
        return columnData;
    }

    public static List<ColumnDatum> defaultColumnData(ColumnHelper columnHelper) {
        return defaultColumnData(columnHelper.getDefaultTableHelper().getClass(), columnHelper.getTableAlias());
    }

    @SuppressWarnings("unchecked")
    public static <T extends OnHelper<T>> T findOnHelperClassFromAncestorsGenericType(SqlJoin<T> sqlJoin) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlJoin.getClass(), OnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends OnHelper<T>> T findOnHelperClassFromAncestorsGenericType(SqlOn<T> sqlOn) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlOn.getClass(), OnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColumnHelper<T>> T findColumnHelperClassFromAncestorsGenericType(SqlColumn<T> sqlColumn) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlColumn.getClass(), ColumnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends WhereHelper<T>> T findWhereHelperClassFromAncestorsGenericType(SqlWhere<T> sqlWhere) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlWhere.getClass(), WhereHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends GroupHelper<T>> T findGroupHelperClassFromAncestorsGenericType(SqlGroup<T> sqlGroup) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlGroup.getClass(), GroupHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends HavingHelper<T>> T findHavingHelperClassFromAncestorsGenericType(SqlHaving<T> sqlHaving) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlHaving.getClass(), HavingHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends SortHelper<T>> T findSortHelperClassFromAncestorsGenericType(SqlSort<T> sqlSort) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlSort.getClass(), SortHelper.class));
    }

    private final static class ColumnDatumList extends ArrayList<ColumnDatum> {
        private ColumnDatumList(int initialCapacity) {
            super(initialCapacity);
        }
    }

    private final static class ColumnDataCache implements Cache<String, ColumnDatumList> {

        private final static String COLUMN_DATA_CACHE_NAME = "COLUMN_DATA_CACHE";

        private final Cache<String, ColumnDatumList> columnDataCache;

        public ColumnDataCache() {
            columnDataCache = CacheManagerBuilder.newCacheManagerBuilder().build()
                    .createCache(COLUMN_DATA_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, ColumnDatumList.class));
        }

        @Override
        public ColumnDatumList get(String key) {
            return columnDataCache.get(key);
        }

        @Override
        public void put(String key, ColumnDatumList value) {
            columnDataCache.put(key, value);
        }

        @Override
        public boolean containsKey(String key) {
            return columnDataCache.containsKey(key);
        }

        @Override
        public void remove(String key) {
            columnDataCache.remove(key);
        }

        @Override
        public Map<String, ColumnDatumList> getAll(Set<? extends String> keys) {
            return columnDataCache.getAll(keys);
        }

        @Override
        public void putAll(Map<? extends String, ? extends ColumnDatumList> entries) {
            columnDataCache.putAll(entries);
        }

        @Override
        public void removeAll(Set<? extends String> keys) {
            columnDataCache.removeAll(keys);
        }

        @Override
        public void clear() {
            columnDataCache.clear();
        }

        @Override
        public ColumnDatumList putIfAbsent(String key, ColumnDatumList value) {
            return columnDataCache.putIfAbsent(key, value);
        }

        @Override
        public boolean remove(String key, ColumnDatumList value) {
            return columnDataCache.remove(key, value);
        }

        @Override
        public ColumnDatumList replace(String key, ColumnDatumList value) {
            return columnDataCache.replace(key, value);
        }

        @Override
        public boolean replace(String key, ColumnDatumList oldValue, ColumnDatumList newValue) {
            return columnDataCache.replace(key, oldValue, newValue);
        }
    }
}