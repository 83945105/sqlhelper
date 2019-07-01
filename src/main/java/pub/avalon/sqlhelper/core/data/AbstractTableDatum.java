package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.Objects;

/**
 * 表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractTableDatum implements TableDatum {

    private Class<?> tableHelperClass;

    private TableHelper tableHelper;

    protected String tableName;

    protected String tableAlias;

    public AbstractTableDatum(Class<?> tableHelperClass, TableHelper tableHelper) {
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = tableHelper;
        this.tableName = tableHelper.getTableName();
        this.tableAlias = tableHelper.getTableAlias();
    }

    @Override
    public Class<?> getTableHelperClass() {
        return this.tableHelperClass;
    }

    @Override
    public TableHelper getTableHelper() {
        return this.tableHelper;
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
