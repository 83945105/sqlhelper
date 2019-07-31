package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author 白超
 * @date 2019/7/19
 */
public final class DefaultSqlEngine<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TJ, TC, TW, TG, TH, TS> implements SqlEngine<DefaultSqlEngine> {

    public DefaultSqlEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        super(dataBaseType, tableHelperClass);
    }

    public DefaultSqlEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultSqlEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        super(dataBaseType, tableName, tableHelperClass);
    }

    public DefaultSqlEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultSqlEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableHelperClass, tableAlias);
    }

    public DefaultSqlEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias);
    }

    public DefaultSqlEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public <FJ extends JoinHelper<FJ>> DefaultSqlEngine sqlJoin(SqlJoin<FJ> sqlJoin) {
        SqlEngine.executeJoin(sqlJoin, this.sqlBuilderOptions).forEach(this::addJoinTableDatum);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> DefaultSqlEngine sqlColumn(SqlColumn<FC> sqlColumn) {
        SqlEngine.executeColumn(sqlColumn, this.sqlBuilderOptions).forEach(this::addTableColumnDatum);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> DefaultSqlEngine sqlWhere(SqlWhere<FW> sqlWhere) {
        SqlEngine.executeWhere(sqlWhere, this.sqlBuilderOptions).forEach(this::addTableWhereDatum);
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> DefaultSqlEngine sqlGroup(SqlGroup<FG> sqlGroup) {
        SqlEngine.executeGroup(sqlGroup, this.sqlBuilderOptions).forEach(this::addTableGroupDatum);
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> DefaultSqlEngine sqlSort(SqlSort<FS> sqlSort) {
        SqlEngine.executeSort(sqlSort, this.sqlBuilderOptions).forEach(this::addTableSortDatum);
        return this;
    }
}
