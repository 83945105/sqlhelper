package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.WhereDatum;

/**
 * 条件助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class WhereHelper<T extends WhereHelper<T>> extends Helper<T, WhereDatum> {

    public WhereHelper(String tableAlias) {
        super(new WhereSqlPartDatumBuilder<>(tableAlias));
    }

    @Override
    protected WhereSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        return (WhereSqlPartDatumBuilder<T>) super.apply(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

}
