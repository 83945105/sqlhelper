package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.SqlColumnBean;
import pub.avalon.sqlhelper.core.beans.SqlColumnBeanJoin;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;
import java.util.Set;

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
    public <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> DefaultSqlEngine sql(Sql<F, FJ, FC, FW, FG, FH, FS> sql) {
        return null;
    }

    @Override
    public <FJ extends JoinHelper<FJ>> DefaultSqlEngine sqlJoin(SqlJoin<FJ> sqlJoin) {
        return null;
    }

    @Override
    public <FC extends ColumnHelper<FC>> DefaultSqlEngine sqlColumn(SqlColumn<FC> sqlColumn) {
        FC fc = sqlColumn.getColumnHelper();
        String tableAlias = sqlColumn.getTableAlias();
        List<SqlColumnBean<FC>> sqlColumnBeans = sqlColumn.getSqlColumnBeans();
        // 设置配置开始
        fc.setSqlBuilderOptions(this.sqlBuilderOptions);
        // 设置配置结束

        for (SqlColumnBean<FC> sqlColumnBean : sqlColumnBeans) {
            if (sqlColumnBean instanceof SqlColumnBeanJoin) {

                continue;
            }

        }

/*        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(tableHelperClass);
        }
        this.addTableColumnDatum(new TableColumnDatum(tableAlias, columnData));*/


        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> DefaultSqlEngine sqlWhere(SqlWhere<FW> sqlWhere) {
        return null;
    }

    @Override
    public <FG extends GroupHelper<FG>> DefaultSqlEngine sqlGroup(SqlGroup<FG> sqlGroup) {
        return null;
    }

    @Override
    public <FS extends SortHelper<FS>> DefaultSqlEngine sqlSort(SqlSort<FS> sqlSort) {
        return null;
    }
}
