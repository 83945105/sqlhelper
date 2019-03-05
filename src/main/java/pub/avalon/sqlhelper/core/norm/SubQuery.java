package pub.avalon.sqlhelper.core.norm;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.engine.QueryEngine;
import pub.avalon.sqlhelper.core.exception.SqlException;

import java.util.Map;

/**
 * 子查询
 *
 * @author 白超
 * @date 2018/11/18
 */
@FunctionalInterface
public interface SubQuery<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>,
        T extends Model<T, TC, TO, TW, TS, TG>,
        TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
        TO extends OnModel<T, TC, TO, TW, TS, TG>,
        TW extends WhereModel<T, TC, TO, TW, TS, TG>,
        TS extends SortModel<T, TC, TO, TW, TS, TG>,
        TG extends GroupModel<T, TC, TO, TW, TS, TG>> {

    /**
     * 子查询处理
     *
     * @param mainTable
     * @param query
     * @return
     */
    SqlBuilder apply(MW mainTable, QueryEngine<T, TC, TO, TW, TS, TG> query);

    /**
     * 执行SubQuery
     *
     * @param sqlData
     * @param tableName
     * @param modelClass
     * @param alias
     * @param subQuery
     * @param <M>
     * @param <MC>
     * @param <MO>
     * @param <MW>
     * @param <MS>
     * @param <MG>
     * @param <T>
     * @param <TC>
     * @param <TO>
     * @param <TW>
     * @param <TS>
     * @param <TG>
     * @return
     */
    static <M extends Model<M, MC, MO, MW, MS, MG>,
            MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
            MO extends OnModel<M, MC, MO, MW, MS, MG>,
            MW extends WhereModel<M, MC, MO, MW, MS, MG>,
            MS extends SortModel<M, MC, MO, MW, MS, MG>,
            MG extends GroupModel<M, MC, MO, MW, MS, MG>,
            T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> SqlBuilder execute(SqlData<M> sqlData, String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        QueryEngine<T, TC, TO, TW, TS, TG> queryEngine;
        switch (sqlData.getDataBaseType()) {
            case MYSQL:
                queryEngine = new QueryEngine<>(tableName, modelClass, alias, DataBaseType.MYSQL);
                break;
            case SQLSERVER:
                queryEngine = new QueryEngine<>(tableName, modelClass, alias, DataBaseType.SQLSERVER);
                break;
            default:
                throw new SqlException("SubQuery do not support this database type temporarily.");
        }
        Map<String, JoinTableData<? extends Model>> joinTableDataAliasMap = sqlData.getJoinTableDataMap();
        if (joinTableDataAliasMap != null && joinTableDataAliasMap.size() > 0) {
            for (Map.Entry<String, JoinTableData<? extends Model>> entry : joinTableDataAliasMap.entrySet()) {
                // 将父查询的关联表数据添加至子查询中
                queryEngine.getSqlData().addSubQueryJoinTableData(entry.getValue());
            }
        }
        return subQuery.apply(sqlData.getMainTableData().getTableModel().getWhereModel(), queryEngine);
    }

}
