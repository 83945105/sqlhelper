package pub.avalon.sqlhelper.core.build;

import pub.avalon.sqlhelper.core.sql.*;

import java.util.List;

/**
 * SQL构建器
 *
 * @author 白超
 * @date 2018/8/15
 */
public interface SqlBuilder extends Table, QueryByPrimaryKey, Query, Insert, UpdateByPrimaryKey, Update, DeleteByPrimaryKey, Delete {

    /**
     * 获取预编译SQL
     *
     * @return
     */
    String getPreparedStatementSql();

    /**
     * 获取预编译参数
     *
     * @return
     */
    List<Object> getPreparedStatementArgs();

}
