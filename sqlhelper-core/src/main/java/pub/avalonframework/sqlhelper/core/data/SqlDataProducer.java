package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.core.beans.DataBaseType;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public interface SqlDataProducer {

    /**
     * set sql builder options
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions);

    /**
     * set data base type
     *
     * @param dataBaseType {@link DataBaseType}
     */
    void setDataBaseType(DataBaseType dataBaseType);

    /**
     * add join table data
     *
     * @param joinTableDatum {@link JoinTableDatum}
     */
    void addJoinTableDatum(JoinTableDatum joinTableDatum);

    /**
     * add table on data
     *
     * @param tableOnDatum {@link TableOnDatum}
     */
    void addTableOnDatum(TableOnDatum tableOnDatum);

    /**
     * add select table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addSelectTableColumnDatum(TableColumnDatum tableColumnDatum);

    /**
     * add insert table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addInsertTableColumnDatum(TableColumnDatum tableColumnDatum);

    /**
     * add update table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addUpdateTableColumnDatum(TableColumnDatum tableColumnDatum);

    /**
     * add table where data
     *
     * @param tableWhereDatum {@link TableWhereDatum}
     */
    void addTableWhereDatum(TableWhereDatum tableWhereDatum);

    /**
     * add table group data
     *
     * @param tableGroupDatum {@link TableGroupDatum}
     */
    void addTableGroupDatum(TableGroupDatum tableGroupDatum);

    /**
     * add table having data
     *
     * @param tableHavingDatum {@link TableHavingDatum}
     */
    void addTableHavingDatum(TableHavingDatum tableHavingDatum);

    /**
     * add table sort data
     *
     * @param tableSortDatum {@link TableSortDatum}
     */
    void addTableSortDatum(TableSortDatum tableSortDatum);

    /**
     * set limit data
     *
     * @param limit The limit data
     */
    void setLimit(Long limit);

    /**
     * set offset data
     *
     * @param offset The offset data
     */
    void setOffset(Long offset);
}