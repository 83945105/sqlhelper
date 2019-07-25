package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.LimitSql;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Sql数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractSqlData extends AbstractSqlDataCache {

    /**
     * 原始列数据
     */
    private List<TableColumnDatum> tableColumnData;
    /**
     * 虚拟列数据
     */
    private List<VirtualColumnDatum> virtualColumnData;
    /**
     * 聚合列数据
     */
    private List<TableGroupColumnDatum> tableGroupColumnData;
    /**
     * 子查询列
     */
    private List<SubQueryColumnDatum> subQueryColumnData;
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
    private LimitSql limitData;

    public AbstractSqlData(MainTableDatum mainTableDatum) {
        super(mainTableDatum);
    }

    @Override
    public List<TableColumnDatum> getTableColumnData() {
        return this.tableColumnData;
    }

    @Override
    public List<VirtualColumnDatum> getVirtualColumnData() {
        return this.virtualColumnData;
    }

    @Override
    public List<TableGroupColumnDatum> getTableGroupColumnData() {
        return this.tableGroupColumnData;
    }

    @Override
    public List<SubQueryColumnDatum> getSubQueryColumnData() {
        return this.subQueryColumnData;
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
    public LimitSql getLimitData() {
        return this.limitData;
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
    public void addVirtualColumnDatum(VirtualColumnDatum virtualColumnDatum) {
        if (virtualColumnDatum == null) {
            return;
        }
        if (this.virtualColumnData == null) {
            this.virtualColumnData = new ArrayList<>();
        }
        this.virtualColumnData.add(virtualColumnDatum);
    }

    @Override
    public void addTableGroupColumnDatum(TableGroupColumnDatum tableGroupColumnDatum) {
        if (tableGroupColumnDatum == null) {
            return;
        }
        if (this.tableGroupColumnData == null) {
            this.tableGroupColumnData = new ArrayList<>();
        }
        this.tableGroupColumnData.add(tableGroupColumnDatum);
    }

    @Override
    public void addSubQueryColumnDatum(SubQueryColumnDatum subQueryColumnDatum) {
        if (subQueryColumnDatum == null) {
            return;
        }
        if (this.subQueryColumnData == null) {
            this.subQueryColumnData = new ArrayList<>();
        }
        this.subQueryColumnData.add(subQueryColumnDatum);
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
    public void setLimitData(LimitSql limitData) {
        this.limitData = limitData;
    }

    @Override
    public void buildLimitData(Long currentPage, Long pageSize) {
        this.limitData = new Pagination(this.getDataBaseType(), currentPage, pageSize);
    }

    @Override
    public void buildLimitData(Long total, Long currentPage, Long pageSize) {
        this.limitData = new Pagination(this.getDataBaseType(), total, currentPage, pageSize);
    }

}
