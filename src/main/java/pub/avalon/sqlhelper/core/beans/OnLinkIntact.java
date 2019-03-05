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
public final class OnLinkIntact<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>,
        T extends Model<T, TC, TO, TW, TS, TG>,
        TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
        TO extends OnModel<T, TC, TO, TW, TS, TG>,
        TW extends WhereModel<T, TC, TO, TW, TS, TG>,
        TS extends SortModel<T, TC, TO, TW, TS, TG>,
        TG extends GroupModel<T, TC, TO, TW, TS, TG>> extends OnLink<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> {

    public OnLinkIntact(SqlData<M> sqlData, Class<T> joinClass, String alias) {
        super(sqlData, joinClass, alias);
    }

    /**
     * 或条件
     *
     * @param onModel On模组
     * @return On条件连接器 {@link OnLinkIntact}
     */
    public OnLinkIntact<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> or(OnModel<T, TC, TO, TW, TS, TG> onModel) {
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
    public OnLinkIntact<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> or(On<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> on) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MO mo = mainTableData.getTableModel().getOnModel();
        mo.onBuilder.setOwnerTableData(mainTableData);
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(this.alias, this.joinClass);
        TO to = joinTableData.getTableModel().getOnModel();
        to.onBuilder.setOwnerTableData(joinTableData);
        OnLink<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> onLink = on.apply(new OnLinkIntact<>(this.sqlData, this.joinClass, this.alias), to, mo);
        List<OnDataLinker> onDataLinkerList = onLink.getAndResetOnDataLinkerList();
        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        onDataLinker.setOnDataLinkerList(onDataLinkerList);
        this.onDataLinkerList.add(onDataLinker);
        return this;
    }

}
