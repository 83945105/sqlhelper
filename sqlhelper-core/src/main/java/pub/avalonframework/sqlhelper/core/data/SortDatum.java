package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.beans.SortType;

/**
 * @author baichao
 */
public final class SortDatum extends AbstractSqlPartDatum<SortDatum> {

    private SortType sortType = SortType.ASC;

    private Type type = Type.DEFAULT;

    private String sqlPart;

    public SortDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public SortDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public SortDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.sqlPart = sqlPart;
        this.type = Type.SQL_PART;
    }

    public SortType getSortType() {
        return sortType;
    }

    public Type getType() {
        return type;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public SortDatum setSortType(SortType sortType) {
        this.sortType = sortType;
        return this;
    }

    public SortDatum setType(Type type) {
        this.type = type;
        return this;
    }

    public enum Type {
        /**
         * default type
         */
        DEFAULT,
        /**
         * custom column sql
         */
        SQL_PART
    }


    @Override
    public SortDatum setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public SortDatum setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public SortDatum setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public SortDatum setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }
}