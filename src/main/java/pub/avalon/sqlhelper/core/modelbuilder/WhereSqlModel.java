package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.data.WhereDatum;

/**
 * On Sql模组
 *
 * @author 白超
 * @date 2019/5/18
 */
public class WhereSqlModel<T extends WhereSqlModel<T>> extends SqlModel<T, WhereDatum> {

    public WhereSqlModel(WhereSqlDataBuilder<T> whereSqlDataBuilder) {
        super(whereSqlDataBuilder);
    }

    @Override
    protected WhereSqlDataBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (WhereSqlDataBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
