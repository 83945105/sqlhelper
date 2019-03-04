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
public class WhereLink<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

    protected SqlData sqlData;

    WhereLink(SqlData sqlData) {
        this.sqlData = sqlData;
    }

    protected List<LinkWhereData> linkWhereDataList = new ArrayList<>();

    public List<LinkWhereData> getLinkWhereDataList() {
        List<LinkWhereData> list = this.linkWhereDataList;
        /*
          每次取走后重置集合
         */
        this.linkWhereDataList = new ArrayList<>();
        return list;
    }

    /**
     * 且
     *
     * @param whereModel 目标条件连接器
     * @return 当前条件连接器
     */
    public WhereLinkIntact<M, ML, MO, MC, MS, MG> and(WhereModel<?, ?, ?, ?, ?, ?> whereModel) {
        LinkWhereData linkWhereData = new LinkWhereData(LinkType.AND);
        List<WhereData> whereDataList = whereModel.whereBuilder.getAndResetWhereDataList();
        if (whereDataList == null || whereDataList.size() == 0) {
            return (WhereLinkIntact<M, ML, MO, MC, MS, MG>) this;
        }
        linkWhereData.setWhereDataList(whereDataList);
        this.linkWhereDataList.add(linkWhereData);
        return (WhereLinkIntact<M, ML, MO, MC, MS, MG>) this;
    }

    /**
     * 且
     *
     * @param condition 条件
     * @return 当前条件连接器
     */
    public WhereLinkIntact<M, ML, MO, MC, MS, MG> and(MainCondition<M, ML, MO, MC, MS, MG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MC mc = (MC) mainTableData.getTableModel().getWhereModel();
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        WhereLink<M, ML, MO, MC, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), mc);
        List<LinkWhereData> linkWhereDataList = whereLink.getLinkWhereDataList();
        if (linkWhereDataList == null || linkWhereDataList.size() == 0) {
            return (WhereLinkIntact<M, ML, MO, MC, MS, MG>) this;
        }
        LinkWhereData linkWhereData = new LinkWhereData(LinkType.AND);
        linkWhereData.setLinkWhereDataList(linkWhereDataList);
        this.linkWhereDataList.add(linkWhereData);
        return (WhereLinkIntact<M, ML, MO, MC, MS, MG>) this;
    }

    /**
     * 且
     *
     * @param conditionClass 目标条件类
     * @param alias          目标条件别名
     * @param condition      条件
     * @return 当前条件连接器
     */
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> WhereLinkIntact<M, ML, MO, MC, MS, MG> and(Class<T> conditionClass,
                                                                                                     String alias,
                                                                                                     JoinCondition<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> condition) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MC mc = mainTableData.getTableModel().getWhereModel();
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        TC tc = joinTableData.getTableModel().getWhereModel();
        tc.getWhereBuilder().setOwnerTableData(joinTableData);
        WhereLink<M, ML, MO, MC, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), tc, mc);
        List<LinkWhereData> linkWhereDataList = whereLink.getLinkWhereDataList();
        if (linkWhereDataList == null || linkWhereDataList.size() == 0) {
            return (WhereLinkIntact<M, ML, MO, MC, MS, MG>) this;
        }
        LinkWhereData linkWhereData = new LinkWhereData(LinkType.AND);
        linkWhereData.setLinkWhereDataList(linkWhereDataList);
        this.linkWhereDataList.add(linkWhereData);
        return (WhereLinkIntact<M, ML, MO, MC, MS, MG>) this;
    }

    /**
     * @param conditionClass 目标条件类
     * @param condition      条件
     * @return 当前条件连接器
     */
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> WhereLinkIntact<M, ML, MO, MC, MS, MG> and(Class<T> conditionClass, JoinCondition<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> condition) {
        return and(conditionClass, null, condition);
    }

}
