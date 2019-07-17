package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.helper.*;

/**
 * Sql引擎
 *
 * @author 白超
 * @date 2019/7/17
 */
public interface SqlEngine<R> {

    <F extends TableHelper<F, FO, FC, FW, FG, FS>,
            FO extends OnHelper<FO>,
            FC extends ColumnHelper<FC>,
            FW extends WhereHelper<FW>,
            FG extends GroupHelper<FG>,
            FS extends SortHelper<FS>> R sql(Sql<F, FO, FC, FW, FG, FS> sql);


    <FC extends ColumnHelper<FC>> R sqlColumn(SqlColumn<FC> sqlColumn);

    <FO extends OnHelper<FO>> R sqlJoin(SqlJoin<FO> sqlJoin);
}
