package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.sql.DeleteByPrimaryKey;

import java.util.Collection;

/**
 * 删除引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public class DeleteEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends JoinIntactEngine<M, MC, MO, MW, MS, MG> implements DeleteByPrimaryKey {

    public DeleteEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public DeleteEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    public DeleteEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object keyValue) {
        return this.sqlBuilderProxy.deleteByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Collection<?> keyValues) {
        return this.sqlBuilderProxy.batchDeleteByPrimaryKeys(keyValues);
    }

}
