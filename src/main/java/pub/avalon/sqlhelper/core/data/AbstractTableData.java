package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.Objects;
import java.util.Set;

/**
 * 表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractTableData<T extends TableHelper> implements TableData<T> {

    private T tableModel;

    private Class<T> tableClass;

    protected String tableName;

    protected String tableAlias;

    protected Set<ColumnDatum> columnData;

    public AbstractTableData(Class<T> tableClass) {
        this.tableClass = tableClass;
        try {
            this.tableModel = tableClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new TableDataException(e);
        }
        this.tableName = this.tableModel.getTableName();
        this.tableAlias = this.tableModel.getTableAlias();
    }

    @Override
    public T getTableModel() {
        return this.tableModel;
    }

    @Override
    public Class<T> getTableClass() {
        return this.tableClass;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public void setTableName(String tableName) {
        if (tableName == null || "".equals(tableName.trim())) {
            return;
        }
        this.tableName = tableName;
    }

    @Override
    public String getTableAlias() {
        return this.tableAlias;
    }

    @Override
    public void setTableAlias(String tableAlias) {
        if (tableAlias == null || "".equals(tableAlias.trim())) {
            return;
        }
        this.tableAlias = tableAlias;
    }

    @Override
    public Set<ColumnDatum> getColumnData() {
        return this.columnData;
    }

    @Override
    public void setColumnData(Set<ColumnDatum> columnData) {
        this.columnData = columnData;
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
