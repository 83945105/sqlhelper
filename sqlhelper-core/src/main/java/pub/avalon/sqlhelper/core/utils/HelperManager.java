package pub.avalon.sqlhelper.core.utils;

import pub.avalon.holygrail.utils.GenericsUtils;
import pub.avalon.sqlhelper.core.beans.TableColumn;
import pub.avalon.sqlhelper.core.cache.ClassCacheManager;
import pub.avalon.sqlhelper.core.cache.core.CacheConfigurationBuilder;
import pub.avalon.sqlhelper.core.cache.core.CacheManager;
import pub.avalon.sqlhelper.core.cache.core.CacheManagerBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.engine.builder.SqlColumn;
import pub.avalon.sqlhelper.core.engine.builder.SqlJoin;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;
import pub.avalon.sqlhelper.core.helper.JoinHelper;
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

    private final static String DEFAULT_TABLE_HELPER_CACHE_NAME = "DEFAULT_TABLE_HELPER_CACHE_NAME";
    private final static String DEFAULT_COLUMN_DATA_CACHE_NAME = "DEFAULT_COLUMN_DATA_CACHE_NAME";

    private final static Cache<Class, TableHelper> DEFAULT_TABLE_HELPER_CACHE;

    private final static Cache<Class, ColumnDatumList> DEFAULT_COLUMN_DATA_CACHE;

    static {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        DEFAULT_TABLE_HELPER_CACHE = cacheManager
                .createCache(DEFAULT_TABLE_HELPER_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, TableHelper.class));
        DEFAULT_COLUMN_DATA_CACHE = cacheManager
                .createCache(DEFAULT_COLUMN_DATA_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, ColumnDatumList.class));
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
    public static List<ColumnDatum> defaultColumnData(Class<?> clazz) {
        List<ColumnDatum> columnData = DEFAULT_COLUMN_DATA_CACHE.get(clazz);
        if (columnData == null) {
            Set<TableColumn> tableColumns = defaultTableHelper(clazz).getTableColumns();
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

    public static List<ColumnDatum> defaultColumnData(ColumnHelper columnHelper) {
        return defaultColumnData(columnHelper.getDefaultTableHelper().getClass());
    }

    @SuppressWarnings("unchecked")
    public static <T extends JoinHelper<T>> T findExpectAncestorsJoinHelperClassGenricType(SqlJoin<T> sqlJoin) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlJoin.getClass(), JoinHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColumnHelper<T>> T findExpectAncestorsColumnHelperClassGenricType(SqlColumn<T> sqlColumn) {
        return (T) ClassCacheManager.getInstance().newInstance(GenericsUtils.getExpectAncestorsClassGenricType(sqlColumn.getClass(), ColumnHelper.class));
    }

    private final static class ColumnDatumList extends ArrayList<ColumnDatum> {
        private ColumnDatumList(int initialCapacity) {
            super(initialCapacity);
        }
    }
}