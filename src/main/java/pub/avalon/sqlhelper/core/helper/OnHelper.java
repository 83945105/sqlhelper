package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.OnSqlDataBuilder;
import pub.avalon.sqlhelper.core.data.OnDatum;

/**
 * On 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class OnHelper<T extends OnHelper<T>> extends Helper<T, OnDatum> {

    public OnHelper(OnSqlDataBuilder<T> onSqlDataBuilder) {
        super(onSqlDataBuilder);
    }

    @Override
    protected OnSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (OnSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
