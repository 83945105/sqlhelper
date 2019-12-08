package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.data.OnDatum;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class OnAndOr<TO extends OnHelper<TO>> implements OnLinker<TO> {

    private List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = new ArrayList<>();

    @Override
    public List<ComparisonSqlPartDataLinker> takeoutComparisonSqlPartDataLinkers() {
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = this.comparisonSqlPartDataLinkers;
        this.comparisonSqlPartDataLinkers = new ArrayList<>();
        return comparisonSqlPartDataLinkers;
    }

    @Override
    public OnAndOr<TO> and(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        ComparisonSqlPartDataLinker onDataLinker = new ComparisonSqlPartDataLinker(LinkType.AND);
        List<OnDatum> onData = onHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setComparisonSqlPartData(onData);
        this.comparisonSqlPartDataLinkers.add(onDataLinker);
        return this;
    }

    @Override
    public OnAndOr<TO> and(OnLinkerCallback<TO> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TO> onLinker = onLinkerCallback.apply(new OnAndOr<>());
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = onLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonSqlPartDataLinker onDataLinker = new ComparisonSqlPartDataLinker(LinkType.AND);
        onDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(onDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param onHelper {@link OnHelper}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO> or(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        ComparisonSqlPartDataLinker onDataLinker = new ComparisonSqlPartDataLinker(LinkType.OR);
        List<OnDatum> onData = onHelper.takeoutSqlPartData();
        if (onData == null || onData.size() == 0) {
            return this;
        }
        onDataLinker.setComparisonSqlPartData(onData);
        this.comparisonSqlPartDataLinkers.add(onDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO> or(OnLinkerCallback<TO> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TO> onLinker = onLinkerCallback.apply(new OnAndOr<>());
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = onLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonSqlPartDataLinker onDataLinker = new ComparisonSqlPartDataLinker(LinkType.OR);
        onDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(onDataLinker);
        return this;
    }
}