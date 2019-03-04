package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.norm.JoinCondition;
import pub.avalon.sqlhelper.core.norm.MainCondition;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.List;

/**
 * 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereLinkIntact<M extends Model<M, ML, MO, MW, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MW, MS, MG>,
        MO extends OnModel<M, ML, MO, MW, MS, MG>,
        MW extends WhereModel<M, ML, MO, MW, MS, MG>,
        MS extends SortModel<M, ML, MO, MW, MS, MG>,
        MG extends GroupModel<M, ML, MO, MW, MS, MG>> extends WhereLink<M, ML, MO, MW, MS, MG> {

    public WhereLinkIntact(SqlData<M> sqlData) {
        super(sqlData);
    }

    /**
     * 或
     *
     * @param whereModel 目标条件连接器
     * @return 当前条件连接器
     */
    public WhereLinkIntact<M, ML, MO, MW, MS, MG> or(WhereModel<?, ?, ?, ?, ?, ?> whereModel) {
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        List<WhereData> whereDataList = whereModel.whereBuilder.getAndResetWhereDataList();
        if (whereDataList == null || whereDataList.size() == 0) {
            return this;
        }
        whereDataLinker.setWhereDataList(whereDataList);
        this.whereDataLinkerList.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param condition 条件
     * @return 当前条件连接器
     */
    public WhereLinkIntact<M, ML, MO, MW, MS, MG> or(MainCondition<M, ML, MO, MW, MS, MG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.getWhereBuilder().setOwnerTableData(mainTableData);
        WhereLink<M, ML, MO, MW, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), mw);
        List<WhereDataLinker> whereDataLinkerList = whereLink.getAndResetWhereDataLinkerList();
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
    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> WhereLinkIntact<M, ML, MO, MW, MS, MG> or(Class<T> conditionClass,
                                                                                                    String alias,
                                                                                                    JoinCondition<M, ML, MO, MW, MS, MG, T, TL, TO, TW, TS, TG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.getWhereBuilder().setOwnerTableData(mainTableData);
        TW tw = joinTableData.getTableModel().getWhereModel();
        tw.getWhereBuilder().setOwnerTableData(joinTableData);
        WhereLink<M, ML, MO, MW, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), tw, mw);
        List<WhereDataLinker> whereDataLinkerList = whereLink.getAndResetWhereDataLinkerList();
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
    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> WhereLinkIntact<M, ML, MO, MW, MS, MG> or(Class<T> conditionClass, JoinCondition<M, ML, MO, MW, MS, MG, T, TL, TO, TW, TS, TG> condition) {
        return or(conditionClass, null, condition);
    }

}
