package pub.avalon.sqlhelper.core.option;

import pub.avalon.sqlhelper.core.sqlbuilder.template.DefaultMySqlBuilderTemplate;
import pub.avalon.sqlhelper.core.sqlbuilder.template.DefaultMySqlPartBuilderTemplate;
import pub.avalon.sqlhelper.core.sqlbuilder.template.MySqlBuilderTemplate;
import pub.avalon.sqlhelper.core.sqlbuilder.template.MySqlPartBuilderTemplate;

/**
 * Sql构建配置
 *
 * @author 白超
 * @date 2019/5/20
 */
public final class SqlBuilderOptions {

    public final static SqlBuilderOptions DEFAULT_SQL_BUILDER_OPTIONS = new SqlBuilderOptions();

    /**
     * 获取默认Sql构建配置
     *
     * @return {@link SqlBuilderOptions}
     */
    public static SqlBuilderOptions getDefaultSqlBuilderOptions() {
        return DEFAULT_SQL_BUILDER_OPTIONS;
    }

    /**
     * Sql片段数据构建器配置
     */
    private SqlPartDatumBuilderOptions sqlPartDatumBuilderOptions = SqlPartDatumBuilderOptions.SQL_PART_DATUM_BUILDER_OPTIONS;

    /**
     * Sql打印配置
     */
    private SqlPrintOptions sqlPrintOptions = SqlPrintOptions.DEFAULT_SQL_PRINT_OPTIONS;

    /**
     * MySql片段构建器模板
     */
    private MySqlPartBuilderTemplate mySqlPartBuilderTemplate = DefaultMySqlPartBuilderTemplate.DEFAULT_DEFAULT_MY_SQL_PART_BUILDER_TEMPLATE;

    /**
     * MySql构建器模板
     */
    private MySqlBuilderTemplate mySqlBuilderTemplate = DefaultMySqlBuilderTemplate.DEFAULT_DEFAULT_MY_SQL_BUILDER_TEMPLATE;

    public SqlPartDatumBuilderOptions getSqlPartDatumBuilderOptions() {
        return this.sqlPartDatumBuilderOptions;
    }

    public SqlBuilderOptions setSqlPartDatumBuilderOptions(SqlPartDatumBuilderOptions sqlPartDatumBuilderOptions) {
        this.sqlPartDatumBuilderOptions = sqlPartDatumBuilderOptions;
        return this;
    }

    public SqlPrintOptions getSqlPrintOptions() {
        return this.sqlPrintOptions;
    }

    public SqlBuilderOptions setSqlPrintOptions(SqlPrintOptions sqlPrintOptions) {
        this.sqlPrintOptions = sqlPrintOptions;
        return this;
    }

    public MySqlPartBuilderTemplate getMySqlPartBuilderTemplate() {
        return this.mySqlPartBuilderTemplate;
    }

    public SqlBuilderOptions setMySqlPartBuilderTemplate(MySqlPartBuilderTemplate mySqlPartBuilderTemplate) {
        this.mySqlPartBuilderTemplate = mySqlPartBuilderTemplate;
        return this;
    }

    public MySqlBuilderTemplate getMySqlBuilderTemplate() {
        this.mySqlBuilderTemplate.setSqlPartBuilderTemplate(this.mySqlPartBuilderTemplate);
        return this.mySqlBuilderTemplate;
    }

    public SqlBuilderOptions setMySqlBuilderTemplate(MySqlBuilderTemplate mySqlBuilderTemplate) {
        this.mySqlBuilderTemplate = mySqlBuilderTemplate;
        return this;
    }

}
