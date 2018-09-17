package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.norm.ConditionA;
import pub.avalon.sqlhelper.core.norm.ConditionB;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.List;

/**
 * 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereLinkIntact<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends WhereLink<M, ML, MO, MC, MS, MG> {

    public WhereLinkIntact(SqlData sqlData) {
        super(sqlData);
    }

    /**
     * 或
     *
     * @param whereModel 目标条件连接器
     * @return 当前条件连接器
     */
    public WhereLinkIntact<M, ML, MO, MC, MS, MG> or(WhereModel<?, ?, ?, ?, ?, ?> whereModel) {
        LinkWhereData linkWhereData = new LinkWhereData(LinkType.OR);
        List<WhereData> whereDataList = whereModel.whereBuilder.getWhereDataList();
        if (whereDataList == null || whereDataList.size() == 0) {
            return this;
        }
        linkWhereData.setWhereDataList(whereDataList);
        this.linkWhereDataList.add(linkWhereData);
        return this;
    }

    /**
     * 或
     *
     * @param condition 条件
     * @return 当前条件连接器
     */
    @SuppressWarnings("unchecked")
    public WhereLinkIntact<M, ML, MO, MC, MS, MG> or(ConditionA<M, ML, MO, MC, MS, MG> condition) {
        MainTableData mainTableData = this.sqlData.getMainTableData();
        MC mc = (MC) mainTableData.getTableModel().getWhereModel();
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        WhereLink<M, ML, MO, MC, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), mc);
        LinkWhereData linkWhereData = new LinkWhereData(LinkType.OR);
        List<LinkWhereData> linkWhereDataList = whereLink.getLinkWhereDataList();
        if (linkWhereDataList == null || linkWhereDataList.size() == 0) {
            return this;
        }
        linkWhereData.setLinkWhereDataList(linkWhereDataList);
        this.linkWhereDataList.add(linkWhereData);
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
    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> WhereLinkIntact<M, ML, MO, MC, MS, MG> or(Class<T> conditionClass,
                                                                                                    String alias,
                                                                                                    ConditionB<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> condition) {
        MainTableData mainTableData = this.sqlData.getMainTableData();
        JoinTableData joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        MC mc = (MC) mainTableData.getTableModel().getWhereModel();
        mc.getWhereBuilder().setOwnerTableData(mainTableData);
        TC tc = (TC) joinTableData.getTableModel().getWhereModel();
        tc.getWhereBuilder().setOwnerTableData(joinTableData);
        WhereLink<M, ML, MO, MC, MS, MG> whereLink = condition.apply(new WhereLinkIntact<>(this.sqlData), tc, mc);
        LinkWhereData linkWhereData = new LinkWhereData(LinkType.OR);
        List<LinkWhereData> linkWhereDataList = whereLink.getLinkWhereDataList();
        if (linkWhereDataList == null || linkWhereDataList.size() == 0) {
            return this;
        }
        linkWhereData.setLinkWhereDataList(linkWhereDataList);
        this.linkWhereDataList.add(linkWhereData);
        return this;
    }

    /**
     * 或
     *
     * @param conditionClass 目标条件类
     * @param condition      条件
     * @return 当前条件连接器
     */
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> WhereLinkIntact<M, ML, MO, MC, MS, MG> or(Class<T> conditionClass, ConditionB<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> condition) {
        return or(conditionClass, null, condition);
    }

}
