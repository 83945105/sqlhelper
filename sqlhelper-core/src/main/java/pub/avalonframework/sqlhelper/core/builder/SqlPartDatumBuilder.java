package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.SqlPartDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public interface SqlPartDatumBuilder<T extends Helper, S extends SqlPartDatum> {

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

    /**
     * get helper
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * set helper
     *
     * @param helper extends {@link Helper} object
     */
    void setHelper(T helper);

    /**
     * add sql part datum
     *
     * @param sqlPartDatum implements {@link SqlPartDatum} object
     */
    void addSqlPartDatum(S sqlPartDatum);

    /**
     * Clean up after each takeout.
     *
     * @return implements {@link SqlPartDatum} objects
     */
    List<S> takeoutSqlPartData();

    /**
     * accept sql part
     *
     * @param templateTableName  template table name
     * @param templateTableAlias template table alias
     * @param sqlPart            sql part
     */
    void accept(String templateTableName, String templateTableAlias, String sqlPart);

    /**
     * accept sql data
     *
     * @param templateTableName   template table name
     * @param templateTableAlias  template table alias
     * @param templateColumnName  template column name
     * @param templateColumnAlias template column alias
     * @param mappingFieldName    mapping field name
     * @param columnHandlers      {@link ColumnHandler}
     */
    void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers);

    /**
     * get sql builder options
     *
     * @return {@link SqlBuilderOptions}
     */
    SqlBuilderOptions getSqlBuilderOptions();

    /**
     * set sql builder options
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions);
}