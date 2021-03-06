package pub.avalon.sqlhelper.core.data;

import org.springframework.beans.BeanUtils;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sql数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public abstract class AbstractSqlData<M extends Model> implements SqlData<M> {

    private DataBaseType dataBaseType;
    private Map<String, Class> aliasClassCache = new ConcurrentHashMap<>();
    private MainTableData<M> mainMainTableData;
    private Map<String, JoinTableData> joinTableDataAliasMap;
    private Map<String, JoinTableData> subQueryJoinTableDataAliasMap;
    private Set<AbstractTableData> columnDataSet;
    private Set<VirtualFieldData> virtualFieldDataSet;
    private List<FunctionColumnData> functionColumnDataList;
    private Map<String, SqlBuilder> subQueryAliasMap;
    private List<List<LinkWhereData>> linkWhereDataListList;
    private List<GroupData> groupDataList;
    private List<List<SortData>> sortDataList;
    private LimitHandler limit;

    public AbstractSqlData(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    @Override
    public MainTableData<M> getMainTableData() {
        return this.mainMainTableData;
    }


    /**
     * 设置主表数据
     *
     * @param mainMainTableData 主表数据
     */
    public void setMainTableData(MainTableData<M> mainMainTableData) {
        this.mainMainTableData = mainMainTableData;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <J extends Model> JoinTableData<J> getJoinTableData(String alias, Class<J> joinClass) {
        if (this.joinTableDataAliasMap == null && this.subQueryJoinTableDataAliasMap == null) {
            if (alias == null) {
                throw new TableDataException("the class table [" + joinClass + "] is not joined.");
            }
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
        JoinTableData joinTableData = null;
        if (this.joinTableDataAliasMap != null) {
            joinTableData = this.joinTableDataAliasMap.get(alias);
        }
        if (joinTableData == null && this.subQueryJoinTableDataAliasMap != null) {
            joinTableData = this.subQueryJoinTableDataAliasMap.get(alias);
        }
        if (joinTableData == null) {
            throw new TableDataException("the alias table [" + alias + "] has not joinTableData.");
        }
        return joinTableData;
    }

    /**
     * 添加连接表数据
     *
     * @param joinTableData 连接表数据
     */
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

    /**
     * 添加子查询连接表数据
     *
     * @param joinTableData 连接表数据
     */
    public void addSubQueryJoinTableData(JoinTableData joinTableData) {
        if (this.aliasClassCache.get(joinTableData.getTableAlias()) != null) {
            throw new TableDataException("alias table [" + joinTableData.getTableAlias() + "] is already join, you can not join it two times, please change another alias.");
        }
        if (this.subQueryJoinTableDataAliasMap == null) {
            this.subQueryJoinTableDataAliasMap = new LinkedHashMap<>();
        }
        this.subQueryJoinTableDataAliasMap.put(joinTableData.getTableAlias(), joinTableData);
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

    /**
     * 添加列数据
     *
     * @param columnData 列数据
     */
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

    /**
     * 添加虚拟属性数据集合
     *
     * @param virtualFieldData 虚拟属性数据集合
     */
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

    /**
     * 添加函数列数据
     *
     * @param functionColumnData 函数列数据
     */
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
    public Map<String, SqlBuilder> getSubQueryAliasMap() {
        return subQueryAliasMap;
    }

    /**
     * 添加子查询
     *
     * @param alias      子查询别名
     * @param sqlBuilder 子查询
     */
    public void addSubQueryAliasMap(String alias, SqlBuilder sqlBuilder) {
        if (alias == null || alias.trim().length() == 0) {
            throw new TableDataException("subQuery alias can not be null or empty.");
        }
        if (this.subQueryAliasMap == null) {
            this.subQueryAliasMap = new LinkedHashMap<>();
        }
        this.subQueryAliasMap.put(alias, sqlBuilder);
    }

    @Override
    public List<List<LinkWhereData>> getLinkWhereDataListList() {
        return this.linkWhereDataListList;
    }

    /**
     * 添加连接条件数据集合
     *
     * @param linkWhereDataList 连接条件数据集合
     */
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

    /**
     * 添加分组数据
     *
     * @param groupData 分组数据
     */
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

    /**
     * 添加排序数据集合
     *
     * @param sortDataList 排序数据集合
     */
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
    public LimitHandler getLimit() {
        return this.limit;
    }

    /**
     * 设置分页
     *
     * @param limit
     */
    public void setLimit(LimitHandler limit) {
        this.limit = limit;
    }

    /**
     * 设置分页
     *
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    public void setLimit(Integer currentPage, Integer pageSize) {
        this.limit = new Pagination(this.dataBaseType, currentPage, pageSize);
    }

    /**
     * 设置分页
     *
     * @param total       总数
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    public void setLimit(Integer total, Integer currentPage, Integer pageSize) {
        this.limit = new Pagination(this.dataBaseType, total, currentPage, pageSize);
    }

    /**
     * 设置分页开始号
     *
     * @param limitStart 分页开始号
     */
    public void setLimitStart(Integer limitStart) {
        if (this.limit == null) {
            this.limit = new Pagination(this.dataBaseType);
        }
        this.limit.setLimitStart(limitStart);
    }

    /**
     * 设置分页结束号
     *
     * @param limitEnd 分页结束号
     */
    public void setLimitEnd(Integer limitEnd) {
        if (this.limit == null) {
            this.limit = new Pagination(this.dataBaseType);
        }
        this.limit.setLimitEnd(limitEnd);
    }

    @Override
    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

}
