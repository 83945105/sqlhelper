package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.WhereDatum;

/**
 * Where 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class WhereHelper<T extends WhereHelper<T>> extends Helper<T, WhereDatum> {

    public WhereHelper() {
        super(new WhereSqlPartDatumBuilder<>());
    }

    @Override
    protected WhereSqlPartDatumBuilder<T> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        return (WhereSqlPartDatumBuilder<T>) super.apply(tableName, tableAlias, columnName, columnAlias);
    }

}
