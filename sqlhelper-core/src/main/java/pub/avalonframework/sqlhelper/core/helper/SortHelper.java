package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.data.SortDatum;
import pub.avalonframework.sqlhelper.core.data.TableSortDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class SortHelper<T extends SortHelper<T>> extends Helper {

    private SortSqlPartDatumBuilder<T> sortSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public SortHelper(String tableAlias) {
        super(tableAlias);
        this.sortSqlPartDatumBuilder = new SortSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.sortSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    protected SortSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.sortSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.sortSqlPartDatumBuilder;
    }

    protected SortSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.sortSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.sortSqlPartDatumBuilder;
    }

    public List<SortDatum> takeoutSqlPartData() {
        return this.sortSqlPartDatumBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sortSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }

    public TableSortDatum execute() {
        return execute(this);
    }

    public static TableSortDatum execute(SortHelper<?> sortHelper) {
        List<SortDatum> sortData = sortHelper.takeoutSqlPartData();
        if (sortData == null || sortData.size() == 0) {
            return null;
        }
        return new TableSortDatum(sortHelper.getTableAlias(), sortData);
    }

    public static List<TableSortDatum> execute(SortHelper<?>... sortHelpers) {
        if (sortHelpers == null || sortHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(sortHelpers).map(sortHelper -> SortHelper.execute(sortHelper)).collect(Collectors.toList());
    }
}