package pub.avalon.sqlhelper.core.callback;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.engine.TableEngine;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.Map;

/**
 * 子查询
 *
 * @author 白超
 * @date 2018/11/18
 */
@FunctionalInterface
public interface SubQueryCallback<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

    /**
     * 子查询处理
     *
     * @param query
     * @return
     */
    SqlBuilder apply(TableEngine<T, TO, TC, TW, TG, TS> query);

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
    static <T extends TableHelper<T, TO, TC, TW, TG, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TS extends SortHelper<TS>> SqlBuilder execute(SqlData<?> sqlData, String tableName, Class<T> tableModelClass, String alias, SubQueryCallback<T, TO, TC, TW, TG, TS> subQuery) {
        TableEngine<T, TO, TC, TW, TG, TS> tableEngine;
        switch (sqlData.getDataBaseType()) {
            case MYSQL:
                tableEngine = new TableEngine<>(tableName, tableModelClass, alias);
                tableEngine.setDataBaseType(DataBaseType.MYSQL);
                break;
            case SQLSERVER:
                tableEngine = new TableEngine<>(tableName, tableModelClass, alias);
                tableEngine.setDataBaseType(DataBaseType.SQLSERVER);
                break;
            default:
                throw new SqlException("SubQuery do not support this database type temporarily.");
        }
        Map<String, JoinTableData<? extends TableHelper>> joinTableDataAliasMap = sqlData.getJoinTableDataMap();
        if (joinTableDataAliasMap != null && joinTableDataAliasMap.size() > 0) {
            for (Map.Entry<String, JoinTableData<? extends TableHelper>> entry : joinTableDataAliasMap.entrySet()) {
                // 将父查询的关联表数据添加至子查询中
                tableEngine.addSubQueryJoinTableData(entry.getValue());
            }
        }
        return subQuery.apply(tableEngine);
    }

}
