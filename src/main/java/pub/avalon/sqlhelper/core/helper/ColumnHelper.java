package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.ColumnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;

/**
 * 列助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class ColumnHelper<T extends ColumnHelper<T>> extends Helper<T, ColumnDatum> {

    public ColumnHelper() {
        super(new ColumnSqlPartDatumBuilder<>());
    }

    @Override
    protected ColumnSqlPartDatumBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (ColumnSqlPartDatumBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
