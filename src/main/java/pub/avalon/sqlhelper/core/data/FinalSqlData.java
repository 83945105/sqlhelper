package pub.avalon.sqlhelper.core.data;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * @author 白超
 * @date 2018/8/20
 */
public final class FinalSqlData<M extends Model> extends AbstractSqlData<M> {

    public FinalSqlData(DataBaseType dataBaseType) {
        super(dataBaseType);
    }
}
