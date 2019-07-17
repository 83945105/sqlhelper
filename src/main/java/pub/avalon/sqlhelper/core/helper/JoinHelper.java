package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.JoinSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.OnDatum;

/**
 * Join 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class JoinHelper<T extends JoinHelper<T>> extends Helper<T, OnDatum> {

    public JoinHelper(String tableAlias) {
        super(tableAlias, new JoinSqlPartDatumBuilder<>(tableAlias));
    }

    @Override
    protected JoinSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        return (JoinSqlPartDatumBuilder<T>) super.apply(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

}
