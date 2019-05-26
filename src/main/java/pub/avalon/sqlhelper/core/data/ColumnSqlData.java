package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 白超
 * @date 2019/5/25
 */
public interface ColumnSqlData {

    Set<ColumnDatum> getMainTableColumnData();

    /**
     * 获取子查询数据集合
     *
     * @return 子查询集合
     */
    Map<String, SqlBuilder> getSubQueryDataMap();

    List<FunctionColumnData> getFunctionColumnDataList();

    /**
     * 获取虚拟属性数据集合
     *
     * @return 虚拟属性数据集合
     */
    Set<VirtualFieldDatum> getVirtualFieldDataSet();

    Set<TableColumnData> getTableColumnDataSet();

}
