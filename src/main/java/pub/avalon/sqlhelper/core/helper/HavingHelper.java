package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.HavingDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class HavingHelper<T extends HavingHelper<T>> extends Helper {

    private HavingSqlPartDatumBuilder<T> havingSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public HavingHelper(String tableAlias) {
        super(tableAlias);
        this.havingSqlPartDatumBuilder = new HavingSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.havingSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    protected HavingSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.havingSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.havingSqlPartDatumBuilder;
    }

    protected HavingSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.havingSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName, columnHandlers);
        return this.havingSqlPartDatumBuilder;
    }

    protected HavingSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.havingSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.havingSqlPartDatumBuilder;
    }

    public List<HavingDatum> takeoutSqlPartData() {
        return this.havingSqlPartDatumBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.havingSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }
}