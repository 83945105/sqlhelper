package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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
     * add table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addTableColumnDatum(TableColumnDatum tableColumnDatum);

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
     * add table sort data
     *
     * @param tableSortDatum {@link TableSortDatum}
     */
    void addTableSortDatum(TableSortDatum tableSortDatum);

    /**
     * set limit data
     *
     * @param limitDatum {@link LimitDatum}
     */
    void setLimitDatum(LimitDatum limitDatum);
}