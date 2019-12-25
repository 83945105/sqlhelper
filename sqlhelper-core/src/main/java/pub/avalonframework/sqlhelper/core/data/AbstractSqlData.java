package pub.avalonframework.sqlhelper.core.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSqlData extends AbstractSqlDataCache {

    private List<TableColumnDatum> selectTableColumnData;

    private List<TableColumnDatum> insertTabColumnData;

    private List<TableColumnDatum> updateTabColumnData;

    private List<TableWhereDatum> tableWhereData;

    private List<TableGroupDatum> tableGroupData;

    private List<TableHavingDatum> tableHavingData;

    private List<TableSortDatum> tableSortData;

    private Long limit;

    private Long offset;

    public AbstractSqlData(MainTableDatum mainTableDatum) {
        super(mainTableDatum);
    }

    @Override
    public List<TableColumnDatum> getSelectTableColumnData() {
        return this.selectTableColumnData;
    }

    @Override
    public List<TableColumnDatum> getInsertTableColumnData() {
        return this.insertTabColumnData;
    }

    @Override
    public List<TableColumnDatum> getUpdateTableColumnData() {
        return this.updateTabColumnData;
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
    public List<TableHavingDatum> getTableHavingData() {
        return this.tableHavingData;
    }

    @Override
    public List<TableSortDatum> getTableSortData() {
        return this.tableSortData;
    }

    @Override
    public Long getLimit() {
        return this.limit;
    }

    @Override
    public Long getOffset() {
        return this.offset;
    }

    @Override
    public void addSelectTableColumnDatum(TableColumnDatum tableColumnDatum) {
        if (tableColumnDatum == null) {
            return;
        }
        if (this.selectTableColumnData == null) {
            this.selectTableColumnData = new ArrayList<>();
        }
        this.selectTableColumnData.add(tableColumnDatum);
    }

    @Override
    public void addInsertTableColumnDatum(TableColumnDatum tableColumnDatum) {
        if (tableColumnDatum == null) {
            return;
        }
        if (this.insertTabColumnData == null) {
            this.insertTabColumnData = new ArrayList<>();
        }
        this.insertTabColumnData.add(tableColumnDatum);
    }

    @Override
    public void addUpdateTableColumnDatum(TableColumnDatum tableColumnDatum) {
        if (tableColumnDatum == null) {
            return;
        }
        if (this.updateTabColumnData == null) {
            this.updateTabColumnData = new ArrayList<>();
        }
        this.updateTabColumnData.add(tableColumnDatum);
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
    public void addTableHavingDatum(TableHavingDatum tableHavingDatum) {
        if (tableHavingDatum == null) {
            return;
        }
        if (this.tableHavingData == null) {
            this.tableHavingData = new ArrayList<>();
        }
        this.tableHavingData.add(tableHavingDatum);
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
    public void setLimit(Long limit) {
        if (limit == null) {
            return;
        }
        this.limit = limit;
    }

    @Override
    public void setOffset(Long offset) {
        if (offset == null) {
            return;
        }
        this.offset = offset;
    }
}