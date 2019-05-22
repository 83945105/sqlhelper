package pub.avalon.sqlhelper.core.option;

import pub.avalon.sqlhelper.core.sqlbuilder.MySqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlServerBuilder;

/**
 * sql构建配置
 *
 * @author 白超
 * @date 2019/5/20
 */
public class SqlBuilderOptions {

    /**
     * sql打印配置
     */
    private SqlPrintOptions sqlPrintOptions = new SqlPrintOptions();

    /**
     * MySql构建器
     */
    private SqlBuilder mySqlBuilder = new MySqlBuilder(this);

    /**
     * SqlServer构建器
     */
    private SqlBuilder sqlServerBuilder = new SqlServerBuilder(this);

    public SqlPrintOptions getSqlPrintOptions() {
        return sqlPrintOptions;
    }

    public SqlBuilder getSqlBuilder() {
        // 根据情况自动切换
        return null;
    }

}
