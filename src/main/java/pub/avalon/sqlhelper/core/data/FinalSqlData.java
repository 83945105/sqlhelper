package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;

/**
 * @author 白超
 * @date 2018/8/20
 */
public final class FinalSqlData extends AbstractSqlData {

    public FinalSqlData(DataBaseType dataBaseType, MainTableDatum mainTableDatum) {
        super(mainTableDatum);
        this.setDataBaseType(dataBaseType);
    }

}
