package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;

/**
 * @author baichao
 */
public final class HavingDatum extends AbstractComparisonSqlPartDatum<HavingDatum> {

    private ColumnHandler columnHandler;

    public HavingDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public HavingDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public HavingDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }

    public HavingDatum setColumnHandler(ColumnHandler columnHandler) {
        this.columnType = ColumnTypeEnum.HANDLER;
        this.columnHandler = columnHandler;
        return this;
    }

    public ColumnHandler getColumnHandler() {
        return columnHandler;
    }

    public enum ColumnTypeEnum implements ColumnType {
        /**
         * column handler {@link ColumnHandler}
         */
        HANDLER
    }
}