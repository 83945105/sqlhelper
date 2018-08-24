package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.build.SqlBuilder;
import com.dt.core.norm.Model;
import com.dt.core.sql.DeleteByPrimaryKey;

import java.util.Collection;

/**
 * 删除引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public class DeleteEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends JoinIntactEngine<M, ML, MO, MC, MS, MG> implements DeleteByPrimaryKey<SqlBuilder> {

    public DeleteEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public DeleteEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    @Override
    public SqlBuilder deleteByPrimaryKey(Object keyValue) {
        return this.sqlBuilderProxy.deleteByPrimaryKey(keyValue);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Object[] keyValues) {
        return this.sqlBuilderProxy.batchDeleteByPrimaryKeys(keyValues);
    }

    @Override
    public SqlBuilder batchDeleteByPrimaryKeys(Collection<?> keyValues) {
        return this.sqlBuilderProxy.batchDeleteByPrimaryKeys(keyValues);
    }

}
