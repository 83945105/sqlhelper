package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author baichao
 */
public interface SqlDataConsumer {

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
     * get table column data
     *
     * @return {@link TableColumnDatum}
     */
    List<TableColumnDatum> getTableColumnData();

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