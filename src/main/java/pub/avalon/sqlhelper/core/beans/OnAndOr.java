package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * and or On条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class OnAndOr<T extends TableHelper<T, TO, TC, TW, TG, TS>,
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
        SS extends SortHelper<SS>> implements OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> {

    private List<OnDataLinker> onDataLinkers = new ArrayList<>();

    @Override
    public List<OnDataLinker> takeoutOnDataLinkers() {
        List<OnDataLinker> onDataLinkers = this.onDataLinkers;
        this.onDataLinkers = new ArrayList<>();
        return onDataLinkers;
    }

    @Override
    public OnAndOr<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> and(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.AND);
        Set<OnDatum> onData = onHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setOnData(onData);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

    @Override
    public OnAndOr<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> and(OnLinkerCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> onLinker = callback.apply(new OnAndOr<>());
        List<OnDataLinker> onDataLinkerList = onLinker.takeoutOnDataLinkers();
        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.AND);
        onDataLinker.setOnDataLinkerList(onDataLinkerList);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param onHelper On助手
     * @return {@link OnAndOr}
     */
    public OnAndOr<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> or(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        Set<OnDatum> onData = onHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setOnData(onData);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param callback On条件连接器回调
     * @return {@link OnAndOr}
     */
    public OnAndOr<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> or(OnLinkerCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> onLinker = callback.apply(new OnAndOr<>());
        List<OnDataLinker> onDataLinkers = onLinker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        onDataLinker.setOnDataLinkerList(onDataLinkers);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

}