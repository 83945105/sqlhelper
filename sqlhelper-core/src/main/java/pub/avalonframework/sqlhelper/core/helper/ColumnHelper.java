package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.TableColumn;
import pub.avalonframework.sqlhelper.core.builder.ColumnSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.data.ColumnDatum;
import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class ColumnHelper<T extends ColumnHelper<T>> extends Helper {

    private ColumnSqlPartDatumBuilder<T> columnSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public ColumnHelper(String tableAlias) {
        super(tableAlias);
        this.columnSqlPartDatumBuilder = new ColumnSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.columnSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    public abstract Set<TableColumn> getTableDefaultColumns();

    protected ColumnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.columnSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.columnSqlPartDatumBuilder;
    }

    protected ColumnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.columnSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName, columnHandlers);
        return this.columnSqlPartDatumBuilder;
    }

    protected ColumnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.columnSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.columnSqlPartDatumBuilder;
    }

    public List<ColumnDatum> takeoutSqlPartData() {
        return this.columnSqlPartDatumBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.columnSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }

    public TableColumnDatum execute() {
        return execute(this);
    }

    public static TableColumnDatum execute(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            columnData = HelperManager.defaultColumnData(columnHelper);
        }
        return new TableColumnDatum(columnHelper.getTableAlias(), columnData);
    }

    public static List<TableColumnDatum> execute(ColumnHelper<?>... columnHelpers) {
        if (columnHelpers == null || columnHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(columnHelpers).map(columnHelper -> ColumnHelper.execute(columnHelper)).collect(Collectors.toList());
    }
}