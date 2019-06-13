package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.helper.TableHelper;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

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
     * @param joinTableData {@link JoinTableData}
     * @param <J>           {@link TableHelper}
     */
    <J extends TableHelper> void addJoinTableData(JoinTableData<J> joinTableData);

    /**
     * 添加虚拟属性数据
     *
     * @param virtualFieldDatum {@link VirtualFieldDatum}
     */
    void addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum);

    /**
     * 添加函数列数据
     *
     * @param tableFunctionColumnDatum {@link TableFunctionColumnDatum}
     */
    void addTableFunctionColumnDatum(TableFunctionColumnDatum tableFunctionColumnDatum);

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
     * @param limitData {@link LimitHandler}
     */
    void setLimitData(LimitHandler limitData);

    /**
     * 构建分页
     *
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    void buildLimitData(Integer currentPage, Integer pageSize);

    /**
     * 构建分页
     *
     * @param total       总数
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    void buildLimitData(Integer total, Integer currentPage, Integer pageSize);

    /**
     * 设置分页开始号
     *
     * @param limitStart 分页开始号
     */
    void setLimitStart(Integer limitStart);

    /**
     * 设置分页结束号
     *
     * @param limitEnd 分页结束号
     */
    void setLimitEnd(Integer limitEnd);

    /**
     * 添加子查询数据
     *
     * @param alias      子查询别名
     * @param sqlBuilder 子查询
     * @return void
     */
    void addSubQueryData(String alias, SqlBuilder sqlBuilder);

    /**
     * 添加子查询连接表数据
     *
     * @param joinTableData 连接表数据
     * @param <J>
     * @return void
     */
    <J extends TableHelper> void addSubQueryJoinTableData(JoinTableData<J> joinTableData);

}
