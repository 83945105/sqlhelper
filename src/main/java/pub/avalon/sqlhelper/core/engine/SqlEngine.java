package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.ArrayList;
import java.util.List;

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

    static <FC extends ColumnHelper<FC>> List<TableColumnDatum> execute(SqlColumn<FC> sqlColumn, SqlBuilderOptions sqlBuilderOptions) {
        List<TableColumnDatum> tableColumnData = new ArrayList<>();
        sqlColumn.getSqlColumnBeans().forEach(sqlColumnBean -> tableColumnData.addAll(sqlColumnBean.execute(sqlBuilderOptions)));
        return tableColumnData;
    }
}
