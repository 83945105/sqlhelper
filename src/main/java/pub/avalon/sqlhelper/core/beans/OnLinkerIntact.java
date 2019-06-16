package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.List;
import java.util.Set;

/**
 * On条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class OnLinkerIntact<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>,
        S extends TableHelper<S, SO, SC, SW, SG, SS>,
        SO extends OnHelper<SO>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SS extends SortHelper<SS>> extends OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> {

    /**
     * 或条件
     *
     * @param onSqlModel On模组
     * @return On条件连接器 {@link OnLinkerIntact}
     */
    public OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> or(OnHelper<?> onSqlModel) {
        if (onSqlModel == null) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        Set<OnDatum> onData = onSqlModel.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setOnData(onData);
        this.onDataLinkerList.add(onDataLinker);
        return this;
    }

    /**
     * 或条件
     *
     * @param callback on处理
     * @return On条件连接器 {@link OnLinkerIntact}
     */
    public OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> or(OnLinkerCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> onLinker = callback.apply(new OnLinkerIntact<>());
        List<OnDataLinker> onDataLinkerList = onLinker.takeoutOnDataLinkerList();
        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        onDataLinker.setOnDataLinkerList(onDataLinkerList);
        this.onDataLinkerList.add(onDataLinker);
        return this;
    }

}
