package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.SortType;

/**
 * 排序数据
 *
 * @author baichao
 * @since 2018/7/10
 */
public final class SortDatum extends AbstractSqlPartDatum<SortDatum> {

    private SortType sortType = SortType.ASC;

    public SortDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

    public SortDatum(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        super(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
    }

    public SortDatum setSortType(SortType sortType) {
        this.sortType = sortType;
        return this;
    }

    public SortType getSortType() {
        return sortType;
    }

}
