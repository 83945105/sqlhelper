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
public final class OnAndOr<TO extends OnHelper<TO>,
        S extends TableHelper<S, SO, SC, SW, SG, SS>,
        SO extends OnHelper<SO>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SS extends SortHelper<SS>> implements OnLinker<TO, S, SO, SC, SW, SG, SS> {

    private List<OnDataLinker> onDataLinkers = new ArrayList<>();

    @Override
    public List<OnDataLinker> takeoutOnDataLinkers() {
        List<OnDataLinker> onDataLinkers = this.onDataLinkers;
        this.onDataLinkers = new ArrayList<>();
        return onDataLinkers;
    }

    @Override
    public OnAndOr<TO, S, SO, SC, SW, SG, SS> and(OnHelper<?> onHelper) {
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
    public OnAndOr<TO, S, SO, SC, SW, SG, SS> and(OnLinkerCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        OnLinker<TO, S, SO, SC, SW, SG, SS> onLinker = callback.apply(new OnAndOr<>());
        List<OnDataLinker> onDataLinkers = onLinker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.AND);
        onDataLinker.setOnDataLinkers(onDataLinkers);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param onHelper On助手
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO, S, SO, SC, SW, SG, SS> or(OnHelper<?> onHelper) {
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
    public OnAndOr<TO, S, SO, SC, SW, SG, SS> or(OnLinkerCallback<TO, S, SO, SC, SW, SG, SS> callback) {
        OnLinker<TO, S, SO, SC, SW, SG, SS> onLinker = callback.apply(new OnAndOr<>());
        List<OnDataLinker> onDataLinkers = onLinker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        onDataLinker.setOnDataLinkers(onDataLinkers);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

}
