package pub.avalon.sqlhelper.core.engine.builder;

import pub.avalon.sqlhelper.core.helper.*;

/**
 * Sql引擎
 *
 * @author baichao
 * @date 2019/7/17
 */
public interface SqlEngine<R> {

    <FJ extends JoinHelper<FJ>> R sqlJoin(SqlJoin<FJ> sqlJoin);

    <FC extends ColumnHelper<FC>> R sqlColumn(SqlColumn<FC> sqlColumn);

    <FW extends WhereHelper<FW>> R sqlWhere(SqlWhere<FW> sqlWhere);

    <FG extends GroupHelper<FG>> R sqlGroup(SqlGroup<FG> sqlGroup);

    <FS extends SortHelper<FS>> R sqlSort(SqlSort<FS> sqlSort);

}
