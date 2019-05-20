package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.data.FinalSqlData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.modelbuilder.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderProxy;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilderTemplate;

/**
 * 引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SqlEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> implements SqlBuilderTemplate<SqlBuilder> {

    protected Class<T> tableModelClass;

    private SqlData<T> sqlData;

    private SqlBuilderOptions sqlBuilderOptions;

    private SqlBuilderProxy sqlBuilderProxy;

    public SqlEngine(Class<T> tableModelClass) {
        this.tableModelClass = tableModelClass;
        this.sqlData = new FinalSqlData<>(new MainTableData<>(tableModelClass));
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass) {
        this.tableModelClass = tableModelClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

    public SqlEngine(String tableName, Class<T> tableModelClass, String alias) {
        this.tableModelClass = tableModelClass;
        MainTableData<T> mainTableData = new MainTableData<>(tableModelClass);
        mainTableData.setTableName(tableName);
        mainTableData.setTableAlias(alias);
        this.sqlData = new FinalSqlData<>(mainTableData);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

}
