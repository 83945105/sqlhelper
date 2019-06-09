package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.helper.TableHelper;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.List;

/**
 * sql数据生产者
 *
 * @author 白超
 * @date 2019/5/20
 */
public interface SqlDataProducer<T> {

    /**
     * 设置数据库类型
     *
     * @param dataBaseType 数据库类型
     * @return T
     */
    T setDataBaseType(DataBaseType dataBaseType);

    /**
     * 添加列数据
     *
     * @param tableColumnDatum 表列数据
     * @return T
     */
    T addTableColumnDatum(TableColumnDatum tableColumnDatum);

    /**
     * 添加虚拟属性数据集合
     *
     * @param virtualFieldDatum 虚拟属性数据集合
     * @return T
     */
    T addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum);

    /**
     * 添加函数列数据
     *
     * @param tableFunctionColumnDatum 函数列数据
     * @return T
     */
    T addTableFunctionColumnDatum(TableFunctionColumnDatum tableFunctionColumnDatum);

    /**
     * 添加连接器数据集合
     *
     * @param whereDataLinkerList 连接器数据集合
     * @return T
     */
    T addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList);

    /**
     * 添加表分组数据
     *
     * @param tableGroupDatum 分组数据
     * @return T
     */
    T addTableGroupDatum(TableGroupDatum tableGroupDatum);

    /**
     * 添加排序数据
     *
     * @param tableSortDatum 排序数据
     * @return T
     */
    T addTableSortDatum(TableSortDatum tableSortDatum);

    /**
     * 设置分页数据
     *
     * @param limitData 分页数据
     * @return
     */
    T setLimitData(LimitHandler limitData);

    /**
     * 构建分页
     *
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     * @return T
     */
    T buildLimitData(Integer currentPage, Integer pageSize);

    /**
     * 构建分页
     *
     * @param total       总数
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     * @return T
     */
    T buildLimitData(Integer total, Integer currentPage, Integer pageSize);

    /**
     * 设置分页开始号
     *
     * @param limitStart 分页开始号
     * @return T
     */
    T setLimitStart(Integer limitStart);

    /**
     * 设置分页结束号
     *
     * @param limitEnd 分页结束号
     * @return
     */
    T setLimitEnd(Integer limitEnd);

    /**
     * 添加子查询数据
     *
     * @param alias      子查询别名
     * @param sqlBuilder 子查询
     * @return T
     */
    T addSubQueryData(String alias, SqlBuilder sqlBuilder);

    /**
     * 添加连接表数据
     *
     * @param joinTableData 连接表数据
     * @param <J>
     * @return T
     */
    <J extends TableHelper> T addJoinTableData(JoinTableData<J> joinTableData);

    /**
     * 添加子查询连接表数据
     *
     * @param joinTableData 连接表数据
     * @param <J>
     * @return T
     */
    <J extends TableHelper> T addSubQueryJoinTableData(JoinTableData<J> joinTableData);

}
