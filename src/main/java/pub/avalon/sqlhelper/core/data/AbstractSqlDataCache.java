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

    /**
     * 添加连接表数据
     *
     * @param joinTableData 连接表数据
     * @param <J>
     */
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

    /**
     * 添加子查询连接表数据
     *
     * @param joinTableData 连接表数据
     * @param <J>
     */
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
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends Model> SqlData<T> fission(Class<T> clazz) {
        FissionSqlData<T> fission = new FissionSqlData<T>(this.getDataBaseType(), new MainTableData<>(clazz));
        this.joinTableDataMap.forEach((s, joinTableData) -> {
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
        public Set<AbstractTableData> getColumnDataSet() {
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
    }

}
