package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.TableSqlBuilderResult;

/**
 * @author baichao
 */
public interface TableSqlBuilder {

    /**
     * copy table
     *
     * @param targetTableName target table name
     * @param copyData        copy data or not
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult copyTable(String targetTableName, boolean copyData);

    /**
     * delete table
     *
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult deleteTable();

    /**
     * rename table
     *
     * @param newTableName new table name
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult renameTable(String newTableName);

    /**
     * is table exist
     *
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult isTableExist();
}