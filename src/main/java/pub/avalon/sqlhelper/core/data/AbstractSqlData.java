package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.LimitHandler;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.*;

/**
 * Sql数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractSqlData<T extends TableModel> extends AbstractSqlDataCache<T> {

    /**
     * 原始列数据
     */
    private Set<TableColumnData> tableColumnDataSet;
    /**
     * 虚拟列数据
     */
    private Set<VirtualFieldData> virtualFieldDataSet;
    /**
     * 函数列数据
     */
    private List<FunctionColumnData> functionColumnDataList;
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
    private Set<TableGroupData> tableGroupDataSet;
    /**
     * sort条件数据
     */
    private Set<TableSortData> tableSortDataSet;
    /**
     * limit条件数据
     */
    private LimitHandler limitData;

    public AbstractSqlData(MainTableData<T> mainTableData) {
        super(mainTableData);
    }

    @Override
    public Set<TableColumnData> getTableColumnDataSet() {
        return tableColumnDataSet;
    }

    @Override
    public SqlData<T> addTableColumnData(TableColumnData tableColumnData) {
        if (tableColumnData == null) {
            return this;
        }
        if (this.tableColumnDataSet == null) {
            this.tableColumnDataSet = new LinkedHashSet<>();
        }
        this.tableColumnDataSet.add(tableColumnData);
        return this;
    }

    @Override
    public Set<VirtualFieldData> getVirtualFieldDataSet() {
        return this.virtualFieldDataSet;
    }

    @Override
    public SqlData<T> addVirtualFieldData(VirtualFieldData virtualFieldData) {
        if (virtualFieldData == null) {
            return this;
        }
        if (this.virtualFieldDataSet == null) {
            this.virtualFieldDataSet = new LinkedHashSet<>();
        }
        this.virtualFieldDataSet.add(virtualFieldData);
        return this;
    }

    @Override
    public List<FunctionColumnData> getFunctionColumnDataList() {
        return this.functionColumnDataList;
    }

    @Override
    public SqlData<T> addFunctionColumnData(FunctionColumnData functionColumnData) {
        if (functionColumnData == null) {
            return this;
        }
        if (this.functionColumnDataList == null) {
            this.functionColumnDataList = new ArrayList<>();
        }
        this.functionColumnDataList.add(functionColumnData);
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
    public Set<TableGroupData> getTableGroupDataSet() {
        return tableGroupDataSet;
    }

    @Override
    public SqlData<T> addTableGroupData(TableGroupData tableGroupData) {
        if (tableGroupData == null) {
            return this;
        }
        if (this.tableGroupDataSet == null) {
            this.tableGroupDataSet = new LinkedHashSet<>();
        }
        this.tableGroupDataSet.add(tableGroupData);
        return this;
    }

    @Override
    public Set<TableSortData> getTableSortDataSet() {
        return tableSortDataSet;
    }

    @Override
    public SqlData<T> addTableSortData(TableSortData tableSortData) {
        if (tableSortData == null) {
            return this;
        }
        if (this.tableSortDataSet == null) {
            this.tableSortDataSet = new LinkedHashSet<>();
        }
        this.tableSortDataSet.add(tableSortData);
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
