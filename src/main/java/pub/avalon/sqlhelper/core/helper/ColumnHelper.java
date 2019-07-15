package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.ColumnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;

/**
 * 列助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class ColumnHelper<T extends ColumnHelper<T>> extends Helper<T, ColumnDatum> {

    public ColumnHelper(String tableAlias) {
        super(tableAlias, new ColumnSqlPartDatumBuilder<>(tableAlias));
    }

    @Override
    protected ColumnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        return (ColumnSqlPartDatumBuilder<T>) super.apply(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

}
