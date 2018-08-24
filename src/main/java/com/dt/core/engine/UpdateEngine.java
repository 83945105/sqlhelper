package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.build.SqlBuilder;
import com.dt.core.norm.Model;
import com.dt.core.sql.UpdateByPrimaryKey;

import java.util.Collection;

/**
 * 更新引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public final class UpdateEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends JoinIntactEngine<M, ML, MO, MC, MS, MG> implements UpdateByPrimaryKey<SqlBuilder> {

    public UpdateEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public UpdateEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Object[] args) {
        return this.sqlBuilderProxy.updateArgsByPrimaryKey(keyValue, args);
    }

    @Override
    public SqlBuilder updateArgsByPrimaryKey(Object keyValue, Collection<?> args) {
        return this.sqlBuilderProxy.updateArgsByPrimaryKey(keyValue, args);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKey(Object keyValue, Object record) {
        return this.sqlBuilderProxy.updateJavaBeanByPrimaryKey(keyValue, record);
    }

    @Override
    public SqlBuilder updateJavaBeanByPrimaryKeySelective(Object keyValue, Object record) {
        return this.sqlBuilderProxy.updateJavaBeanByPrimaryKeySelective(keyValue, record);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Object[] records) {
        return this.sqlBuilderProxy.batchUpdateJavaBeansByPrimaryKeys(records);
    }

    @Override
    public SqlBuilder batchUpdateJavaBeansByPrimaryKeys(Collection<?> records) {
        return this.sqlBuilderProxy.batchUpdateJavaBeansByPrimaryKeys(records);
    }
}
