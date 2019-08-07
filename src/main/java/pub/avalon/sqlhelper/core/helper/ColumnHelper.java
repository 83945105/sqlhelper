package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.TableColumn;
import pub.avalon.sqlhelper.core.builder.ColumnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;
import java.util.Set;

/**
 * 列助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public abstract class ColumnHelper<T extends ColumnHelper<T>> extends Helper {

    private ColumnSqlPartDatumBuilder<T> columnSqlPartDatumBuilder;

    public ColumnHelper(String tableAlias) {
        super(tableAlias);
        this.columnSqlPartDatumBuilder = new ColumnSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    /**
     * 获取表默认列数据
     *
     * @return {@link java.util.LinkedHashSet}
     */
    public abstract Set<TableColumn> getTableDefaultColumns();

    /**
     * 接收sql片段
     *
     * @param templateTableName  模板表名
     * @param templateTableAlias 模板表别名
     * @param sqlPart            sql片段
     * @return {@link SqlPartDatumBuilder}
     */
    protected ColumnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.columnSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.columnSqlPartDatumBuilder;
    }

    /**
     * 接收数据
     *
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     * @param columnHandlers      列处理
     * @return {@link SqlPartDatumBuilder}
     */
    protected ColumnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.columnSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName, columnHandlers);
        return this.columnSqlPartDatumBuilder;
    }

    /**
     * 接收数据
     *
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     * @param mappingFieldName    映射的属性名
     * @return {@link SqlPartDatumBuilder}
     */
    protected ColumnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.columnSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.columnSqlPartDatumBuilder;
    }

    public List<ColumnDatum> takeoutSqlPartData() {
        return this.columnSqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.columnSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }

    public TableColumnDatum execute() {
        return execute(this);
    }

    public static TableColumnDatum execute(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(columnHelper);
        }
        return new TableColumnDatum(columnHelper.getTableAlias(), columnData);
    }

}
