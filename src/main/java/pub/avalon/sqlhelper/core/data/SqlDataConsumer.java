package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * sql数据消费者
 *
 * @author 白超
 * @date 2019/5/27
 */
public interface SqlDataConsumer<T extends TableModel> {

    /**
     * 获取子查询数据集合
     *
     * @return 子查询集合
     */
    Map<String, SqlBuilder> getSubQueryDataMap();

    /**
     * 获取函数列数据集合
     *
     * @return 函数列数据集合
     */
    Set<TableFunctionColumnDatum> getTableFunctionColumnData();

    /**
     * 获取虚拟属性数据集合
     *
     * @return 虚拟属性数据集合
     */
    Set<VirtualFieldDatum> getVirtualFieldData();

    Set<TableColumnDatum> getTableColumnData();

    /**
     * 获取数据库类型
     *
     * @return {@link pub.avalon.beans.DataBaseType}
     */
    DataBaseType getDataBaseType();

    /**
     * 获取主表数据
     *
     * @return 主表数据
     */
    MainTableData<T> getMainTableData();

    default Set<ColumnDatum> getMainTableColumnData() {
        return this.getMainTableData().buildTableColumnData();
    }

    /**
     * 获取连接表数据
     *
     * @param alias     别名
     * @param joinClass 连接表模组类
     * @return 连接表数据
     */
    <J extends TableModel> JoinTableData<J> getJoinTableData(String alias, Class<J> joinClass);

    /**
     * 获取连接表数据集合
     *
     * @return 连接表数据集合
     */
    LinkedHashMap<String, JoinTableData<? extends TableModel>> getJoinTableDataMap();

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
    Set<TableGroupDatum> getTableGroupData();

    /**
     * 获取排序数据集合
     *
     * @return 排序数据集合
     */
    Set<TableSortDatum> getTableSortData();

    /**
     * 获取分页数据
     *
     * @return 分页对象
     */
    LimitHandler getLimitData();

}
