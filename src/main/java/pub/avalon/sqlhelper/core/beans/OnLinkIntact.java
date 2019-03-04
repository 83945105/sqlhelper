package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.norm.On;

import java.util.List;

/**
 * On条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class OnLinkIntact<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>,
        T extends Model<T, TL, TO, TC, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
        TO extends OnModel<T, TL, TO, TC, TS, TG>,
        TC extends WhereModel<T, TL, TO, TC, TS, TG>,
        TS extends SortModel<T, TL, TO, TC, TS, TG>,
        TG extends GroupModel<T, TL, TO, TC, TS, TG>> extends OnLink<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> {

    public OnLinkIntact(SqlData<M> sqlData, Class<T> joinClass, String alias) {
        super(sqlData, joinClass, alias);
    }

    /**
     * 或条件
     *
     * @param onModel On模组
     * @return On条件连接器 {@link OnLinkIntact}
     */
    public OnLinkIntact<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> or(OnModel<T, TL, TO, TC, TS, TG> onModel) {
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        List<OnData> onDataList = onModel.onBuilder.getAndResetOnDataList();
        if (onDataList == null || onDataList.size() == 0) {
            return this;
        }
        onDataLinker.setOnDataList(onDataList);
        this.onDataLinkerList.add(onDataLinker);
        return this;
    }

    /**
     * 或条件
     *
     * @param on on处理
     * @return On条件连接器 {@link OnLinkIntact}
     */
    public OnLinkIntact<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> or(On<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> on) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MO mo = mainTableData.getTableModel().getOnModel();
        mo.onBuilder.setOwnerTableData(mainTableData);
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(this.alias, this.joinClass);
        TO to = joinTableData.getTableModel().getOnModel();
        to.onBuilder.setOwnerTableData(joinTableData);
        OnLink onLink = on.apply(new OnLinkIntact<>(this.sqlData, this.joinClass, this.alias), to, mo);
        List<OnDataLinker> onDataLinkerList = onLink.getOnDataLinkerList();
        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        onDataLinker.setOnDataLinkerList(onDataLinkerList);
        this.onDataLinkerList.add(onDataLinker);
        return this;
    }

}
