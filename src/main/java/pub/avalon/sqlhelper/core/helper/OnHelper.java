package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.OnDatum;

/**
 * On 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class OnHelper<T extends OnHelper<T>> extends Helper<T, OnDatum> {

    public OnHelper(String tableAlias) {
        super(new OnSqlPartDatumBuilder<>(tableAlias));
    }

    @Override
    protected OnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        return (OnSqlPartDatumBuilder<T>) super.apply(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

}
