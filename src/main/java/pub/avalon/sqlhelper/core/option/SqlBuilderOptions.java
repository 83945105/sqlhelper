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
     * sql打印配置
     */
    private SqlPrintOptions sqlPrintOptions = new SqlPrintOptions();

    /**
     * MySql构建器
     */
    private MySqlBuilderTemplate mySqlBuilderTemplate = new DefaultMySqlBuilderTemplate();

    public SqlPrintOptions getSqlPrintOptions() {
        return sqlPrintOptions;
    }

    public void setSqlPrintOptions(SqlPrintOptions sqlPrintOptions) {
        this.sqlPrintOptions = sqlPrintOptions;
    }

    public MySqlBuilderTemplate getMySqlBuilderTemplate() {
        return mySqlBuilderTemplate;
    }

    public void setMySqlBuilderTemplate(MySqlBuilderTemplate mySqlBuilderTemplate) {
        this.mySqlBuilderTemplate = mySqlBuilderTemplate;
    }

}
