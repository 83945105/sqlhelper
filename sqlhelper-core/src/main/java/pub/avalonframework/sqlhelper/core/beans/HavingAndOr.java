package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.HavingLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.data.HavingDatum;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class HavingAndOr<TH extends HavingHelper<TH>> implements HavingLinker<TH> {

    private List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = new ArrayList<>();

    @Override
    public List<ComparisonSqlPartDataLinker> takeoutComparisonSqlPartDataLinkers() {
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = this.comparisonSqlPartDataLinkers;
        this.comparisonSqlPartDataLinkers = new ArrayList<>();
        return comparisonSqlPartDataLinkers;
    }

    @Override
    public HavingAndOr<TH> and(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        ComparisonSqlPartDataLinker havingDataLinker = new ComparisonSqlPartDataLinker(LinkType.AND);
        List<HavingDatum> havingData = havingHelper.takeoutSqlPartData();
        if (havingData == null || havingData.size() == 0) {
            return this;
        }
        havingDataLinker.setComparisonSqlPartData(havingData);
        this.comparisonSqlPartDataLinkers.add(havingDataLinker);
        return this;
    }

    @Override
    public HavingAndOr<TH> and(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingLinker<TH> havingLinker = havingLinkerCallback.apply(new HavingAndOr<>());
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = havingLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonSqlPartDataLinker havingDataLinker = new ComparisonSqlPartDataLinker(LinkType.AND);
        havingDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(havingDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param havingHelper {@link HavingHelper}
     * @return {@link HavingAndOr}
     */
    public HavingAndOr<TH> or(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        ComparisonSqlPartDataLinker havingDataLinker = new ComparisonSqlPartDataLinker(LinkType.OR);
        List<HavingDatum> havingData = havingHelper.takeoutSqlPartData();
        if (havingData == null || havingData.size() == 0) {
            return this;
        }
        havingDataLinker.setComparisonSqlPartData(havingData);
        this.comparisonSqlPartDataLinkers.add(havingDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param havingLinkerCallback {@link HavingLinkerCallback}
     * @return {@link HavingAndOr}
     */
    public HavingAndOr<TH> or(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingLinker<TH> havingLinker = havingLinkerCallback.apply(new HavingAndOr<>());
        List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = havingLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonSqlPartDataLinker havingDataLinker = new ComparisonSqlPartDataLinker(LinkType.OR);
        havingDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(havingDataLinker);
        return this;
    }
}