package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.LimitSql;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.*;

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
    private Set<VirtualFieldDatum> virtualFieldData;
    /**
     * 函数列数据
     */
    private List<TableGroupColumnDatum> tableGroupColumnData;
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
    /**
     * 子查询数据
     */
    private Map<String, SqlBuilder> subQueryDataMap;

    public AbstractSqlData(MainTableDatum mainTableDatum) {
        super(mainTableDatum);
    }

    @Override
    public List<TableColumnDatum> getTableColumnData() {
        return this.tableColumnData;
    }

    @Override
    public Set<VirtualFieldDatum> getVirtualFieldData() {
        return this.virtualFieldData;
    }

    @Override
    public List<TableGroupColumnDatum> getTableGroupColumnData() {
        return this.tableGroupColumnData;
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
    public Map<String, SqlBuilder> getSubQueryDataMap() {
        return this.subQueryDataMap;
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
    public void addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum) {
        if (virtualFieldDatum == null) {
            return;
        }
        if (this.virtualFieldData == null) {
            this.virtualFieldData = new LinkedHashSet<>();
        }
        this.virtualFieldData.add(virtualFieldDatum);
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

    @Override
    public void addSubQueryData(String alias, SqlBuilder sqlBuilder) {
        if (alias == null || alias.trim().length() == 0) {
            throw new TableDataException("subQuery alias can not be null or empty.");
        }
        if (this.subQueryDataMap == null) {
            this.subQueryDataMap = new LinkedHashMap<>();
        }
        this.subQueryDataMap.put(alias, sqlBuilder);
    }

}
