package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.data.OnDatum;

/**
 * On Sql模组
 *
 * @author 白超
 * @date 2019/5/18
 */
public class OnSqlModel<T extends OnSqlModel<T>> extends SqlModel<T, OnDatum> {

    public OnSqlModel(OnSqlDataBuilder<T> onSqlDataBuilder) {
        super(onSqlDataBuilder);
    }

    @Override
    protected OnSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (OnSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
