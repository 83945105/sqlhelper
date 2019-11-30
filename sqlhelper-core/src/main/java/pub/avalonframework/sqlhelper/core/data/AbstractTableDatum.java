package pub.avalonframework.sqlhelper.core.data;

import java.util.Objects;

/**
 * @author baichao
 */
public abstract class AbstractTableDatum implements TableDatum {

    private Class<?> tableHelperClass;

    protected String tableName;

    protected String tableAlias;

    public AbstractTableDatum(Class<?> tableHelperClass, String tableName, String tableAlias) {
        this.tableHelperClass = tableHelperClass;
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    @Override
    public Class<?> getTableHelperClass() {
        return this.tableHelperClass;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractTableDatum tableData = (AbstractTableDatum) o;
        return Objects.equals(tableAlias, tableData.tableAlias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableAlias);
    }
}