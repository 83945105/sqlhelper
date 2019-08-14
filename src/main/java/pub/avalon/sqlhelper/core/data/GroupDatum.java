package pub.avalon.sqlhelper.core.data;

/**
 * 分组数据
 *
 * @author baichao
 * @date 2019/5/6
 */
public final class GroupDatum extends AbstractSqlPartDatum<GroupDatum> {

    public GroupDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public GroupDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }
}
