package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.SortType;
import pub.avalon.sqlhelper.core.data.SortDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * 排序Sql片段数据构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SortSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, SortDatum> {

    private SortDatum sortDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public SortSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {

    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.sortDatum = new SortDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    /**
     * 升序
     *
     * @return 当前操作的排序模组
     */
    public T asc() {
        this.sortDatum.setSortType(SortType.ASC);
        this.addSqlPartDatum(this.sortDatum);
        return this.getHelper();
    }

    /**
     * 降序
     *
     * @return 当前操作的排序模组
     */
    public T desc() {
        this.sortDatum.setSortType(SortType.DESC);
        this.addSqlPartDatum(this.sortDatum);
        return this.getHelper();
    }

}
