package pub.avalon.sqlhelper.core.data;

/**
 * @author baichao
 */
public final class HavingDatum extends AbstractComparisonSqlPartDatum<HavingDatum> {

    public HavingDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public HavingDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public HavingDatum(String templateTableName, String templateTableAlias, String sqlPart) {
        super(templateTableName, templateTableAlias, sqlPart);
    }
}