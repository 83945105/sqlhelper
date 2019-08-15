package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;

/**
 * @author baichao
 */
public final class FinalSqlData extends AbstractSqlData {

    public FinalSqlData(DataBaseType dataBaseType, MainTableDatum mainTableDatum) {
        super(mainTableDatum);
        this.setDataBaseType(dataBaseType);
    }
}