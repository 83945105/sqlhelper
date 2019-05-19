package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.data.SortDatum;

/**
 * On Sql模组
 *
 * @author 白超
 * @date 2019/5/18
 */
public class SortSqlModel<T extends SortSqlModel<T>> extends SqlModel<T, SortDatum> {

    public SortSqlModel(SortSqlDataBuilder<T> sortSqlDataBuilder) {
        super(sortSqlDataBuilder);
    }

    @Override
    protected SortSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (SortSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
