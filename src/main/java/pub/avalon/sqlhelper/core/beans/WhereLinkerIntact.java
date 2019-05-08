package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.norm.JoinCondition;
import pub.avalon.sqlhelper.core.norm.MainCondition;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.List;
import java.util.Set;

/**
 * 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereLinkerIntact<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends WhereLinker<M, MC, MO, MW, MS, MG> {

    public WhereLinkerIntact(SqlData<M> sqlData) {
        super(sqlData);
    }

    /**
     * 或
     *
     * @param whereModel 目标条件连接器
     * @return 当前条件连接器
     */
    public WhereLinkerIntact<M, MC, MO, MW, MS, MG> or(WhereModel<?, ?, ?, ?, ?, ?> whereModel) {
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        Set<WhereDatum> whereData = whereModel.modelDataBuilder.takeoutModelData();
        if (whereData == null || whereData.size() == 0) {
            return this;
        }
        whereDataLinker.setWhereData(whereData);
        this.whereDataLinkerList.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param condition 条件
     * @return 当前条件连接器
     */
    public WhereLinkerIntact<M, MC, MO, MW, MS, MG> or(MainCondition<M, MC, MO, MW, MS, MG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.modelDataBuilder.setOwnerTableData(mainTableData);
        WhereLinker<M, MC, MO, MW, MS, MG> whereLinker = condition.apply(new WhereLinkerIntact<>(this.sqlData), mw);
        List<WhereDataLinker> whereDataLinkerList = whereLinker.getAndResetWhereDataLinkerList();
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        whereDataLinker.setWhereDataLinkerList(whereDataLinkerList);
        this.whereDataLinkerList.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param conditionClass 目标条件类
     * @param alias          目标条件别名
     * @param condition      条件
     * @return 当前条件连接器
     */
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> WhereLinkerIntact<M, MC, MO, MW, MS, MG> or(Class<T> conditionClass,
                                                                                                      String alias,
                                                                                                      JoinCondition<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.modelDataBuilder.setOwnerTableData(mainTableData);
        TW tw = joinTableData.getTableModel().getWhereModel();
        tw.modelDataBuilder.setOwnerTableData(joinTableData);
        WhereLinker<M, MC, MO, MW, MS, MG> whereLinker = condition.apply(new WhereLinkerIntact<>(this.sqlData), tw, mw);
        List<WhereDataLinker> whereDataLinkerList = whereLinker.getAndResetWhereDataLinkerList();
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        whereDataLinker.setWhereDataLinkerList(whereDataLinkerList);
        this.whereDataLinkerList.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param conditionClass 目标条件类
     * @param condition      条件
     * @return 当前条件连接器
     */
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> WhereLinkerIntact<M, MC, MO, MW, MS, MG> or(Class<T> conditionClass, JoinCondition<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> condition) {
        return or(conditionClass, null, condition);
    }

}
