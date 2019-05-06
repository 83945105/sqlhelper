package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.*;

/**
 * Sql数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractSqlData<M extends Model> extends AbstractSqlDataCache<M> {

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

    public AbstractSqlData(DataBaseType dataBaseType, MainTableData<M> mainTableData) {
        super(dataBaseType, mainTableData);
    }

    @Override
    public Set<TableColumnData> getTableColumnDataSet() {
        return tableColumnDataSet;
    }

    @Override
    public void addTableColumnData(TableColumnData tableColumnData) {
        if (tableColumnData == null) {
            return;
        }
        if (this.tableColumnDataSet == null) {
            this.tableColumnDataSet = new LinkedHashSet<>();
        }
        this.tableColumnDataSet.add(tableColumnData);
    }

    @Override
    public Set<VirtualFieldData> getVirtualFieldDataSet() {
        return this.virtualFieldDataSet;
    }

    @Override
    public void addVirtualFieldData(VirtualFieldData virtualFieldData) {
        if (virtualFieldData == null) {
            return;
        }
        if (this.virtualFieldDataSet == null) {
            this.virtualFieldDataSet = new LinkedHashSet<>();
        }
        this.virtualFieldDataSet.add(virtualFieldData);
    }

    @Override
    public List<FunctionColumnData> getFunctionColumnDataList() {
        return this.functionColumnDataList;
    }

    @Override
    public void addFunctionColumnData(FunctionColumnData functionColumnData) {
        if (functionColumnData == null) {
            return;
        }
        if (this.functionColumnDataList == null) {
            this.functionColumnDataList = new ArrayList<>();
        }
        this.functionColumnDataList.add(functionColumnData);
    }

    @Override
    public Map<String, SqlBuilder> getSubQueryDataMap() {
        return subQueryDataMap;
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

    @Override
    public List<List<WhereDataLinker>> getWhereDataLinkerListList() {
        return this.whereDataLinkerListList;
    }

    @Override
    public void addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList) {
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return;
        }
        if (this.whereDataLinkerListList == null) {
            this.whereDataLinkerListList = new ArrayList<>();
        }
        this.whereDataLinkerListList.add(whereDataLinkerList);
    }

    @Override
    public Set<TableGroupData> getTableGroupDataSet() {
        return tableGroupDataSet;
    }

    @Override
    public void addTableGroupData(TableGroupData tableGroupData) {
        if (tableGroupData == null) {
            return;
        }
        if (this.tableGroupDataSet == null) {
            this.tableGroupDataSet = new LinkedHashSet<>();
        }
        this.tableGroupDataSet.add(tableGroupData);
    }

    @Override
    public Set<TableSortData> getTableSortDataSet() {
        return tableSortDataSet;
    }

    @Override
    public void addTableSortData(TableSortData tableSortData) {
        if (tableSortData == null) {
            return;
        }
        if (this.tableSortDataSet == null) {
            this.tableSortDataSet = new LinkedHashSet<>();
        }
        this.tableSortDataSet.add(tableSortData);
    }


    @Override
    public LimitHandler getLimitData() {
        return this.limitData;
    }

    @Override
    public void setLimitData(LimitHandler limitData) {
        this.limitData = limitData;
    }

    @Override
    public void buildLimitData(Integer currentPage, Integer pageSize) {
        this.limitData = new Pagination(this.getDataBaseType(), currentPage, pageSize);
    }

    @Override
    public void buildLimitData(Integer total, Integer currentPage, Integer pageSize) {
        this.limitData = new Pagination(this.getDataBaseType(), total, currentPage, pageSize);
    }

    @Override
    public void setLimitStart(Integer limitStart) {
        if (this.limitData == null) {
            this.limitData = new Pagination(this.getDataBaseType());
        }
        this.limitData.setLimitStart(limitStart);
    }

    @Override
    public void setLimitEnd(Integer limitEnd) {
        if (this.limitData == null) {
            this.limitData = new Pagination(this.getDataBaseType());
        }
        this.limitData.setLimitEnd(limitEnd);
    }

}
