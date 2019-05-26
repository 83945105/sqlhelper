package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.List;

/**
 * sql数据构建器
 *
 * @author 白超
 * @date 2019/5/20
 */
public interface SqlDataBuilder<T> {

    /**
     * 设置数据库类型
     *
     * @param dataBaseType 数据库类型
     */
    T setDataBaseType(DataBaseType dataBaseType);

    /**
     * 添加列数据
     *
     * @param tableColumnData 表列数据
     */
    T addTableColumnData(TableColumnData tableColumnData);

    /**
     * 添加虚拟属性数据集合
     *
     * @param virtualFieldData 虚拟属性数据集合
     */
    T addVirtualFieldDatum(VirtualFieldDatum virtualFieldData);

    /**
     * 添加函数列数据
     *
     * @param functionColumnData 函数列数据
     */
    T addFunctionColumnData(FunctionColumnData functionColumnData);

    /**
     * 添加连接器数据集合
     *
     * @param whereDataLinkerList 连接器数据集合
     */
    T addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList);

    /**
     * 添加表分组数据
     *
     * @param tableGroupData 分组数据
     */
    T addTableGroupData(TableGroupData tableGroupData);

    /**
     * 添加排序数据
     *
     * @param tableSortData 排序数据
     */
    T addTableSortData(TableSortData tableSortData);

    /**
     * 设置分页数据
     *
     * @param limitData 分页数据
     */
    T setLimitData(LimitHandler limitData);

    /**
     * 构建分页
     *
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    T buildLimitData(Integer currentPage, Integer pageSize);

    /**
     * 构建分页
     *
     * @param total       总数
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    T buildLimitData(Integer total, Integer currentPage, Integer pageSize);

    /**
     * 设置分页开始号
     *
     * @param limitStart 分页开始号
     */
    T setLimitStart(Integer limitStart);

    /**
     * 设置分页结束号
     *
     * @param limitEnd 分页结束号
     */
    T setLimitEnd(Integer limitEnd);

    /**
     * 添加子查询数据
     *
     * @param alias      子查询别名
     * @param sqlBuilder 子查询
     */
    T addSubQueryData(String alias, SqlBuilder sqlBuilder);

    /**
     * 添加连接表数据
     *
     * @param joinTableData 连接表数据
     */
    <J extends TableModel> T addJoinTableData(JoinTableData<J> joinTableData);

    /**
     * 添加子查询连接表数据
     *
     * @param joinTableData 连接表数据
     */
    <J extends TableModel> T addSubQueryJoinTableData(JoinTableData<J> joinTableData);

}
