package com.dt.core.norm;

import com.dt.core.bean.*;
import com.dt.core.data.*;

import java.util.*;

/**
 * 数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public interface Data<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

    /**
     * 获取连接表数据
     *
     * @param alias     别名
     * @param joinClass 连接表模组类
     * @return 连接表数据
     */
    <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinTableData<J, JL, JO, JC, JS, JG> getJoinTableData(String alias, Class<J> joinClass);

    /**
     * 设置连接表数据
     *
     * @param alias     别名
     * @param joinClass 连接表模组类
     */
    default <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> void setJoinTableData(String alias, Class<J> joinClass) {
        JoinTableData<J, JL, JO, JC, JS, JG> joinTableData = new JoinTableData<>(joinClass);
        joinTableData.setTableAlias(alias);
        this.addJoinTableData(joinTableData);
    }

    /**
     * 获取主表数据
     *
     * @return 主表数据
     */
    MainTableData<M, ML, MO, MC, MS, MG> getMainTableData();

    /**
     * 设置主表数据
     *
     * @param mainMainTableData 主表数据
     */
    void setMainTableData(MainTableData<M, ML, MO, MC, MS, MG> mainMainTableData);

    /**
     * 获取连接表数据集合
     *
     * @return 连接表数据集合
     */
    Map<String, JoinTableData> getJoinTableDataAliasMap();

    /**
     * 添加连接表数据集合
     *
     * @param joinTableData
     */
    void addJoinTableData(JoinTableData joinTableData);

    /**
     * 获取列数据集合
     *
     * @return 列数据集合
     */
    Set<AbstractTableData> getColumnDataSet();

    /**
     * 添加列数据集合
     *
     * @param columnData 列数据集合
     */
    void addColumnData(AbstractTableData columnData);

    /**
     * 获取虚拟属性数据集合
     *
     * @return 虚拟属性数据集合
     */
    Set<VirtualFieldData> getVirtualFieldDataSet();

    /**
     * 添加虚拟属性数据集合
     *
     * @param virtualFieldData 虚拟属性数据集合
     */
    void addVirtualFieldData(VirtualFieldData virtualFieldData);

    /**
     * 获取函数列数据集合
     *
     * @return 函数列数据集合
     */
    List<FunctionColumnData> getFunctionColumnDataList();

    /**
     * 添加函数列数据集合
     *
     * @param functionColumnData 函数列数据集合
     */
    void addFunctionColumnData(FunctionColumnData functionColumnData);

    /**
     * 获取连接条件数据集合
     *
     * @return 连接条件数据集合
     */
    List<List<LinkWhereData>> getLinkWhereDataListList();

    /**
     * 添加连接条件数据集合
     *
     * @param linkWhereDataList 连接条件数据集合
     */
    void addLinkWhereDataList(List<LinkWhereData> linkWhereDataList);

    /**
     * 获取分组数据集合
     *
     * @return 分组数据集合
     */
    List<GroupData> getGroupDataList();

    /**
     * 添加分组数据集合
     *
     * @param groupData 分组数据集合
     */
    void addGroupData(GroupData groupData);

    /**
     * 获取排序数据集合
     *
     * @return 排序数据集合
     */
    List<List<SortData>> getSortDataList();

    /**
     * 添加排序数据集合
     *
     * @param sortDataList 排序数据集合
     */
    void addSortDataList(List<SortData> sortDataList);

    Integer getLimitStart();

    void setLimitStart(Integer limitStart);

    Integer getLimitEnd();

    void setLimitEnd(Integer limitEnd);

}
