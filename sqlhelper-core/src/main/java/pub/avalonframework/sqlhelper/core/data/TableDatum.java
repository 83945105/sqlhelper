package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.helper.TableHelper;

/**
 * @author baichao
 */
public interface TableDatum {

    /**
     * get extends {@link TableHelper} class
     *
     * @return {@link TableHelper}
     */
    Class<?> getTableHelperClass();

    /**
     * get table name
     *
     * @return table name
     */
    String getTableName();

    /**
     * set table name
     *
     * @param tableName table name
     */
    void setTableName(String tableName);

    /**
     * get table alias
     *
     * @return table alias
     */
    String getTableAlias();

    /**
     * set table alias
     *
     * @param tableAlias table alias
     */
    void setTableAlias(String tableAlias);
}