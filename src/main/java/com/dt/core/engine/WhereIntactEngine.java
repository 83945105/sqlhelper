package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.build.SqlBuilder;
import com.dt.core.data.JoinTableData;
import com.dt.core.data.LinkWhereData;
import com.dt.core.data.MainTableData;
import com.dt.core.norm.ConditionA;
import com.dt.core.norm.ConditionB;
import com.dt.core.norm.Model;
import com.dt.core.sql.Delete;
import com.dt.core.sql.Update;

import java.util.Collection;
import java.util.List;

/**
 * 条件引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereIntactEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends GroupIntactEngine<M, ML, MO, MC, MS, MG> implements Update<SqlBuilder>, Delete<SqlBuilder> {

    WhereIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    WhereIntactEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    @SuppressWarnings("unchecked")
    public WhereIntactEngine<M, ML, MO, MC, MS, MG> where(ConditionA<M, ML, MO, MC, MS, MG> condition) {
        MainTableData mainTableData = this.sqlData.getMainTableData();
        MC mc = (MC) mainTableData.getTableModel().getWhereModel();
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        WhereLink<M, ML, MO, MC, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), mc);
        List<LinkWhereData> linkWhereDataList = whereLink.getLinkWhereDataList();
        mainTableData.addLinkWhereDataList(linkWhereDataList);
        this.sqlData.addLinkWhereDataList(linkWhereDataList);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> WhereIntactEngine<M, ML, MO, MC, MS, MG> where(Class<T> conditionClass, String alias, ConditionB<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> condition) {
        MainTableData mainTableData = this.sqlData.getMainTableData();
        JoinTableData joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MC mc = (MC) mainTableData.getTableModel().getWhereModel();
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        TC tc = (TC) joinTableData.getTableModel().getWhereModel();
        tc.getWhereBuilder().setOwnerTableData(joinTableData);
        WhereLink<M, ML, MO, MC, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), tc, mc);
        List<LinkWhereData> linkWhereDataList = whereLink.getLinkWhereDataList();
        joinTableData.addLinkWhereDataList(linkWhereDataList);
        this.sqlData.addLinkWhereDataList(linkWhereDataList);
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> WhereIntactEngine<M, ML, MO, MC, MS, MG> where(Class<T> conditionClass, ConditionB<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> condition) {
        return where(conditionClass, null, condition);
    }

    @Override
    public SqlBuilder updateJavaBean(Object record) {
        return this.sqlBuilderProxy.updateJavaBean(record);
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object record) {
        return this.sqlBuilderProxy.updateJavaBeanSelective(record);
    }

    @Override
    public SqlBuilder updateOrInsertArgs(Object[] batchArgs) {
        return this.sqlBuilderProxy.updateOrInsertArgs(batchArgs);
    }

    @Override
    public SqlBuilder updateOrInsertArgs(Collection<?> batchArgs) {
        return this.sqlBuilderProxy.updateOrInsertArgs(batchArgs);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Object[] records) {
        return this.sqlBuilderProxy.updateOrInsertJavaBeans(records);
    }

    @Override
    public SqlBuilder updateOrInsertJavaBeans(Collection<?> records) {
        return this.sqlBuilderProxy.updateOrInsertJavaBeans(records);
    }

    @Override
    public SqlBuilder delete() {
        return this.sqlBuilderProxy.delete();
    }
}
