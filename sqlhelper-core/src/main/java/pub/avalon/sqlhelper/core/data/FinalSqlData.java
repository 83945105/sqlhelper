package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class FinalSqlData extends AbstractSqlData {

    public FinalSqlData(DataBaseType dataBaseType, MainTableDatum mainTableDatum, SqlBuilderOptions sqlBuilderOptions) {
        super(mainTableDatum);
        this.setSqlBuilderOptions(sqlBuilderOptions);
        this.setDataBaseType(dataBaseType);
    }
}