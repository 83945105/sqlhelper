package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 新增引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public final class InsertEngine<M extends Model<M, ML, MO, MW, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MW, MS, MG>,
        MO extends OnModel<M, ML, MO, MW, MS, MG>,
        MW extends WhereModel<M, ML, MO, MW, MS, MG>,
        MS extends SortModel<M, ML, MO, MW, MS, MG>,
        MG extends GroupModel<M, ML, MO, MW, MS, MG>> extends ColumnEngine<M, ML, MO, MW, MS, MG> {

    public InsertEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public InsertEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    public InsertEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

}
