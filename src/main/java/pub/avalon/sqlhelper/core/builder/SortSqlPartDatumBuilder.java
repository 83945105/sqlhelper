package pub.avalon.sqlhelper.core.builder;

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
public final class SortSqlPartDatumBuilder<T extends Helper<T, SortDatum>> extends AbstractSqlPartDatumBuilder<T, SortDatum> {

    private SortDatum sortDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias, String fieldName) {
        this.sortDatum = new SortDatum(tableName, tableAlias, columnName, columnAlias, fieldName);
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
