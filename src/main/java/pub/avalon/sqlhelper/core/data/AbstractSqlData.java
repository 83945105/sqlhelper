package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
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
    private List<GroupData> groupDataList;
    /**
     * sort条件数据
     */
    private List<List<SortData>> sortDataListList;
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
    public List<GroupData> getGroupDataList() {
        return this.groupDataList;
    }

    @Override
    public void addGroupData(GroupData groupData) {
        if (groupData == null) {
            return;
        }
        if (this.groupDataList == null) {
            this.groupDataList = new ArrayList<>();
        }
        this.groupDataList.add(groupData);
    }

    @Override
    public List<List<SortData>> getSortDataListList() {
        return this.sortDataListList;
    }

    @Override
    public void addSortDataList(List<SortData> sortDataList) {
        if (sortDataList == null || sortDataList.size() == 0) {
            return;
        }
        if (this.sortDataListList == null) {
            this.sortDataListList = new ArrayList<>();
        }
        this.sortDataListList.add(sortDataList);
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
