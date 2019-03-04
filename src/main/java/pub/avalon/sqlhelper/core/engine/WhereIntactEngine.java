package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.data.FinalSqlData;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.LinkWhereData;
import pub.avalon.sqlhelper.core.data.MainTableData;
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
public class WhereIntactEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends GroupIntactEngine<M, ML, MO, MC, MS, MG> implements Update, Delete {

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
    public WhereIntactEngine<M, ML, MO, MC, MS, MG> where(Where<M> where) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MC mc = mainTableData.getTableModel().getWhereModel();
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        mc.setSqlData(this.sqlData);


        return this;
    }

    public WhereIntactEngine<M, ML, MO, MC, MS, MG> where(MainCondition<M, ML, MO, MC, MS, MG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MC mc = mainTableData.getTableModel().getWhereModel();
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        mc.setSqlData(this.sqlData);
        WhereLink<M, ML, MO, MC, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), mc);
        List<LinkWhereData> linkWhereDataList = whereLink.getLinkWhereDataList();
        this.sqlData.addLinkWhereDataList(linkWhereDataList);
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> WhereIntactEngine<M, ML, MO, MC, MS, MG> where(Class<T> conditionClass, String alias, JoinCondition<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MC mc = mainTableData.getTableModel().getWhereModel();
        mc.setSqlData(this.sqlData);
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        TC tc = joinTableData.getTableModel().getWhereModel();
        tc.setSqlData(this.sqlData.fission(joinTableData.getTableClass()));
        tc.getWhereBuilder().setOwnerTableData(joinTableData);
        WhereLink<M, ML, MO, MC, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), tc, mc);
        List<LinkWhereData> linkWhereDataList = whereLink.getLinkWhereDataList();
        this.sqlData.addLinkWhereDataList(linkWhereDataList);
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> WhereIntactEngine<M, ML, MO, MC, MS, MG> where(Class<T> conditionClass, JoinCondition<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> condition) {
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
