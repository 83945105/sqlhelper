package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.SortDatum;

/**
 * 排序助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class SortHelper<T extends SortHelper<T>> extends Helper<T, SortDatum> {

    public SortHelper(String tableAlias) {
        super(tableAlias, new SortSqlPartDatumBuilder<>(tableAlias));
    }

    @Override
    protected SortSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        return (SortSqlPartDatumBuilder<T>) super.apply(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

}
