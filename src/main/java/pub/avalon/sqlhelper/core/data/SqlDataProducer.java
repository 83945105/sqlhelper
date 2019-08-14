package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;

/**
 * sql数据生产者
 *
 * @author baichao
 * @date 2019/5/20
 */
public interface SqlDataProducer {

    /**
     * 设置数据库类型
     *
     * @param dataBaseType 数据库类型 {@link DataBaseType}
     */
    void setDataBaseType(DataBaseType dataBaseType);

    /**
     * 添加连接表数据
     *
     * @param joinTableDatum {@link JoinTableDatum}
     */
    void addJoinTableDatum(JoinTableDatum joinTableDatum);

    /**
     * 添加列数据
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addTableColumnDatum(TableColumnDatum tableColumnDatum);

    /**
     * 添加条件数据
     *
     * @param tableWhereDatum {@link TableWhereDatum}
     */
    void addTableWhereDatum(TableWhereDatum tableWhereDatum);

    /**
     * 添加分组数据
     *
     * @param tableGroupDatum {@link TableGroupDatum}
     */
    void addTableGroupDatum(TableGroupDatum tableGroupDatum);

    /**
     * 添加排序数据
     *
     * @param tableSortDatum {@link TableSortDatum}
     */
    void addTableSortDatum(TableSortDatum tableSortDatum);

    /**
     * 添加分页数据
     *
     * @param limitDatum {@link LimitDatum}
     */
    void setLimitDatum(LimitDatum limitDatum);

}
