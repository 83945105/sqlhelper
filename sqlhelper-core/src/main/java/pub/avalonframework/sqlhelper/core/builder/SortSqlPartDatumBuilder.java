package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.SortType;
import pub.avalonframework.sqlhelper.core.data.SortDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class SortSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, SortDatum> {

    private SortDatum sortDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public SortSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.sortDatum = new SortDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.sortDatum = new SortDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public SqlBuilderOptions getSqlBuilderOptions() {
        return this.sqlBuilderOptions;
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