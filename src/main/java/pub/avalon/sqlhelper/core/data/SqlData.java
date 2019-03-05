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
     * 获取列数据集合
     *
     * @return 列数据集合
     */
    Set<ColumnData> getColumnDataSet();

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
    List<List<SortData>> getSortDataListList();

    /**
     * 获取分页数据
     *
     * @return 分页对象
     */
    LimitHandler getLimitData();

}
