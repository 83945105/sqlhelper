package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.*;

/**
 * 表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
//TODO 优化 去除无用属性,简化逻辑
public abstract class AbstractTableData<T extends Model> {

    private T tableModel;

    private Class<T> tableClass;

    protected String tableName;

    protected String tableAlias;

    private String primaryKeyName;

    private String primaryKeyAlias;

    private Map<String, String> columnAliasMap;

    public AbstractTableData(Class<T> tableClass) {
        this.tableClass = tableClass;
        try {
            this.tableModel = tableClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new TableDataException(e);
        }
        this.tableName = this.tableModel.getTableName();
        this.tableAlias = this.tableModel.getTableAlias();
        this.primaryKeyName = this.tableModel.getPrimaryKeyName();
        this.primaryKeyAlias = this.tableModel.getPrimaryKeyAlias();
    }

    public T getTableModel() {
        return this.tableModel;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        if (tableName == null || "".equals(tableName.trim())) {
            return;
        }
        this.tableName = tableName;
    }

    public String getTableAlias() {
        return this.tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        if (tableAlias == null || "".equals(tableAlias.trim())) {
            return;
        }
        this.tableAlias = tableAlias;
    }

    public String getPrimaryKeyName() {
        return this.primaryKeyName;
    }

    public String getPrimaryKeyAlias() {
        return this.primaryKeyAlias;
    }

    public Map<String, String> getColumnAliasMap() {
        return this.columnAliasMap;
    }

    public void addColumnAliasMap(Map<String, String> selectColumns) {
        if (this.columnAliasMap == null) {
            this.columnAliasMap = new LinkedHashMap<>();
        }
        this.columnAliasMap.putAll(selectColumns);
    }

    public Class<T> getTableClass() {
        return this.tableClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractTableData tableData = (AbstractTableData) o;
        return Objects.equals(tableAlias, tableData.tableAlias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableAlias);
    }
}
