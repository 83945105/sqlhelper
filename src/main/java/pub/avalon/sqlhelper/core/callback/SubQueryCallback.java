package pub.avalon.sqlhelper.core.callback;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.engine.QueryEngine;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.Map;

/**
 * 子查询
 *
 * @author 白超
 * @date 2018/11/18
 */
@FunctionalInterface
public interface SubQueryCallback<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> {

    /**
     * 子查询处理
     *
     * @param query
     * @return
     */
    SqlBuilder apply(QueryEngine<T, TO, TC, TW, TG, TS> query);

    /**
     * 执行SubQuery
     *
     * @param sqlData
     * @param tableName
     * @param tableModelClass
     * @param alias
     * @param subQuery
     * @param <T>
     * @return
     */
    static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> SqlBuilder execute(SqlData<?> sqlData, String tableName, Class<T> tableModelClass, String alias, SubQueryCallback<T, TO, TC, TW, TG, TS> subQuery) {
        QueryEngine<T, TO, TC, TW, TG, TS> queryEngine;
        switch (sqlData.getDataBaseType()) {
            case MYSQL:
                queryEngine = new QueryEngine<>(tableName, tableModelClass, alias).setDataBaseType(DataBaseType.MYSQL);
                break;
            case SQLSERVER:
                queryEngine = new QueryEngine<>(tableName, tableModelClass, alias).setDataBaseType(DataBaseType.SQLSERVER);
                break;
            default:
                throw new SqlException("SubQuery do not support this database type temporarily.");
        }
        Map<String, JoinTableData<? extends TableModel>> joinTableDataAliasMap = sqlData.getJoinTableDataMap();
        if (joinTableDataAliasMap != null && joinTableDataAliasMap.size() > 0) {
            for (Map.Entry<String, JoinTableData<? extends TableModel>> entry : joinTableDataAliasMap.entrySet()) {
                // 将父查询的关联表数据添加至子查询中
                queryEngine.getSqlData().addSubQueryJoinTableData(entry.getValue());
            }
        }
        return subQuery.apply(queryEngine);
    }

}
