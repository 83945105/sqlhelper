package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.exception.TableDataException;

import java.util.LinkedHashMap;

/**
 * 数据缓存
 *
 * @author 白超
 * @date 2019/3/4
 */
public abstract class AbstractSqlDataCache implements SqlData {

    /**
     * 数据库类型
     */
    private DataBaseType dataBaseType;
    /**
     * 主表数据
     */
    private MainTableDatum mainTableDatum;

    public AbstractSqlDataCache(MainTableDatum mainTableDatum) {
        this.mainTableDatum = mainTableDatum;
    }

    /**
     * key - 表别名
     * value - 关联表数据
     */
    private LinkedHashMap<String, JoinTableDatum> aliasJoinTableDataCache;

    @Override
    public DataBaseType getDataBaseType() {
        return this.dataBaseType;
    }

    @Override
    public MainTableDatum getMainTableDatum() {
        return this.mainTableDatum;
    }

    @Override
    public LinkedHashMap<String, JoinTableDatum> getAliasJoinTableData() {
        return this.aliasJoinTableDataCache;
    }

    @Override
    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    @Override
    public void addJoinTableDatum(JoinTableDatum joinTableDatum) {
        if (this.aliasJoinTableDataCache == null) {
            this.aliasJoinTableDataCache = new LinkedHashMap<>();
        }
        if (this.aliasJoinTableDataCache.get(joinTableDatum.getTableAlias()) != null) {
            // 同一个表别名不能使用2次
            throw new TableDataException("alias table [" + joinTableDatum.getTableAlias() + "] is already join, you can not join it two times, please change another alias.");
        }
        this.aliasJoinTableDataCache.put(joinTableDatum.getTableAlias(), joinTableDatum);
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

}
