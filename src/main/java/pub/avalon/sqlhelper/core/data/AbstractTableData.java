package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.norm.Model;

import java.util.*;

/**
 * 表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractTableData<T extends Model> {

    private T tableModel;

    private Class<T> tableClass;

    protected String tableName;

    protected String tableAlias;

    private String primaryKeyName;

    private String primaryKeyAlias;

    private Map<String, String> columnAliasMap;

    private List<LinkWhereData> linkWhereDataList;

    private List<String> groupColumns;

    private List<List<SortData>> sortDataList;

    public AbstractTableData(Class<T> tableClass) {
        this.tableClass = tableClass;
        try {
            this.tableModel = tableClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
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

    public void setPrimaryKeyName(String primaryKeyName) {
        if (primaryKeyName == null || "".equals(primaryKeyName)) {
            return;
        }
        this.primaryKeyName = primaryKeyName;
    }

    public String getPrimaryKeyAlias() {
        return this.primaryKeyAlias;
    }

    public void setPrimaryKeyAlias(String primaryKeyAlias) {
        if (primaryKeyAlias == null || "".equals(primaryKeyAlias)) {
            return;
        }
        this.primaryKeyAlias = primaryKeyAlias;
    }

    public List<LinkWhereData> getLinkWhereDataList() {
        return this.linkWhereDataList;
    }

    public void addLinkWhereDataList(List<LinkWhereData> linkWhereDataList) {
        if (linkWhereDataList == null || linkWhereDataList.size() == 0) {
            return;
        }
        if (this.linkWhereDataList == null) {
            this.linkWhereDataList = new ArrayList<>();
        }
        this.linkWhereDataList.addAll(linkWhereDataList);
    }

    public Map<String, String> getColumnAliasMap() {
        return this.columnAliasMap;
    }

    public void addColumnAlias(String columnName, String alias) {
        if (this.columnAliasMap == null) {
            this.columnAliasMap = new LinkedHashMap<>();
        }
        this.columnAliasMap.put(columnName, alias);
    }

    public void addColumnAliasMap(Map<String, String> selectColumns) {
        if (this.columnAliasMap == null) {
            this.columnAliasMap = new LinkedHashMap<>();
        }
        this.columnAliasMap.putAll(selectColumns);
    }

    public List<String> getGroupColumns() {
        return this.groupColumns;
    }

    public void addGroupColumns(Collection<String> groupColumns) {
        if (groupColumns == null || groupColumns.size() == 0) {
            return;
        }
        if (this.groupColumns == null) {
            this.groupColumns = new ArrayList<>();
        }
        this.groupColumns.addAll(groupColumns);
    }

    public void addGroupColumns(String[] groupColumns) {
        if (groupColumns == null || groupColumns.length == 0) {
            return;
        }
        if (this.groupColumns == null) {
            this.groupColumns = new ArrayList<>();
        }
        Collections.addAll(this.groupColumns, groupColumns);
    }

    public void addSortDataList(List<SortData> sortDataList) {
        if (sortDataList == null || sortDataList.size() == 0) {
            return;
        }
        if (this.sortDataList == null) {
            this.sortDataList = new ArrayList<>();
        }
        this.sortDataList.add(sortDataList);
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
