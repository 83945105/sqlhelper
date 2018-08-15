package com.dt.core.data;

import com.dt.beans.Pagination;
import com.dt.core.bean.*;
import com.dt.core.exception.TableDataException;
import com.dt.core.norm.Data;
import com.dt.core.norm.Model;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class EngineData<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> implements Data<M, ML, MO, MC, MS, MG> {

    private DataBaseType dataBaseType;
    private Map<String, Class> aliasClassCache = new ConcurrentHashMap<>();
    private MainTableData<M, ML, MO, MC, MS, MG> mainMainTableData;
    private Map<String, JoinTableData> joinTableDataAliasMap;
    private Set<AbstractTableData> columnDataSet;
    private Set<VirtualFieldData> virtualFieldDataSet;
    private List<FunctionColumnData> functionColumnDataList;
    private List<List<LinkWhereData>> linkWhereDataListList;
    private List<GroupData> groupDataList;
    private List<List<SortData>> sortDataList;
    private Pagination pagination;

    public EngineData(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    @Override
    public MainTableData<M, ML, MO, MC, MS, MG> getMainTableData() {
        return this.mainMainTableData;
    }

    @Override
    public void setMainTableData(MainTableData<M, ML, MO, MC, MS, MG> mainMainTableData) {
        this.mainMainTableData = mainMainTableData;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinTableData<J, JL, JO, JC, JS, JG> getJoinTableData(String alias, Class<J> joinClass) {
        if (this.joinTableDataAliasMap == null) {
            throw new TableDataException("the alias table [" + alias + "] is not joined.");
        }
        if (alias == null || alias.trim().length() == 0) {
            alias = BeanUtils.instantiateClass(joinClass).getTableAlias();
        }
        if (alias == null || alias.trim().length() == 0) {
            throw new TableDataException("alias can not be null or empty.");
        }
        if (this.aliasClassCache.get(alias) == null) {
            throw new TableDataException("the alias table [" + alias + "] is not joined.");
        }
        JoinTableData joinTableData = this.joinTableDataAliasMap.get(alias);
        if (joinTableData == null) {
            throw new TableDataException("the alias table [" + alias + "] has not joinTableData.");
        }
        return joinTableData;
    }

    @Override
    public void addJoinTableData(JoinTableData joinTableData) {
        if (this.aliasClassCache.get(joinTableData.getTableAlias()) != null) {
            throw new TableDataException("alias table [" + joinTableData.getTableAlias() + "] is already join, you can not join it two times, please change another alias.");
        }
        if (this.joinTableDataAliasMap == null) {
            this.joinTableDataAliasMap = new LinkedHashMap<>();
        }
        this.joinTableDataAliasMap.put(joinTableData.getTableAlias(), joinTableData);
        this.aliasClassCache.put(joinTableData.getTableAlias(), joinTableData.getTableClass());
    }

    @Override
    public Map<String, JoinTableData> getJoinTableDataAliasMap() {
        return joinTableDataAliasMap;
    }

    @Override
    public Set<AbstractTableData> getColumnDataSet() {
        return this.columnDataSet;
    }

    @Override
    public void addColumnData(AbstractTableData columnData) {
        if (columnData == null) {
            return;
        }
        if (this.columnDataSet == null) {
            this.columnDataSet = new LinkedHashSet<>();
        }
        this.columnDataSet.add(columnData);
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
    public List<List<LinkWhereData>> getLinkWhereDataListList() {
        return this.linkWhereDataListList;
    }

    @Override
    public void addLinkWhereDataList(List<LinkWhereData> linkWhereDataList) {
        if (linkWhereDataList == null || linkWhereDataList.size() == 0) {
            return;
        }
        if (this.linkWhereDataListList == null) {
            this.linkWhereDataListList = new ArrayList<>();
        }
        this.linkWhereDataListList.add(linkWhereDataList);
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
    public List<List<SortData>> getSortDataList() {
        return this.sortDataList;
    }

    @Override
    public void addSortDataList(List<SortData> sortDataList) {
        if (sortDataList == null || sortDataList.size() == 0) {
            return;
        }
        if (this.sortDataList == null) {
            this.sortDataList = new ArrayList<>();
        }
        this.sortDataList.add(sortDataList);
    }

    @Override
    public Pagination getPagination() {
        return this.pagination;
    }

    @Override
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public void setLimitStart(Integer limitStart) {
        if (this.pagination == null) {
            this.pagination = new Pagination(this.dataBaseType);
        }
        this.pagination.setLimitStart(limitStart);
    }

    @Override
    public void setLimitEnd(Integer limitEnd) {
        if (this.pagination == null) {
            this.pagination = new Pagination(this.dataBaseType);
        }
        this.pagination.setLimitEnd(limitEnd);
    }

    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }
}
