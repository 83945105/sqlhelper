package pub.avalonframework.sqlhelper.core.option;

import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DefaultMySqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DefaultMySqlPartBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.MySqlBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.MySqlPartBuilderTemplate;

/**
 * @author baichao
 */
public final class SqlBuilderOptions {

    public final static SqlBuilderOptions DEFAULT_SQL_BUILDER_OPTIONS = new SqlBuilderOptions();

    /**
     * get default sql builder options
     *
     * @return {@link SqlBuilderOptions}
     */
    public static SqlBuilderOptions getDefaultSqlBuilderOptions() {
        return DEFAULT_SQL_BUILDER_OPTIONS;
    }

    private SqlPartDatumBuilderOptions sqlPartDatumBuilderOptions = SqlPartDatumBuilderOptions.SQL_PART_DATUM_BUILDER_OPTIONS;

    private SqlPrintOptions sqlPrintOptions = SqlPrintOptions.DEFAULT_SQL_PRINT_OPTIONS;

    private MySqlPartBuilderTemplate mySqlPartBuilderTemplate = DefaultMySqlPartBuilderTemplate.DEFAULT_DEFAULT_MY_SQL_PART_BUILDER_TEMPLATE;

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