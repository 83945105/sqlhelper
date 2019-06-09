package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.LimitHandler;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.helper.TableHelper;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.*;

/**
 * Sql数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractSqlData<T extends TableHelper> extends AbstractSqlDataCache<T> {

    /**
     * 原始列数据
     */
    private Set<TableColumnDatum> tableColumnData;
    /**
     * 虚拟列数据
     */
    private Set<VirtualFieldDatum> virtualFieldData;
    /**
     * 函数列数据
     */
    private Set<TableFunctionColumnDatum> tableFunctionColumnData;
    /**
     * 子查询数据
     */
    private Map<String, SqlBuilder> subQueryDataMap;
    /**
     * where条件数据
     */
    private List<List<WhereDataLinker>> whereDataLinkerListList;
    /**
     * group条件数据
     */
    private Set<TableGroupDatum> tableGroupData;
    /**
     * sort条件数据
     */
    private Set<TableSortDatum> tableSortData;
    /**
     * limit条件数据
     */
    private LimitHandler limitData;

    public AbstractSqlData(MainTableData<T> mainTableData) {
        super(mainTableData);
    }

    @Override
    public Set<VirtualFieldDatum> getVirtualFieldData() {
        return this.virtualFieldData;
    }

    @Override
    public Set<TableColumnDatum> getTableColumnData() {
        return this.tableColumnData;
    }

    @Override
    public SqlData<T> addTableColumnDatum(TableColumnDatum tableColumnDatum) {
        if (tableColumnDatum == null) {
            return this;
        }
        if (this.tableColumnData == null) {
            this.tableColumnData = new LinkedHashSet<>();
        }
        this.tableColumnData.add(tableColumnDatum);
        return this;
    }

    @Override
    public SqlData<T> addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum) {
        if (virtualFieldDatum == null) {
            return this;
        }
        if (this.virtualFieldData == null) {
            this.virtualFieldData = new LinkedHashSet<>();
        }
        this.virtualFieldData.add(virtualFieldDatum);
        return this;
    }

    @Override
    public Set<TableFunctionColumnDatum> getTableFunctionColumnData() {
        return this.tableFunctionColumnData;
    }

    @Override
    public SqlData<T> addTableFunctionColumnDatum(TableFunctionColumnDatum tableFunctionColumnDatum) {
        if (tableFunctionColumnDatum == null) {
            return this;
        }
        if (this.tableFunctionColumnData == null) {
            this.tableFunctionColumnData = new LinkedHashSet<>();
        }
        this.tableFunctionColumnData.add(tableFunctionColumnDatum);
        return this;
    }

    @Override
    public Map<String, SqlBuilder> getSubQueryDataMap() {
        return subQueryDataMap;
    }

    @Override
    public SqlData<T> addSubQueryData(String alias, SqlBuilder sqlBuilder) {
        if (alias == null || alias.trim().length() == 0) {
            throw new TableDataException("subQuery alias can not be null or empty.");
        }
        if (this.subQueryDataMap == null) {
            this.subQueryDataMap = new LinkedHashMap<>();
        }
        this.subQueryDataMap.put(alias, sqlBuilder);
        return this;
    }

    @Override
    public List<List<WhereDataLinker>> getWhereDataLinkerListList() {
        return this.whereDataLinkerListList;
    }

    @Override
    public Set<TableGroupDatum> getTableGroupData() {
        return this.tableGroupData;
    }

    @Override
    public Set<TableSortDatum> getTableSortData() {
        return this.tableSortData;
    }

    @Override
    public SqlData<T> addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList) {
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return this;
        }
        if (this.whereDataLinkerListList == null) {
            this.whereDataLinkerListList = new ArrayList<>();
        }
        this.whereDataLinkerListList.add(whereDataLinkerList);
        return this;
    }

    @Override
    public SqlData<T> addTableGroupDatum(TableGroupDatum tableGroupDatum) {
        if (tableGroupDatum == null) {
            return this;
        }
        if (this.tableGroupData == null) {
            this.tableGroupData = new LinkedHashSet<>();
        }
        this.tableGroupData.add(tableGroupDatum);
        return this;
    }

    @Override
    public SqlData<T> addTableSortDatum(TableSortDatum tableSortDatum) {
        if (tableSortDatum == null) {
            return this;
        }
        if (this.tableSortData == null) {
            this.tableSortData = new LinkedHashSet<>();
        }
        this.tableSortData.add(tableSortDatum);
        return this;
    }


    @Override
    public LimitHandler getLimitData() {
        return this.limitData;
    }

    @Override
    public SqlData<T> setLimitData(LimitHandler limitData) {
        this.limitData = limitData;
        return this;
    }

    @Override
    public SqlData<T> buildLimitData(Integer currentPage, Integer pageSize) {
        this.limitData = new Pagination(this.getDataBaseType(), currentPage, pageSize);
        return this;
    }

    @Override
    public SqlData<T> buildLimitData(Integer total, Integer currentPage, Integer pageSize) {
        this.limitData = new Pagination(this.getDataBaseType(), total, currentPage, pageSize);
        return this;
    }

    @Override
    public SqlData<T> setLimitStart(Integer limitStart) {
        if (this.limitData == null) {
            this.limitData = new Pagination(this.getDataBaseType());
        }
        this.limitData.setLimitStart(limitStart);
        return this;
    }

    @Override
    public SqlData<T> setLimitEnd(Integer limitEnd) {
        if (this.limitData == null) {
            this.limitData = new Pagination(this.getDataBaseType());
        }
        this.limitData.setLimitEnd(limitEnd);
        return this;
    }

}
