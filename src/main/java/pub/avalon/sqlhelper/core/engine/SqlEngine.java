package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.*;

/**
 * Sql引擎
 *
 * @author 白超
 * @date 2019/7/17
 */
public interface SqlEngine<R> {

    <F extends TableHelper<F, FJ, FC, FW, FG, FS>,
            FJ extends JoinHelper<FJ>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FS extends SortHelper<FS>> R sql(Sql<F, FJ, FC, FW, FG, FS> sql);


    <FC extends ColumnHelper<FC>> R sqlColumn(SqlColumn<FC> sqlColumn);

    <FJ extends JoinHelper<FJ>> R sqlJoin(SqlJoin<FJ> sqlJoin);
}
