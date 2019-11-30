package pub.avalonframework.sqlhelper.core.data;

/**
 * @author baichao
 */
public final class GroupDatum extends AbstractSqlPartDatum<GroupDatum> {

    private Type type = Type.DEFAULT;

    private String sqlPart;

    public GroupDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public GroupDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public GroupDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, null, null);
        this.sqlPart = sqlPart;
        this.type = Type.SQL_PART;
    }

    public Type getType() {
        return type;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public GroupDatum setType(Type type) {
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
    public GroupDatum setTableName(String tableName) {
        super.setTableName(tableName);
        return this;
    }

    @Override
    public GroupDatum setTableAlias(String tableAlias) {
        super.setTableAlias(tableAlias);
        return this;
    }

    @Override
    public GroupDatum setColumnName(String columnName) {
        super.setColumnName(columnName);
        return this;
    }

    @Override
    public GroupDatum setColumnAlias(String columnAlias) {
        super.setColumnAlias(columnAlias);
        return this;
    }
}