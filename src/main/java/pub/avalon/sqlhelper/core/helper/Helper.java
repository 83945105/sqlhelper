package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Set;

/**
 * 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class Helper<T extends Helper<T, E>, E extends SqlPartDatum> {

    protected String tableAlias;

    private SqlPartDatumBuilder<T, E> sqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public Helper(String tableAlias, SqlPartDatumBuilder<T, E> sqlPartDatumBuilder) {
        this.tableAlias = tableAlias;
        sqlPartDatumBuilder.setHelper((T) this);
        this.sqlPartDatumBuilder = sqlPartDatumBuilder;
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
    protected SqlPartDatumBuilder<T, E> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        this.sqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
        return this.sqlPartDatumBuilder;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public Set<E> takeoutSqlPartData() {
        return this.sqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }

}
