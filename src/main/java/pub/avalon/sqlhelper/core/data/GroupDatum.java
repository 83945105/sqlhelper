package pub.avalon.sqlhelper.core.data;

/**
 * 分组数据
 *
 * @author baichao
 * @date 2019/5/6
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
}
