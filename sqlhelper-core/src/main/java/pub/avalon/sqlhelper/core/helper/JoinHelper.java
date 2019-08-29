package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.JoinSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class JoinHelper<T extends JoinHelper<T>> extends Helper {

    private JoinSqlPartDatumBuilder<T> joinSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public JoinHelper(String tableAlias) {
        super(tableAlias);
        this.joinSqlPartDatumBuilder = new JoinSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.joinSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    protected JoinSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.joinSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.joinSqlPartDatumBuilder;
    }

    protected JoinSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.joinSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.joinSqlPartDatumBuilder;
    }

    public List<OnDatum> takeoutSqlPartData() {
        return this.joinSqlPartDatumBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.joinSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }
}