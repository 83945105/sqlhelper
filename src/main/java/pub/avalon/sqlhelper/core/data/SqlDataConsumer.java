package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;

import java.util.LinkedHashMap;
import java.util.List;

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
     * 获取列数据
     *
     * @return {@link TableColumnDatum}
     */
    List<TableColumnDatum> getTableColumnData();

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
    List<TableWhereDatum> getTableWhereData();

    /**
     * 获取分组数据
     *
     * @return {@link TableGroupDatum}
     */
    List<TableGroupDatum> getTableGroupData();

    /**
     * 获取排序数据
     *
     * @return {@link TableSortDatum}
     */
    List<TableSortDatum> getTableSortData();

    /**
     * 获取分页数据
     *
     * @return {@link LimitDatum}
     */
    LimitDatum getLimitDatum();

}
