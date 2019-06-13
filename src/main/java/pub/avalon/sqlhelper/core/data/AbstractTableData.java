package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
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

    private T tableHelper;

    private Class<T> tableHelperClass;

    protected String tableName;

    protected String tableAlias;

    public AbstractTableData(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = BeanUtils.tableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
    }

    @Override
    public T getTableHelper() {
        return this.tableHelper;
    }

    @Override
    public Class<T> getTableHelperClass() {
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
        AbstractTableData tableData = (AbstractTableData) o;
        return Objects.equals(tableAlias, tableData.tableAlias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableAlias);
    }

}
