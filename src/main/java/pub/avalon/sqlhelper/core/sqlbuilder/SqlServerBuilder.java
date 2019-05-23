package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.data.SqlData;

/**
 * @author 白超
 * @date 2019/5/23
 */
public interface SqlServerBuilder extends SqlBuilder {

    /**
     * 设置sql数据
     *
     * @param sqlData
     * @return
     */
    SqlServerBuilder setSqlData(SqlData<?> sqlData);

}
