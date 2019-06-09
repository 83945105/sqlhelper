package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * On条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class OnLinker<T extends TableHelper<T, TO, TC, TW, TG, TS>,
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
        SS extends SortHelper<SS>> {

    protected List<OnDataLinker> onDataLinkerList = new ArrayList<>();

    public List<OnDataLinker> takeoutOnDataLinkerList() {
        List<OnDataLinker> list = this.onDataLinkerList;
        this.onDataLinkerList = new ArrayList<>();
        return list;
    }

    /**
     * 且条件
     *
     * @param onSqlModel On模组
     * @return On条件连接器 {@link OnLinkerIntact}
     */
    public OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> and(OnHelper<?> onSqlModel) {
        if (onSqlModel == null) {
            return (OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS>) this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.AND);
        Set<OnDatum> onData = onSqlModel.takeoutSqlModelData();
        if (onData == null || onData.size() == 0) {
            return (OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS>) this;
        }
        onDataLinker.setOnData(onData);
        this.onDataLinkerList.add(onDataLinker);
        return (OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS>) this;
    }

    /**
     * 且条件
     *
     * @param callback on处理
     * @return On条件连接器 {@link OnLinkerIntact}
     */
    public OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> and(OnLinkerCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> onLinker = callback.apply(new OnLinkerIntact<>());
        List<OnDataLinker> onDataLinkerList = onLinker.takeoutOnDataLinkerList();
        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return (OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS>) this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.AND);
        onDataLinker.setOnDataLinkerList(onDataLinkerList);
        this.onDataLinkerList.add(onDataLinker);
        return (OnLinkerIntact<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS>) this;
    }

}
