package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface SqlEngine<R> {

    /**
     * add join sql data
     *
     * @param sqlJoin extends {@link SqlJoin} object
     * @param <FO>    extends {@link OnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FO extends OnHelper<FO>> R sqlJoin(SqlJoin<FO> sqlJoin);

    /**
     * add on sql data
     *
     * @param sqlOn extends {@link SqlOn} object
     * @param <FO>  extends {@link OnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FO extends OnHelper<FO>> R sqlOn(SqlOn<FO> sqlOn);

    /**
     * add column sql data
     *
     * @param sqlColumn extends {@link SqlColumn} object
     * @param <FC>      extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R sqlColumn(SqlColumn<FC> sqlColumn);

    /**
     * add where sql data
     *
     * @param sqlWhere extends {@link SqlWhere} object
     * @param <FW>     extends {@link WhereHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FW extends WhereHelper<FW>> R sqlWhere(SqlWhere<FW> sqlWhere);

    /**
     * add group sql data
     *
     * @param sqlGroup extends {@link SqlGroup} object
     * @param <FG>     extends {@link GroupHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FG extends GroupHelper<FG>> R sqlGroup(SqlGroup<FG> sqlGroup);

    /**
     * add sort sql data
     *
     * @param sqlSort extends {@link SqlSort} object
     * @param <FS>    extends {@link SortHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FS extends SortHelper<FS>> R sqlSort(SqlSort<FS> sqlSort);
}