package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.data.ColumnDatum;

/**
 * 列sql构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class ColumnSqlDataBuilder<S extends SqlModel<S, ColumnDatum>> extends AbstractSqlDataBuilder<S, ColumnDatum> {

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.addSqlModelDatum(new ColumnDatum(tableName, tableAlias, columnName, columnAlias));
    }

}
