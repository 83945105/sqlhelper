package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.JoinHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class OnAndOr<TJ extends JoinHelper<TJ>, SJ extends JoinHelper<SJ>> implements OnLinker<TJ, SJ> {

    private List<OnDataLinker> onDataLinkers = new ArrayList<>();

    @Override
    public List<OnDataLinker> takeoutOnDataLinkers() {
        List<OnDataLinker> onDataLinkers = this.onDataLinkers;
        this.onDataLinkers = new ArrayList<>();
        return onDataLinkers;
    }

    @Override
    public OnAndOr<TJ, SJ> and(JoinHelper<?> joinHelper) {
        if (joinHelper == null) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.AND);
        List<OnDatum> onData = joinHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setOnData(onData);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

    @Override
    public OnAndOr<TJ, SJ> and(OnLinkerCallback<TJ, SJ> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TJ, SJ> onLinker = onLinkerCallback.apply(new OnAndOr<>());
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
     * @param joinHelper {@link JoinHelper}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TJ, SJ> or(JoinHelper<?> joinHelper) {
        if (joinHelper == null) {
            return this;
        }
        OnDataLinker onDataLinker = new OnDataLinker(LinkType.OR);
        List<OnDatum> onData = joinHelper.takeoutSqlPartData();
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
    public OnAndOr<TJ, SJ> or(OnLinkerCallback<TJ, SJ> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TJ, SJ> onLinker = onLinkerCallback.apply(new OnAndOr<>());
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
