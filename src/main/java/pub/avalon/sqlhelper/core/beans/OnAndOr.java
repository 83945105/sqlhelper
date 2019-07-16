package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.OnHelper;

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
        Set<OnDatum> onData = onHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setOnData(onData);
        this.onDataLinkers.add(onDataLinker);
        return this;
    }

    @Override
    public OnAndOr<TO, SO> and(OnLinkerCallback<TO, SO> callback) {
        OnLinker<TO, SO> onLinker = callback.apply(new OnAndOr<>());
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
    public OnAndOr<TO, SO> or(OnHelper<?> onHelper) {
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
    public OnAndOr<TO, SO> or(OnLinkerCallback<TO, SO> callback) {
        OnLinker<TO, SO> onLinker = callback.apply(new OnAndOr<>());
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
