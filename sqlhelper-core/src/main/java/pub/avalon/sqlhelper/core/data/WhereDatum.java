package pub.avalon.sqlhelper.core.data;

/**
 * @author baichao
 */
public final class WhereDatum extends AbstractComparisonSqlPartDatum<WhereDatum> {

    public WhereDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public WhereDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public WhereDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }
}