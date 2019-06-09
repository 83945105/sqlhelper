package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.WhereSqlDataBuilder;
import pub.avalon.sqlhelper.core.data.WhereDatum;

/**
 * Where 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class WhereHelper<T extends WhereHelper<T>> extends Helper<T, WhereDatum> {

    public WhereHelper(WhereSqlDataBuilder<T> whereSqlDataBuilder) {
        super(whereSqlDataBuilder);
    }

    @Override
    protected WhereSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (WhereSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
