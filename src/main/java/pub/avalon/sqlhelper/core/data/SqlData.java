package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Sql数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface SqlData<M extends Model> {

    /**
     * 获取数据库类型
     *
     * @return 数据库类型
     */
    DataBaseType getDataBaseType();

    /**
     * 获取主表数据
     *
     * @return 主表数据
     */
    MainTableData<M> getMainTableData();

    /**
     * 获取连接表数据
     *
     * @param alias     别名
     * @param joinClass 连接表模组类
     * @return 连接表数据
     */
    <J extends Model> JoinTableData<J> getJoinTableData(String alias, Class<J> joinClass);

    /**
     * 获取连接表数据集合
     *
     * @return 连接表数据集合
     */
    LinkedHashMap<String, JoinTableData<? extends Model>> getJoinTableDataMap();

    /**
     * 获取表列数据集合
     *
     * @return 列数据集合
     */
    Set<TableColumnData> getTableColumnDataSet();

    /**
     * 获取虚拟属性数据集合
     *
     * @return 虚拟属性数据集合
     */
    Set<VirtualFieldData> getVirtualFieldDataSet();

    /**
     * 获取函数列数据集合
     *
     * @return 函数列数据集合
     */
    List<FunctionColumnData> getFunctionColumnDataList();

    /**
     * 获取子查询数据集合
     *
     * @return 子查询集合
     */
    Map<String, SqlBuilder> getSubQueryDataMap();

    /**
     * 获取where条件连接器数据集合
     *
     * @return 连接条件数据集合
     */
    List<List<WhereDataLinker>> getWhereDataLinkerListList();

    /**
     * 获取分组数据集合
     *
     * @return 分组数据集合
     */
    List<GroupData> getGroupDataList();

    /**
     * 获取排序数据集合
     *
     * @return 排序数据集合
     */
    Set<TableSortData> getTableSortDataSet();

    /**
     * 获取分页数据
     *
     * @return 分页对象
     */
    LimitHandler getLimitData();

    /**
     * 添加列数据
     *
     * @param tableColumnData 表列数据
     */
    void addTableColumnData(TableColumnData tableColumnData);

    /**
     * 添加虚拟属性数据集合
     *
     * @param virtualFieldData 虚拟属性数据集合
     */
    void addVirtualFieldData(VirtualFieldData virtualFieldData);

    /**
     * 添加函数列数据
     *
     * @param functionColumnData 函数列数据
     */
    void addFunctionColumnData(FunctionColumnData functionColumnData);

    /**
     * 添加连接器数据集合
     *
     * @param whereDataLinkerList 连接器数据集合
     */
    void addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList);

    /**
     * 添加分组数据
     *
     * @param groupData 分组数据
     */
    void addGroupData(GroupData groupData);

    /**
     * 添加排序数据
     *
     * @param tableSortData 排序数据
     */
    void addTableSortData(TableSortData tableSortData);

    /**
     * 设置分页数据
     *
     * @param limitData 分页数据
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
     */
    void addSubQueryData(String alias, SqlBuilder sqlBuilder);

    /**
     * 添加连接表数据
     *
     * @param joinTableData 连接表数据
     */
    <J extends Model> void addJoinTableData(JoinTableData<J> joinTableData);

    /**
     * 添加子查询连接表数据
     *
     * @param joinTableData 连接表数据
     */
    <J extends Model> void addSubQueryJoinTableData(JoinTableData<J> joinTableData);

    /**
     * 分裂
     *
     * @param clazz 目标类Model.class
     * @return SqlData
     */
    <T extends Model> SqlData<T> fission(Class<T> clazz);

}
