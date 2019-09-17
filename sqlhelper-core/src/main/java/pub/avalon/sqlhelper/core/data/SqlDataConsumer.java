package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author baichao
 */
public interface SqlDataConsumer {

    /**
     * get sql builder options
     *
     * @return {@link SqlBuilderOptions}
     */
    SqlBuilderOptions getSqlBuilderOptions();

    /**
     * get data base type
     *
     * @return {@link DataBaseType}
     */
    DataBaseType getDataBaseType();

    /**
     * get main table data
     *
     * @return {@link MainTableDatum}
     */
    MainTableDatum getMainTableDatum();

    /**
     * get select table column data
     *
     * @return {@link TableColumnDatum}
     */
    List<TableColumnDatum> getSelectTableColumnData();

    /**
     * get insert table column data
     *
     * @return {@link TableColumnDatum}
     */
    List<TableColumnDatum> getInsertTableColumnData();

    /**
     * get update table column data
     *
     * @return {@link TableColumnDatum}
     */
    List<TableColumnDatum> getUpdateTableColumnData();

    /**
     * get join table alias and data
     *
     * @return key - table alias | value - {@link JoinTableDatum}
     */
    LinkedHashMap<String, JoinTableDatum> getAliasJoinTableData();

    /**
     * get table where data
     *
     * @return {@link WhereDataLinker}
     */
    List<TableWhereDatum> getTableWhereData();

    /**
     * get table group data
     *
     * @return {@link TableGroupDatum}
     */
    List<TableGroupDatum> getTableGroupData();

    /**
     * get table sort data
     *
     * @return {@link TableSortDatum}
     */
    List<TableSortDatum> getTableSortData();

    /**
     * get limit data
     *
     * @return {@link LimitDatum}
     */
    LimitDatum getLimitDatum();
}