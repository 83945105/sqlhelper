package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.norm.JoinCondition;
import pub.avalon.sqlhelper.core.norm.MainCondition;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereLink<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> {

    protected SqlData<M> sqlData;

    WhereLink(SqlData<M> sqlData) {
        this.sqlData = sqlData;
    }

    List<WhereDataLinker> whereDataLinkerList = new ArrayList<>();

    public List<WhereDataLinker> getAndResetWhereDataLinkerList() {
        List<WhereDataLinker> list = this.whereDataLinkerList;
        this.whereDataLinkerList = new ArrayList<>();
        return list;
    }

    /**
     * 且
     *
     * @param whereModel 目标条件连接器
     * @return 当前条件连接器
     */
    public WhereLinkIntact<M, MC, MO, MW, MS, MG> and(WhereModel<?, ?, ?, ?, ?, ?> whereModel) {
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        List<WhereData> whereDataList = whereModel.whereBuilder.getAndResetWhereDataList();
        if (whereDataList == null || whereDataList.size() == 0) {
            return (WhereLinkIntact<M, MC, MO, MW, MS, MG>) this;
        }
        whereDataLinker.setWhereDataList(whereDataList);
        this.whereDataLinkerList.add(whereDataLinker);
        return (WhereLinkIntact<M, MC, MO, MW, MS, MG>) this;
    }

    /**
     * 且
     *
     * @param condition 条件
     * @return 当前条件连接器
     */
    public WhereLinkIntact<M, MC, MO, MW, MS, MG> and(MainCondition<M, MC, MO, MW, MS, MG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.getWhereBuilder().setOwnerTableData(mainTableData);
        WhereLink<M, MC, MO, MW, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), mw);
        List<WhereDataLinker> whereDataLinkerList = whereLink.getAndResetWhereDataLinkerList();
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return (WhereLinkIntact<M, MC, MO, MW, MS, MG>) this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        whereDataLinker.setWhereDataLinkerList(whereDataLinkerList);
        this.whereDataLinkerList.add(whereDataLinker);
        return (WhereLinkIntact<M, MC, MO, MW, MS, MG>) this;
    }

    /**
     * 且
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
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> WhereLinkIntact<M, MC, MO, MW, MS, MG> and(Class<T> conditionClass,
                                                                                                     String alias,
                                                                                                     JoinCondition<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MW mw = mainTableData.getTableModel().getWhereModel();
        mw.getWhereBuilder().setOwnerTableData(mainTableData);
        TW tw = joinTableData.getTableModel().getWhereModel();
        tw.getWhereBuilder().setOwnerTableData(joinTableData);
        WhereLink<M, MC, MO, MW, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), tw, mw);
        List<WhereDataLinker> whereDataLinkerList = whereLink.getAndResetWhereDataLinkerList();
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return (WhereLinkIntact<M, MC, MO, MW, MS, MG>) this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        whereDataLinker.setWhereDataLinkerList(whereDataLinkerList);
        this.whereDataLinkerList.add(whereDataLinker);
        return (WhereLinkIntact<M, MC, MO, MW, MS, MG>) this;
    }

    /**
     * @param conditionClass 目标条件类
     * @param condition      条件
     * @return 当前条件连接器
     */
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> WhereLinkIntact<M, MC, MO, MW, MS, MG> and(Class<T> conditionClass, JoinCondition<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> condition) {
        return and(conditionClass, null, condition);
    }

}
