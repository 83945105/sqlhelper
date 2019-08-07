package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.SortSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.SortDatum;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;
import java.util.Set;

/**
 * 排序助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public abstract class SortHelper<T extends SortHelper<T>> extends Helper {

    private SortSqlPartDatumBuilder<T> sortSqlPartDatumBuilder;

    public SortHelper(String tableAlias) {
        super(tableAlias);
        this.sortSqlPartDatumBuilder = new SortSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    /**
     * 接收sql片段
     *
     * @param templateTableName  模板表名
     * @param templateTableAlias 模板表别名
     * @param sqlPart            sql片段
     * @return {@link SqlPartDatumBuilder}
     */
    protected SortSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.sortSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.sortSqlPartDatumBuilder;
    }

    /**
     * 接收数据
     *
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     * @return {@link SqlPartDatumBuilder}
     */
    protected SortSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.sortSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.sortSqlPartDatumBuilder;
    }

    public List<SortDatum> takeoutSqlPartData() {
        return this.sortSqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
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

}
