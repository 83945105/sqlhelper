package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.OnHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class OnAndOr<TO extends OnHelper<TO>, SO extends OnHelper<SO>> implements OnLinker<TO, SO> {

    private List<OnDataLinker> onDataLinkers = new ArrayList<>();

    @Override
    public List<OnDataLinker> takeoutOnDataLinkers() {
        List<OnDataLinker> onDataLinkers = this.onDataLinkers;
        this.onDataLinkers = new ArrayList<>();
        return onDataLinkers;
    }

    @Override
    public OnAndOr<TO, SO> and(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.AND);
        List<OnDatum> onData = onHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setOnData(onData);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

    @Override
    public OnAndOr<TO, SO> and(OnLinkerCallback<TO, SO> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TO, SO> onLinker = onLinkerCallback.apply(new OnAndOr<>());
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
     * or
     *
     * @param onHelper {@link OnHelper}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO, SO> or(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        List<OnDatum> onData = onHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setOnData(onData);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO, SO> or(OnLinkerCallback<TO, SO> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TO, SO> onLinker = onLinkerCallback.apply(new OnAndOr<>());
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