package pub.avalon.sqlhelper.core.data;

/**
 * @author baichao
 */
public final class OnDatum extends AbstractComparisonSqlPartDatum<OnDatum> {

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public OnDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }
}