package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.builder.OnSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.data.OnDatum;
import pub.avalonframework.sqlhelper.core.data.TableOnDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public TableOnDatum execute() {
        return execute(this);
    }

    public static TableOnDatum execute(OnHelper<?> onHelper) {
        List<OnDatum> onData = onHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return null;
        }
        return new TableOnDatum(onHelper.getTableAlias(),
                Collections.singletonList(new ComparisonSqlPartDataLinker(LinkType.AND).setComparisonSqlPartData(onData)));
    }

    public static List<TableOnDatum> execute(OnHelper<?>... onHelpers) {
        if (onHelpers == null || onHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(onHelpers).map(onHelper -> OnHelper.execute(onHelper)).collect(Collectors.toList());
    }
}