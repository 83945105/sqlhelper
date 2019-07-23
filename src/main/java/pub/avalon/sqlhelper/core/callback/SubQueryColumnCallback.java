package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.engine.Engine;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;

/**
 * 子查询
 *
 * @author 白超
 * @date 2018/11/18
 */
@FunctionalInterface
public interface SubQueryColumnCallback<TC extends ColumnHelper<TC>> {

    Engine apply(TC parentTable);

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
/*    static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
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
    }*/

}
