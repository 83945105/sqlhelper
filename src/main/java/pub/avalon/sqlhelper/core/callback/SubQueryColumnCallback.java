package pub.avalon.sqlhelper.core.callback;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.engine.SqlHelperEngine;
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
public interface SubQueryColumnCallback<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> {

    /**
     * 子查询处理
     *
     * @param query
     * @return
     */
    SqlBuilder apply(SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> query);

    /**
     * 执行SubQuery
     *
     * @param sqlData
     * @param tableName
     * @param tableHelperClass
     * @param tableAlias
     * @param subQuery
     * @param <T>
     * @return
     */
    static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlBuilder execute(SqlData sqlData, String tableName, Class<T> tableHelperClass, String tableAlias, SubQueryColumnCallback<T, TJ, TC, TW, TG, TH, TS> subQuery) {
        SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> tableEngine;
        switch (sqlData.getDataBaseType()) {
            case MYSQL:
                tableEngine = new SqlHelperEngine<>(sqlData.getDataBaseType(), tableName, tableHelperClass, tableAlias);
                tableEngine.setDataBaseType(DataBaseType.MYSQL);
                break;
            case SQLSERVER:
                tableEngine = new SqlHelperEngine<>(sqlData.getDataBaseType(), tableName, tableHelperClass, tableAlias);
                tableEngine.setDataBaseType(DataBaseType.SQLSERVER);
                break;
            default:
                throw new SqlException("SubQuery do not support this database type temporarily.");
        }
        Map<String, JoinTableDatum> joinTableDataAliasMap = sqlData.getAliasJoinTableData();
        if (joinTableDataAliasMap != null && joinTableDataAliasMap.size() > 0) {
            for (Map.Entry<String, JoinTableDatum> entry : joinTableDataAliasMap.entrySet()) {
                // 将父查询的关联表数据添加至子查询中
                tableEngine.addSubQueryJoinTableDatum(entry.getValue());
            }
        }
        return subQuery.apply(tableEngine);
    }

}
