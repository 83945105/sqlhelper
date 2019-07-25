package pub.avalon.sqlhelper.core.sqlbuilder.beans;

import java.util.List;

/**
 * @author 白超
 * @date 2019/7/25
 */
public interface SqlBuilderResult {

    boolean hasPreparedStatementSql();

    boolean hasPreparedStatementArgs();

    /**
     * 获取sql
     * 该sql为填充过参数的sql语句
     *
     * @return sql
     */
//    String getSql();

    /**
     * 获取预编译SQL
     *
     * @return sql
     */
    String getPreparedStatementSql();

    /**
     * 获取预编译参数
     *
     * @return 参数
     */
    List<Object> getPreparedStatementArgs();

}
