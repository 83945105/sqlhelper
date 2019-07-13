package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.beans.SqlBuilderResult;

/**
 * MySql构建器模板
 *
 * @author 白超
 * @date 2019/5/23
 */
public abstract class AbstractMySqlBuilderTemplate implements SqlBuilderTemplate<SqlBuilderResult> {

    /**
     * Sql片段构建器模板
     */
    protected SqlPartBuilderTemplate<SqlBuilderResult> sqlPartBuilderTemplate = DefaultMySqlPartBuilderTemplate.DEFAULT_DEFAULT_MY_SQL_PART_BUILDER_TEMPLATE;

    @Override
    public void setSqlPartBuilderTemplate(SqlPartBuilderTemplate<SqlBuilderResult> sqlPartBuilderTemplate) {
        this.sqlPartBuilderTemplate = sqlPartBuilderTemplate;
    }
    
}
