package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.data.ColumnDatum;

/**
 * On Sql模组
 *
 * @author 白超
 * @date 2019/5/18
 */
public class ColumnSqlModel<T extends ColumnSqlModel<T>> extends SqlModel<T, ColumnDatum> {

    public ColumnSqlModel(ColumnSqlDataBuilder<T> columnSqlDataBuilder) {
        super(columnSqlDataBuilder);
    }

    @Override
    protected ColumnSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (ColumnSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
