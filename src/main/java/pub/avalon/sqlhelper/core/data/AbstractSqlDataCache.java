package pub.avalon.sqlhelper.core.data;

import org.springframework.beans.BeanUtils;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.norm.Model;

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
public abstract class AbstractSqlDataCache<M extends Model> implements SqlData<M> {

    /**
     * 数据库类型
     */
    private DataBaseType dataBaseType;
    /**
     * 主表数据
     */
    private MainTableData<M> mainTableData;

    public AbstractSqlDataCache(DataBaseType dataBaseType, MainTableData<M> mainTableData) {
        this.dataBaseType = dataBaseType;
        this.mainTableData = mainTableData;
    }

    /**
     * 连接表表名缓存
     * 用于校验连接表是否重复/存在
     */
    private Map<String, Class> joinTableAliasCache = new ConcurrentHashMap<>();
    private LinkedHashMap<String, JoinTableData<? extends Model>> joinTableDataMap;
    private LinkedHashMap<String, JoinTableData<? extends Model>> subQueryJoinTableDataMap;

    @Override
    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }

    @Override
    public MainTableData<M> getMainTableData() {
        return this.mainTableData;
    }

    @Override
    public <J extends Model> JoinTableData<J> getJoinTableData(String alias, Class<J> joinClass) {
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
        JoinTableData<? extends Model> joinTableData = null;
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
    public <J extends Model> void addJoinTableData(JoinTableData<J> joinTableData) {
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
    public <J extends Model> void addSubQueryJoinTableData(JoinTableData<J> joinTableData) {
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
    public LinkedHashMap<String, JoinTableData<? extends Model>> getJoinTableDataMap() {
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
    public <T extends Model> SqlData<T> fission(Class<T> clazz) {
        FissionSqlData<T> fission = new FissionSqlData<T>(this.getDataBaseType(), new MainTableData<>(clazz));
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
     * @param <M>
     */
    private class FissionSqlData<M extends Model> extends AbstractSqlDataCache<M> {

        FissionSqlData(DataBaseType dataBaseType, MainTableData<M> mainTableData) {
            super(dataBaseType, mainTableData);
        }

        @Override
        public Set<ColumnData> getColumnDataSet() {
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
        public List<GroupData> getGroupDataList() {
            throw new SqlException("you can not use getGroupDataList for class FissionSqlData.");
        }

        @Override
        public List<List<SortData>> getSortDataListList() {
            throw new SqlException("you can not use getSortDataListList for class FissionSqlData.");
        }

        @Override
        public LimitHandler getLimitData() {
            throw new SqlException("you can not use getLimitData for class FissionSqlData.");
        }

        @Override
        public void addColumnData(ColumnData columnData) {
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
        public void addGroupData(GroupData groupData) {
            throw new SqlException("you can not use addGroupData for class FissionSqlData.");
        }

        @Override
        public void addSortDataList(List<SortData> sortDataList) {
            throw new SqlException("you can not use addSortDataList for class FissionSqlData.");
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
