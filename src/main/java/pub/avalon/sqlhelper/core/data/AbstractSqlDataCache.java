package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据缓存
 *
 * @author 白超
 * @date 2019/3/4
 */
public abstract class AbstractSqlDataCache<T extends TableHelper> implements SqlData<T> {

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
    private LinkedHashMap<String, JoinTableData<? extends TableHelper>> joinTableDataMap;
    private LinkedHashMap<String, JoinTableData<? extends TableHelper>> subQueryJoinTableDataMap;

    @Override
    public DataBaseType getDataBaseType() {
        return this.dataBaseType;
    }

    @Override
    public MainTableData<T> getMainTableData() {
        return this.mainTableData;
    }

    @Override
    public <J extends TableHelper> JoinTableData<J> getJoinTableData(Class<J> tableHelperClass, String tableAlias) {
        if (this.joinTableDataMap == null && this.subQueryJoinTableDataMap == null) {
            if (tableAlias == null) {
                throw new TableDataException("the class table [" + tableHelperClass + "] is not joined.");
            }
            throw new TableDataException("the alias table [" + tableAlias + "] is not joined.");
        }
        if (tableAlias == null || tableAlias.trim().length() == 0) {
            tableAlias = BeanUtils.tableHelper(tableHelperClass).getTableAlias();
        }
        if (tableAlias == null || tableAlias.trim().length() == 0) {
            throw new TableDataException("alias can not be null or empty.");
        }
        if (this.joinTableAliasCache.get(tableAlias) == null) {
            throw new TableDataException("the alias table [" + tableAlias + "] is not joined.");
        }
        JoinTableData<? extends TableHelper> joinTableData = null;
        if (this.joinTableDataMap != null) {
            joinTableData = this.joinTableDataMap.get(tableAlias);
        }
        if (joinTableData == null && this.subQueryJoinTableDataMap != null) {
            joinTableData = this.subQueryJoinTableDataMap.get(tableAlias);
        }
        if (joinTableData == null) {
            throw new TableDataException("the alias table [" + tableAlias + "] has not joinTableData.");
        }
        return (JoinTableData<J>) joinTableData;
    }

    @Override
    public LinkedHashMap<String, JoinTableData<? extends TableHelper>> getJoinTableDataMap() {
        return this.joinTableDataMap;
    }

    @Override
    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    @Override
    public <J extends TableHelper> void addJoinTableData(JoinTableData<J> joinTableData) {
        if (this.joinTableAliasCache.get(joinTableData.getTableAlias()) != null) {
            // 同一个表别名不能使用2次
            throw new TableDataException("alias table [" + joinTableData.getTableAlias() + "] is already join, you can not join it two times, please change another alias.");
        }
        this.joinTableAliasCache.put(joinTableData.getTableAlias(), joinTableData.getTableHelperClass());
        if (this.joinTableDataMap == null) {
            this.joinTableDataMap = new LinkedHashMap<>();
        }
        this.joinTableDataMap.put(joinTableData.getTableAlias(), joinTableData);
    }

    @Override
    public <J extends TableHelper> void addSubQueryJoinTableData(JoinTableData<J> joinTableData) {
        if (this.joinTableAliasCache.get(joinTableData.getTableAlias()) != null) {
            // 同一个表别名不能使用2次
            throw new TableDataException("alias table [" + joinTableData.getTableAlias() + "] is already join, you can not join it two times, please change another alias.");
        }
        this.joinTableAliasCache.put(joinTableData.getTableAlias(), joinTableData.getTableHelperClass());
        if (this.subQueryJoinTableDataMap == null) {
            this.subQueryJoinTableDataMap = new LinkedHashMap<>();
        }
        this.subQueryJoinTableDataMap.put(joinTableData.getTableAlias(), joinTableData);
    }


/*    public <T extends TableHelper> SqlData<T> fission(Class<T> clazz) {
        FissionSqlData<T> fission = new FissionSqlData<T>(new MainTableData<>(clazz));
        fission.setDataBaseType(this.getDataBaseType());
        this.getJoinTableDataMap().forEach((s, joinTableData) -> {
            // 排除目标主表, 防止自身关联自身
            if (joinTableData.getTableClass() != clazz) {
                fission.addJoinTableData(joinTableData);
            }
        });
        return fission;
    }*/

    /**
     * 分裂Sql数据
     *
     * @param <T>
     */
    /*private class FissionSqlData<T extends TableHelper> extends AbstractSqlDataCache<T> {

        FissionSqlData(MainTableData<T> mainTableData) {
            super(mainTableData);
        }


        @Override
        public Map<String, SqlBuilder> getSubQueryDataMap() {
            throw new SqlException("you can not use getSubQueryDataMap for class FissionSqlData.");
        }

        @Override
        public Set<TableFunctionColumnDatum> getTableFunctionColumnData() {
            throw new SqlException("you can not use getTableFunctionColumnData for class FissionSqlData.");
        }

        @Override
        public Set<VirtualFieldDatum> getVirtualFieldData() {
            throw new SqlException("you can not use getVirtualFieldData for class FissionSqlData.");
        }

        @Override
        public Set<TableColumnDatum> getTableColumnData() {
            throw new SqlException("you can not use getTableColumnData for class FissionSqlData.");
        }

        @Override
        public List<List<WhereDataLinker>> getWhereDataLinkerListList() {
            throw new SqlException("you can not use getWhereDataLinkerListList for class FissionSqlData.");
        }

        @Override
        public Set<TableGroupDatum> getTableGroupData() {
            throw new SqlException("you can not use getTableGroupData for class FissionSqlData.");
        }

        @Override
        public Set<TableSortDatum> getTableSortData() {
            throw new SqlException("you can not use getTableSortData for class FissionSqlData.");
        }


        @Override
        public LimitHandler getLimitData() {
            throw new SqlException("you can not use getLimitData for class FissionSqlData.");
        }


        @Override
        public SqlData<T> addTableColumnDatum(TableColumnDatum tableColumnDatum) {
            throw new SqlException("you can not use addTableColumnDatum for class FissionSqlData.");
        }

        @Override
        public SqlData<T> addVirtualFieldDatum(VirtualFieldDatum virtualFieldDatum) {
            throw new SqlException("you can not use addVirtualFieldDatum for class FissionSqlData.");
        }

        @Override
        public SqlData<T> addTableFunctionColumnDatum(TableFunctionColumnDatum tableFunctionColumnDatum) {
            throw new SqlException("you can not use addTableFunctionColumnDatum for class FissionSqlData.");
        }

        @Override
        public SqlData<T> addWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList) {
            throw new SqlException("you can not use addWhereDataLinkerList for class FissionSqlData.");
        }

        @Override
        public SqlData<T> addTableGroupDatum(TableGroupDatum tableGroupDatum) {
            throw new SqlException("you can not use addTableGroupDatum for class FissionSqlData.");
        }

        @Override
        public SqlData<T> addTableSortDatum(TableSortDatum tableSortDatum) {
            throw new SqlException("you can not use addTableSortDatum for class FissionSqlData.");
        }


        @Override
        public SqlData<T> setLimitData(LimitHandler limitData) {
            throw new SqlException("you can not use setLimitData for class FissionSqlData.");
        }

        @Override
        public SqlData<T> buildLimitData(Integer currentPage, Integer pageSize) {
            throw new SqlException("you can not use buildLimitData for class FissionSqlData.");
        }

        @Override
        public SqlData<T> buildLimitData(Integer total, Integer currentPage, Integer pageSize) {
            throw new SqlException("you can not use buildLimitData for class FissionSqlData.");
        }

        @Override
        public SqlData<T> setLimitStart(Integer limitStart) {
            throw new SqlException("you can not use setLimitStart for class FissionSqlData.");
        }

        @Override
        public SqlData<T> setLimitEnd(Integer limitEnd) {
            throw new SqlException("you can not use setLimitEnd for class FissionSqlData.");
        }

        @Override
        public SqlData<T> addSubQueryData(String alias, SqlBuilder sqlBuilder) {
            throw new SqlException("you can not use addSubQueryData for class FissionSqlData.");
        }
    }*/

}
