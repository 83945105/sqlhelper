package pub.avalon.sqlhelper.core.option;

import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.sqlbuilder.*;

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
    private MySqlBuilder mySqlBuilder = new DefaultMySqlBuilder();

    /**
     * SqlServer构建器
     */
    private SqlServerBuilder sqlServerBuilder = new DefaultSqlServerBuilder(this);

    public SqlPrintOptions getSqlPrintOptions() {
        return sqlPrintOptions;
    }

    public SqlBuilder getSqlBuilder() {
        switch (sqlData.getDataBaseType()) {
            case MYSQL:
                return this.mySqlBuilder.setSqlData(sqlData);
            case SQLSERVER:
                return this.sqlServerBuilder.setSqlData(sqlData);
            default:
                throw new SqlException("SqlBuilder do not support this database type temporarily.");
        }
    }

}
