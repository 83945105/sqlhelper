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

    private SqlPartDatumBuilder<T, E> sqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public Helper(SqlPartDatumBuilder<T, E> sqlPartDatumBuilder) {
        sqlPartDatumBuilder.setHelper((T) this);
        this.sqlPartDatumBuilder = sqlPartDatumBuilder;
    }

    /**
     * 接收数据
     *
     * @param tableName   表名
     * @param tableAlias  表别名
     * @param columnName  列名
     * @param columnAlias 列别名
     * @return {@link SqlPartDatumBuilder}
     */
    protected SqlPartDatumBuilder<T, E> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.sqlPartDatumBuilder.accept(tableName, tableAlias, columnName, columnAlias);
        return this.sqlPartDatumBuilder;
    }

    public Set<E> takeoutSqlPartData() {
        return this.sqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置表别名
     *
     * @param tableAlias 表别名
     */
    public void setTableAlias(String tableAlias) {
        this.sqlPartDatumBuilder.setTableAlias(tableAlias);
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
