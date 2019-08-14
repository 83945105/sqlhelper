package pub.avalon.sqlhelper.core.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Sql数据
 *
 * @author baichao
 * @since 2018/7/10
 */
public abstract class AbstractSqlData extends AbstractSqlDataCache {

    /**
     * 原始列数据
     */
    private List<TableColumnDatum> tableColumnData;
    /**
     * where条件数据
     */
    private List<TableWhereDatum> tableWhereData;
    /**
     * group条件数据
     */
    private List<TableGroupDatum> tableGroupData;
    /**
     * sort条件数据
     */
    private List<TableSortDatum> tableSortData;
    /**
     * limit条件数据
     */
    private LimitDatum limitDatum;

    public AbstractSqlData(MainTableDatum mainTableDatum) {
        super(mainTableDatum);
    }

    @Override
    public List<TableColumnDatum> getTableColumnData() {
        return this.tableColumnData;
    }

    @Override
    public List<TableWhereDatum> getTableWhereData() {
        return this.tableWhereData;
    }

    @Override
    public List<TableGroupDatum> getTableGroupData() {
        return this.tableGroupData;
    }

    @Override
    public List<TableSortDatum> getTableSortData() {
        return this.tableSortData;
    }

    @Override
    public LimitDatum getLimitDatum() {
        return this.limitDatum;
    }

    @Override
    public void addTableColumnDatum(TableColumnDatum tableColumnDatum) {
        if (tableColumnDatum == null) {
            return;
        }
        if (this.tableColumnData == null) {
            this.tableColumnData = new ArrayList<>();
        }
        this.tableColumnData.add(tableColumnDatum);
    }

    @Override
    public void addTableWhereDatum(TableWhereDatum tableWhereDatum) {
        if (tableWhereDatum == null) {
            return;
        }
        if (this.tableWhereData == null) {
            this.tableWhereData = new ArrayList<>();
        }
        this.tableWhereData.add(tableWhereDatum);
    }

    @Override
    public void addTableGroupDatum(TableGroupDatum tableGroupDatum) {
        if (tableGroupDatum == null) {
            return;
        }
        if (this.tableGroupData == null) {
            this.tableGroupData = new ArrayList<>();
        }
        this.tableGroupData.add(tableGroupDatum);
    }

    @Override
    public void addTableSortDatum(TableSortDatum tableSortDatum) {
        if (tableSortDatum == null) {
            return;
        }
        if (this.tableSortData == null) {
            this.tableSortData = new ArrayList<>();
        }
        this.tableSortData.add(tableSortDatum);
    }

    @Override
    public void setLimitDatum(LimitDatum limitDatum) {
        if (limitDatum == null) {
            return;
        }
        this.limitDatum = limitDatum;
    }
}
