package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * sql数据消费者
 *
 * @author 白超
 * @date 2019/5/27
 */
public interface SqlDataConsumer {

    /**
     * 获取数据库类型
     *
     * @return {@link DataBaseType}
     */
    DataBaseType getDataBaseType();

    /**
     * 获取主表数据
     *
     * @return {@link MainTableDatum}
     */
    MainTableDatum getMainTableDatum();

    /**
     * 获取连接表数据
     *
     * @param tableAlias 连接表别名
     * @return {@link JoinTableDatum}
     */
    JoinTableDatum getJoinTableDatum(String tableAlias);

    /**
     * 获取列数据
     *
     * @return {@link TableColumnDatum}
     */
    Set<TableColumnDatum> getTableColumnData();

    /**
     * 获取虚拟属性
     *
     * @return {@link VirtualFieldDatum}
     */
    Set<VirtualFieldDatum> getVirtualFieldData();

    /**
     * 获取函数列属性
     *
     * @return {@link TableFunctionColumnDatum}
     */
    Set<TableFunctionColumnDatum> getTableFunctionColumnData();

    /**
     * 获取连接表数据
     *
     * @return key - 表别名 value - {@link JoinTableDatum}
     */
    LinkedHashMap<String, JoinTableDatum> getAliasJoinTableData();

    /**
     * 获取条件数据
     *
     * @return {@link WhereDataLinker}
     */
    Set<TableWhereDatum> getTableWhereData();

    /**
     * 获取分组数据
     *
     * @return {@link TableGroupDatum}
     */
    Set<TableGroupDatum> getTableGroupData();

    /**
     * 获取排序数据
     *
     * @return {@link TableSortDatum}
     */
    Set<TableSortDatum> getTableSortData();

    /**
     * 获取分页数据
     *
     * @return {@link LimitHandler}
     */
    LimitHandler getLimitData();

    /**
     * 获取子查询数据集合
     *
     * @return 子查询集合
     */
    Map<String, SqlBuilder> getSubQueryDataMap();

}
