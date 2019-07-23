package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sql引擎
 *
 * @author 白超
 * @date 2019/7/17
 */
public interface SqlEngine<R> {

    <F extends TableHelper<F, FJ, FC, FW, FG, FH, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FH extends HavingHelper<FH>,
            FS extends SortHelper<FS>> R sql(Sql<F, FJ, FC, FW, FG, FH, FS> sql);

    <FJ extends JoinHelper<FJ>> R sqlJoin(SqlJoin<FJ> sqlJoin);

    <FC extends ColumnHelper<FC>> R sqlColumn(SqlColumn<FC> sqlColumn);

    <FW extends WhereHelper<FW>> R sqlWhere(SqlWhere<FW> sqlWhere);

    <FG extends GroupHelper<FG>> R sqlGroup(SqlGroup<FG> sqlGroup);

    <FS extends SortHelper<FS>> R sqlSort(SqlSort<FS> sqlSort);

    static <FJ extends JoinHelper<FJ>> List<JoinTableDatum> executeJoin(SqlJoin<FJ> sqlJoin, SqlBuilderOptions sqlBuilderOptions) {
        return sqlJoin.getSqlJoinBeans().stream().map(sqlJoinBean -> sqlJoinBean.execute(sqlBuilderOptions)).collect(Collectors.toList());
    }

    static <FC extends ColumnHelper<FC>> List<TableColumnDatum> executeColumn(SqlColumn<FC> sqlColumn, SqlBuilderOptions sqlBuilderOptions) {
        List<TableColumnDatum> tableColumnData = new ArrayList<>();
        sqlColumn.getSqlColumnBeans().forEach(sqlColumnBean -> tableColumnData.addAll(sqlColumnBean.execute(sqlBuilderOptions)));
        return tableColumnData;
    }

    static <FW extends WhereHelper<FW>> List<TableWhereDatum> executeWhere(SqlWhere<FW> sqlWhere, SqlBuilderOptions sqlBuilderOptions) {
        List<TableWhereDatum> tableWhereData = new ArrayList<>();
        sqlWhere.getSqlWhereBeans().forEach(sqlWhereBean -> tableWhereData.addAll(sqlWhereBean.execute(sqlBuilderOptions)));
        return tableWhereData;
    }

    static <FG extends GroupHelper<FG>> List<TableGroupDatum> executeGroup(SqlGroup<FG> sqlGroup, SqlBuilderOptions sqlBuilderOptions) {
        List<TableGroupDatum> tableGroupData = new ArrayList<>();
        sqlGroup.getSqlGroupBeans().forEach(sqlGroupBean -> tableGroupData.addAll(sqlGroupBean.execute(sqlBuilderOptions)));
        return tableGroupData;
    }

    static <FS extends SortHelper<FS>> List<TableSortDatum> executeSort(SqlSort<FS> sqlSort, SqlBuilderOptions sqlBuilderOptions) {
        List<TableSortDatum> tableSortData = new ArrayList<>();
        sqlSort.getSqlSortBeans().forEach(sqlSortBean -> tableSortData.addAll(sqlSortBean.execute(sqlBuilderOptions)));
        return tableSortData;
    }

}
