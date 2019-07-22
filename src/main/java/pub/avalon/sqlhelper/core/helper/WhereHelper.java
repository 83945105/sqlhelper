package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Set;

/**
 * 条件助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public abstract class WhereHelper<T extends WhereHelper<T>> extends Helper {

    private WhereSqlPartDatumBuilder<T> whereSqlPartDatumBuilder;

    public WhereHelper(String tableAlias) {
        super(tableAlias);
        this.whereSqlPartDatumBuilder = new WhereSqlPartDatumBuilder<>(tableAlias, (T) this);
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
    protected WhereSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        this.whereSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
        return this.whereSqlPartDatumBuilder;
    }

    public Set<WhereDatum> takeoutSqlPartData() {
        return this.whereSqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.whereSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }

    public TableWhereDatum execute() {
        return execute(this);
    }

    public static TableWhereDatum execute(WhereHelper<?> whereHelper) {
        Set<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return null;
        }
        return new TableWhereDatum(whereHelper.getTableAlias(), whereData);
    }
}
