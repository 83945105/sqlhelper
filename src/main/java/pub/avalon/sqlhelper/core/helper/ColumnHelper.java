package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.ColumnSqlDataBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;

/**
 * On 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class ColumnHelper<T extends ColumnHelper<T>> extends Helper<T, ColumnDatum> {

    public ColumnHelper(ColumnSqlDataBuilder<T> columnSqlDataBuilder) {
        super(columnSqlDataBuilder);
    }

    @Override
    protected ColumnSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (ColumnSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
