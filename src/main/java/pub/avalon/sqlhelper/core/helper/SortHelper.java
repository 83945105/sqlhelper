package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.SortDatum;

/**
 * Sort 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class SortHelper<T extends SortHelper<T>> extends Helper<T, SortDatum> {

    public SortHelper() {
        super(new SortSqlPartDatumBuilder<>());
    }

    @Override
    protected SortSqlPartDatumBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (SortSqlPartDatumBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
