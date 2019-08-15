package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.exception.TableDataException;

import java.util.LinkedHashMap;

/**
 * @author baichao
 */
public abstract class AbstractSqlDataCache implements SqlData {

    private DataBaseType dataBaseType;

    private MainTableDatum mainTableDatum;

    public AbstractSqlDataCache(MainTableDatum mainTableDatum) {
        this.mainTableDatum = mainTableDatum;
    }

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
}