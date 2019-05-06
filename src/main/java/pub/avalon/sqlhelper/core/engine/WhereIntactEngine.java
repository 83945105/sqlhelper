package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.norm.JoinCondition;
import pub.avalon.sqlhelper.core.norm.MainCondition;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.sql.Delete;
import pub.avalon.sqlhelper.core.sql.Update;

import java.util.List;

/**
 * 条件引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereIntactEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends GroupIntactEngine<M, MC, MO, MW, MS, MG> implements Update, Delete {

    WhereIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    WhereIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    WhereIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    //TODO 优化
    public WhereIntactEngine<M, MC, MO, MW, MS, MG> where(Where<M> where) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.whereDataBuilder.setOwnerTableData(mainTableData);
        mw.setSqlData(this.sqlData);


        return this;
    }

    public WhereIntactEngine<M, MC, MO, MW, MS, MG> where(MainCondition<M, MC, MO, MW, MS, MG> condition) {
        if (condition == null) {
            return this;
        }
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.whereDataBuilder.setOwnerTableData(mainTableData);
        mw.setSqlData(this.sqlData);
        WhereLinker<M, MC, MO, MW, MS, MG> whereLinker = condition.apply(new WhereLinkerIntact<>(this.sqlData), mw);
        List<WhereDataLinker> whereDataLinkerList = whereLinker.getAndResetWhereDataLinkerList();
        this.sqlData.addWhereDataLinkerList(whereDataLinkerList);
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> WhereIntactEngine<M, MC, MO, MW, MS, MG> where(Class<T> conditionClass, String alias, JoinCondition<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> condition) {
        if (condition == null) {
            return this;
        }
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.setSqlData(this.sqlData);
        mw.whereDataBuilder.setOwnerTableData(mainTableData);
        TW tw = joinTableData.getTableModel().getWhereModel();
        tw.setSqlData(this.sqlData.fission(joinTableData.getTableClass()));
        tw.whereDataBuilder.setOwnerTableData(joinTableData);
        WhereLinker<M, MC, MO, MW, MS, MG> whereLinker = condition.apply(new WhereLinkerIntact<>(this.sqlData), tw, mw);
        List<WhereDataLinker> whereDataLinkerList = whereLinker.getAndResetWhereDataLinkerList();
        this.sqlData.addWhereDataLinkerList(whereDataLinkerList);
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> WhereIntactEngine<M, MC, MO, MW, MS, MG> where(Class<T> conditionClass, JoinCondition<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> condition) {
        return where(conditionClass, null, condition);
    }

    @Override
    public SqlBuilder updateJavaBean(Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBean(javaBean);
    }

    @Override
    public SqlBuilder updateJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderProxy.updateJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder delete() {
        return this.sqlBuilderProxy.delete();
    }
}
