package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitSql;

/**
 * sql数据生产者
 *
 * @author 白超
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
     * 添加列数据
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addTableColumnDatum(TableColumnDatum tableColumnDatum);

    /**
     * 添加连接表数据
     *
     * @param joinTableDatum {@link JoinTableDatum}
     */
    void addJoinTableDatum(JoinTableDatum joinTableDatum);

    /**
     * 添加虚拟属性数据
     *
     * @param virtualFieldDatum {@link VirtualFieldDatum}
     */
    void addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum);

    /**
     * 添加聚合函数列数据
     *
     * @param tableGroupColumnDatum {@link TableGroupColumnDatum}
     */
    void addTableGroupColumnDatum(TableGroupColumnDatum tableGroupColumnDatum);

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
     * 设置分页数据
     *
     * @param limitData {@link LimitSql}
     */
    void setLimitData(LimitSql limitData);

    /**
     * 构建分页
     *
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    void buildLimitData(Long currentPage, Long pageSize);

    /**
     * 构建分页
     *
     * @param total       总数
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    void buildLimitData(Long total, Long currentPage, Long pageSize);

}
