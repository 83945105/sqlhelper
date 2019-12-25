package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.core.beans.DataBaseType;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

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