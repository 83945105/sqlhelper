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
    private Set<AbstractTableData> columnDataSet;
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
    private List<List<LinkWhereData>> linkWhereDataListList;
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
    public Map<String, SqlBuilder> getSubQueryDataMap() {
        return subQueryDataMap;
    }

    /**
     * 添加子查询数据
     *
     * @param alias      子查询别名
     * @param sqlBuilder 子查询
     */
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
    public List<List<SortData>> getSortDataListList() {
        return this.sortDataListList;
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
        if (this.sortDataListList == null) {
            this.sortDataListList = new ArrayList<>();
        }
        this.sortDataListList.add(sortDataList);
    }


    @Override
    public LimitHandler getLimitData() {
        return this.limitData;
    }

    /**
     * 设置分页数据
     *
     * @param limitData 分页数据
     */
    public void setLimitData(LimitHandler limitData) {
        this.limitData = limitData;
    }

    /**
     * 构建分页
     *
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    public void buildLimitData(Integer currentPage, Integer pageSize) {
        this.limitData = new Pagination(this.getDataBaseType(), currentPage, pageSize);
    }

    /**
     * 构建分页
     *
     * @param total       总数
     * @param currentPage 当前页号
     * @param pageSize    每页显示数量
     */
    public void buildLimitData(Integer total, Integer currentPage, Integer pageSize) {
        this.limitData = new Pagination(this.getDataBaseType(), total, currentPage, pageSize);
    }

    /**
     * 设置分页开始号
     *
     * @param limitStart 分页开始号
     */
    public void setLimitStart(Integer limitStart) {
        if (this.limitData == null) {
            this.limitData = new Pagination(this.getDataBaseType());
        }
        this.limitData.setLimitStart(limitStart);
    }

    /**
     * 设置分页结束号
     *
     * @param limitEnd 分页结束号
     */
    public void setLimitEnd(Integer limitEnd) {
        if (this.limitData == null) {
            this.limitData = new Pagination(this.getDataBaseType());
        }
        this.limitData.setLimitEnd(limitEnd);
    }

}
