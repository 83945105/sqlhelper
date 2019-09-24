package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class OnHelper<T extends OnHelper<T>> extends Helper {

    private OnSqlPartDatumBuilder<T> onSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public OnHelper(String tableAlias) {
        super(tableAlias);
        this.onSqlPartDatumBuilder = new OnSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.onSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    protected OnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.onSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.onSqlPartDatumBuilder;
    }

    protected OnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.onSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.onSqlPartDatumBuilder;
    }

    public List<OnDatum> takeoutSqlPartData() {
        return this.onSqlPartDatumBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.onSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }
}