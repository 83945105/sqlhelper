package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.sqlhelper.core.data.SqlData;

/**
 * @author 白超
 * @date 2019/5/23
 */
public interface MySqlBuilder extends SqlBuilder {

    /**
     * 设置sql数据
     *
     * @param sqlData
     * @return
     */
    MySqlBuilder setSqlData(SqlData<?> sqlData);

}
