package pub.avalon.sqlhelper.core.option;

import pub.avalon.sqlhelper.core.sqlbuilder.DefaultMySqlBuilderTemplate;
import pub.avalon.sqlhelper.core.sqlbuilder.MySqlBuilderTemplate;

/**
 * sql构建配置
 *
 * @author 白超
 * @date 2019/5/20
 */
public final class SqlBuilderOptions {

    public final static SqlBuilderOptions DEFAULT_SQL_BUILDER_OPTIONS = new SqlBuilderOptions();

    /**
     * Sql片段数据构建器配置
     */
    private SqlPartDatumBuilderOptions sqlPartDatumBuilderOptions = SqlPartDatumBuilderOptions.SQL_PART_DATUM_BUILDER_OPTIONS;

    /**
     * Sql打印配置
     */
    private SqlPrintOptions sqlPrintOptions = SqlPrintOptions.DEFAULT_SQL_PRINT_OPTIONS;

    /**
     * MySql构建器
     */
    private MySqlBuilderTemplate mySqlBuilderTemplate = DefaultMySqlBuilderTemplate.DEFAULT_DEFAULT_MY_SQL_BUILDER_TEMPLATE;

    public SqlPartDatumBuilderOptions getSqlPartDatumBuilderOptions() {
        return sqlPartDatumBuilderOptions;
    }

    public SqlBuilderOptions setSqlPartDatumBuilderOptions(SqlPartDatumBuilderOptions sqlPartDatumBuilderOptions) {
        this.sqlPartDatumBuilderOptions = sqlPartDatumBuilderOptions;
        return this;
    }

    public SqlPrintOptions getSqlPrintOptions() {
        return sqlPrintOptions;
    }

    public SqlBuilderOptions setSqlPrintOptions(SqlPrintOptions sqlPrintOptions) {
        this.sqlPrintOptions = sqlPrintOptions;
        return this;
    }

    public MySqlBuilderTemplate getMySqlBuilderTemplate() {
        return mySqlBuilderTemplate;
    }

    public SqlBuilderOptions setMySqlBuilderTemplate(MySqlBuilderTemplate mySqlBuilderTemplate) {
        this.mySqlBuilderTemplate = mySqlBuilderTemplate;
        return this;
    }

}
