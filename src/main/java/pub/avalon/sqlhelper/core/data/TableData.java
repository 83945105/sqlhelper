package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 表数据
 *
 * @author 白超
 * @date 2019/5/2
 */
public interface TableData<T extends Model> {

    /**
     * 获取表模型
     *
     * @return 表模型
     */
    T getTableModel();

    /**
     * 获取表类型
     *
     * @return 表模型类
     */
    Class<T> getTableClass();

    /**
     * 获取表名称
     *
     * @return 表名称
     */
    String getTableName();

    /**
     * 设置表名称
     *
     * @param tableName 表名称
     */
    void setTableName(String tableName);

    /**
     * 获取表别名
     *
     * @return 表别名
     */
    String getTableAlias();

    /**
     * 设置表别名
     *
     * @param tableAlias 表别名
     */
    void setTableAlias(String tableAlias);

    /**
     * 获取列数据
     *
     * @return 列数据
     */
    Set<ColumnDatum> getColumnData();

    /**
     * 设置列数据
     *
     * @param columnData 列数据
     */
    void setColumnData(Set<ColumnDatum> columnData);

    /**
     * 构建表的列数据
     *
     * @return LinkedHashSet
     */
    @SuppressWarnings("unchecked")
    default Set<ColumnDatum> buildTableColumnData() {
        Set<ColumnDatum> columnData = new LinkedHashSet<>();
        Map<String, String> columnAliasMap = this.getTableModel().getColumnAliasMap();
        if (columnAliasMap == null) {
            return columnData;
        }
        String tableName = this.getTableName();
        String tableAlias = this.getTableAlias();
        for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
            columnData.add(new ColumnDatum(tableName, tableAlias, entry.getKey(), entry.getValue()));
        }
        return columnData;
    }

}
