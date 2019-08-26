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

    private LinkedHashMap<String, JoinTableDatum> aliasJoinTableData;

    public AbstractSqlDataCache(MainTableDatum mainTableDatum) {
        this.mainTableDatum = mainTableDatum;
    }

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
        return this.aliasJoinTableData;
    }

    @Override
    public void setDataBaseType(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    @Override
    public void addJoinTableDatum(JoinTableDatum joinTableDatum) {
        if (this.aliasJoinTableData == null) {
            this.aliasJoinTableData = new LinkedHashMap<>();
        }
        if (this.aliasJoinTableData.get(joinTableDatum.getTableAlias()) != null) {
            // 同一个表别名不能使用2次
            throw new TableDataException("alias table [" + joinTableDatum.getTableAlias() + "] is already join, you can not join it two times, please change another alias.");
        }
        this.aliasJoinTableData.put(joinTableDatum.getTableAlias(), joinTableDatum);
    }
}