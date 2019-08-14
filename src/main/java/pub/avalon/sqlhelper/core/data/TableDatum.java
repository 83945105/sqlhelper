package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

/**
 * 表数据
 *
 * @author baichao
 * @date 2019/5/2
 */
public interface TableDatum {

    /**
     * 获取表助手Class
     *
     * @return {@link TableHelper}
     */
    Class<?> getTableHelperClass();

    /**
     * 获取表名称
     *
     * @return 表名称
     */
    String getTableName();

    /**
     * 设置表名称
     *
     * @param tableName 表名称
     */
    void setTableName(String tableName);

    /**
     * 获取表别名
     *
     * @return 表别名
     */
    String getTableAlias();

    /**
     * 设置表别名
     *
     * @param tableAlias 表别名
     */
    void setTableAlias(String tableAlias);

}
