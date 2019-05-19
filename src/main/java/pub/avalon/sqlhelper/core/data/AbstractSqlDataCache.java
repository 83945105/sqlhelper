package pub.avalon.sqlhelper.core.data;

import org.springframework.beans.BeanUtils;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据缓存
 *
 * @author 白超
 * @date 2019/3/4
 */
public abstract class AbstractSqlDataCache<T extends TableModel> implements SqlData<T> {

    /**
     * 数据库类型
     */
    private DataBaseType dataBaseType;
    /**
     * 主表数据
     */
    private MainTableData<T> mainTableData;

    public AbstractSqlDataCache(MainTableData<T> mainTableData) {
        this.mainTableData = mainTableData;
    }

    /**
     * 连接表表名缓存
     * 用于校验连接表是否重复/存在
     */
    private Map<String, Class> joinTableAliasCache = new ConcurrentHashMap<>();
    private LinkedHashMap<String, JoinTableData<? extends TableModel>> joinTableDataMap;
    private LinkedHashMap<String, JoinTableData<? extends TableModel>> subQueryJoinTableDataMap;

    @Override
    public DataBaseType getDataBaseType() {
        return this.dataBaseType;
    }

    @Override
    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    @Override
    public MainTableData<T> getMainTableData() {
        return this.mainTableData;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <J extends TableModel> JoinTableData<J> getJoinTableData(String alias, Class<J> joinClass) {
        if (this.joinTableDataMap == null && this.subQueryJoinTableDataMap == null) {
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
        if (this.joinTableAliasCache.get(alias) == null) {
            throw new TableDataException("the alias table [" + alias + "] is not joined.");
        }
        JoinTableData<? extends TableModel> joinTableData = null;
        if (this.joinTableDataMap != null) {
            joinTableData = this.joinTableDataMap.get(alias);
        }
        if (joinTableData == null && this.subQueryJoinTableDataMap != null) {
            joinTableData = this.subQueryJoinTableDataMap.get(alias);
        }
        if (joinTableData == null) {
            throw new TableDataException("the alias table [" + alias + "] has not joinTableData.");
        }
        return (JoinTableData<J>) joinTableData;
    }

    @Override
    public <J extends TableModel> void addJoinTableData(JoinTableData<J> joinTableData) {
        if (this.joinTableAliasCache.get(joinTableData.getTableAlias()) != null) {
            // 同一个表别名不能使用2次
            throw new TableDataException("alias table [" + joinTableData.getTableAlias() + "] is already join, you can not join it two times, please change another alias.");
        }
        this.joinTableAliasCache.put(joinTableData.getTableAlias(), joinTableData.getTableClass());
        if (this.joinTableDataMap == null) {
            this.joinTableDataMap = new LinkedHashMap<>();
        }
        this.joinTableDataMap.put(joinTableData.getTableAlias(), joinTableData);
    }

    @Override
    public <J extends TableModel> void addSubQueryJoinTableData(JoinTableData<J> joinTableData) {
        if (this.joinTableAliasCache.get(joinTableData.getTableAlias()) != null) {
            // 同一个表别名不能使用2次
            throw new TableDataException("alias table [" + joinTableData.getTableAlias() + "] is already join, you can not join it two times, please change another alias.");
        }
        this.joinTableAliasCache.put(joinTableData.getTableAlias(), joinTableData.getTableClass());
        if (this.subQueryJoinTableDataMap == null) {
            this.subQueryJoinTableDataMap = new LinkedHashMap<>();
        }
        this.subQueryJoinTableDataMap.put(joinTableData.getTableAlias(), joinTableData);
    }

    @Override
    public LinkedHashMap<String, JoinTableData<? extends TableModel>> getJoinTableDataMap() {
        return this.joinTableDataMap;
    }

    /**
     * 分裂
     *
     * @param clazz 目标类Model.class
     * @param <T>
     * @return
     */
    @Override
    public <T extends TableModel> SqlData<T> fission(Class<T> clazz) {
        FissionSqlData<T> fission = new FissionSqlData<T>(new MainTableData<>(clazz));
        fission.setDataBaseType(this.getDataBaseType());
        this.getJoinTableDataMap().forEach((s, joinTableData) -> {
            // 排除目标主表, 防止自身关联自身
            if (joinTableData.getTableClass() != clazz) {
                fission.addJoinTableData(joinTableData);
            }
        });
        return fission;
    }

    /**
     * 分裂Sql数据
     *
     * @param <T>
     */
    private class FissionSqlData<T extends TableModel> extends AbstractSqlDataCache<T> {

        FissionSqlData(MainTableData<T> mainTableData) {
            super(mainTableData);
        }

        @Override
        public Set<TableColumnData> getTableColumnDataSet() {
            throw new SqlException("you can not use getColumnDataSet for class FissionSqlData.");
        }

        @Override
        public Set<VirtualFieldData> getVirtualFieldDataSet() {
            throw new SqlException("you can not use getVirtualFieldDataSet for class FissionSqlData.");
        }

        @Override
        public List<FunctionColumnData> getFunctionColumnDataList() {
            throw new SqlException("you can not use getFunctionColumnDataList for class FissionSqlData.");
        }

        @Override
        public Map<String, SqlBuilder> getSubQueryDataMap() {
            throw new SqlException("you can not use getSubQueryDataMap for class FissionSqlData.");
        }

        @Override
        public List<List<WhereDataLinker>> getWhereDataLinkerListList() {
            throw new SqlException("you can not use getWhereDataLinkerListList for class FissionSqlData.");
        }

        @Override
        public Set<TableGroupData> getTableGroupDataSet() {
            throw new SqlException("you can not use getTableGroupDataSet for class FissionSqlData.");
        }

        @Override
        public Set<TableSortData> getTableSortDataSet() {
            throw new SqlException("you can not use getTableSortDataSet for class FissionSqlData.");
        }

        @Override
        public LimitHandler getLimitData() {
            throw new SqlException("you can not use getLimitData for class FissionSqlData.");
        }

        @Override
        public void addTableColumnData(TableColumnData tableColumnData) {
            throw new SqlException("you can not use addColumnData for class FissionSqlData.");
        }

        @Override
        public void addVirtualFieldData(VirtualFieldData virtualFieldData) {
            throw new SqlException("you can not use addVirtualFieldData for class FissionSqlData.");
        }

        @Override
        public void addFunctionColumnData(FunctionColumnData functionColumnData) {
            throw new SqlException("you can not use addFunctionColumnData for class FissionSqlData.");
        }

        @Override
        public void addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList) {
            throw new SqlException("you can not use addWhereDataLinkerList for class FissionSqlData.");
        }

        @Override
        public void addTableGroupData(TableGroupData tableGroupData) {
            throw new SqlException("you can not use addTableGroupData for class FissionSqlData.");
        }


        @Override
        public void addTableSortData(TableSortData tableSortData) {
            throw new SqlException("you can not use addTableSortData for class FissionSqlData.");
        }


        @Override
        public void setLimitData(LimitHandler limitData) {
            throw new SqlException("you can not use setLimitData for class FissionSqlData.");
        }

        @Override
        public void buildLimitData(Integer currentPage, Integer pageSize) {
            throw new SqlException("you can not use buildLimitData for class FissionSqlData.");
        }

        @Override
        public void buildLimitData(Integer total, Integer currentPage, Integer pageSize) {
            throw new SqlException("you can not use buildLimitData for class FissionSqlData.");
        }

        @Override
        public void setLimitStart(Integer limitStart) {
            throw new SqlException("you can not use setLimitStart for class FissionSqlData.");
        }

        @Override
        public void setLimitEnd(Integer limitEnd) {
            throw new SqlException("you can not use setLimitEnd for class FissionSqlData.");
        }

        @Override
        public void addSubQueryData(String alias, SqlBuilder sqlBuilder) {
            throw new SqlException("you can not use addSubQueryData for class FissionSqlData.");
        }
    }

}
