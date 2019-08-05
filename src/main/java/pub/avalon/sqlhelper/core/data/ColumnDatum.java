package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.ColumnType;

/**
 * 列数据
 *
 * @author 白超
 * @date 2019/3/4
 */
public final class ColumnDatum extends AbstractSqlPartDatum<ColumnDatum> {

    private ColumnType columnType = ColumnType.DEFAULT;

    private ColumnHandler[] columnHandlers;

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public ColumnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }


    public ColumnDatum setColumnType(ColumnType columnType) {
        this.columnType = columnType;
        return this;
    }

    public ColumnDatum setColumnHandlers(ColumnHandler[] columnHandlers) {
        this.columnHandlers = columnHandlers;
        return this;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public ColumnHandler[] getColumnHandlers() {
        return columnHandlers;
    }
}
