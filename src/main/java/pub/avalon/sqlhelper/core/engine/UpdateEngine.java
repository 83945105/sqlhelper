package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 更新引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public final class UpdateEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends JoinIntactEngine<M, MC, MO, MW, MS, MG> {

    public UpdateEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public UpdateEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    public UpdateEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

}
