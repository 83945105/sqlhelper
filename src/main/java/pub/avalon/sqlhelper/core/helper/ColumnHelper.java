package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.ColumnSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Set;

/**
 * 列助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class ColumnHelper<T extends ColumnHelper<T>> extends Helper {

    private ColumnSqlPartDatumBuilder<T> columnSqlPartDatumBuilder;

    public ColumnHelper(String tableAlias) {
        super(tableAlias);
        this.columnSqlPartDatumBuilder = new ColumnSqlPartDatumBuilder<>(tableAlias, (T) this);
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
    protected ColumnSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        this.columnSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
        return this.columnSqlPartDatumBuilder;
    }

    public Set<ColumnDatum> takeoutSqlPartData() {
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

}
