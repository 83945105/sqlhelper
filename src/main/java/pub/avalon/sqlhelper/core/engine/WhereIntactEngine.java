package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
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
public class WhereIntactEngine<M extends Model<M, ML, MO, MW, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MW, MS, MG>,
        MO extends OnModel<M, ML, MO, MW, MS, MG>,
        MW extends WhereModel<M, ML, MO, MW, MS, MG>,
        MS extends SortModel<M, ML, MO, MW, MS, MG>,
        MG extends GroupModel<M, ML, MO, MW, MS, MG>> extends GroupIntactEngine<M, ML, MO, MW, MS, MG> implements Update, Delete {

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
    public WhereIntactEngine<M, ML, MO, MW, MS, MG> where(Where<M> where) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.getWhereBuilder().setOwnerTableData(mainTableData);
        mw.setSqlData(this.sqlData);


        return this;
    }

    public WhereIntactEngine<M, ML, MO, MW, MS, MG> where(MainCondition<M, ML, MO, MW, MS, MG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.getWhereBuilder().setOwnerTableData(mainTableData);
        mw.setSqlData(this.sqlData);
        WhereLink<M, ML, MO, MW, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), mw);
        List<WhereDataLinker> whereDataLinkerList = whereLink.getAndResetWhereDataLinkerList();
        this.sqlData.addWhereDataLinkerList(whereDataLinkerList);
        return this;
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> WhereIntactEngine<M, ML, MO, MW, MS, MG> where(Class<T> conditionClass, String alias, JoinCondition<M, ML, MO, MW, MS, MG, T, TL, TO, TW, TS, TG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.setSqlData(this.sqlData);
        mw.getWhereBuilder().setOwnerTableData(mainTableData);
        TW tw = joinTableData.getTableModel().getWhereModel();
        tw.setSqlData(this.sqlData.fission(joinTableData.getTableClass()));
        tw.getWhereBuilder().setOwnerTableData(joinTableData);
        WhereLink<M, ML, MO, MW, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), tw, mw);
        List<WhereDataLinker> whereDataLinkerList = whereLink.getAndResetWhereDataLinkerList();
        this.sqlData.addWhereDataLinkerList(whereDataLinkerList);
        return this;
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> WhereIntactEngine<M, ML, MO, MW, MS, MG> where(Class<T> conditionClass, JoinCondition<M, ML, MO, MW, MS, MG, T, TL, TO, TW, TS, TG> condition) {
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
