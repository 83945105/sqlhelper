package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.HavingLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.HavingDataLinker;
import pub.avalonframework.sqlhelper.core.data.HavingDatum;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class HavingAndOr<TH extends HavingHelper<TH>> implements HavingLinker<TH> {

    private List<HavingDataLinker> havingDataLinkers = new ArrayList<>();

    @Override
    public List<HavingDataLinker> takeoutHavingDataLinkers() {
        List<HavingDataLinker> havingDataLinkers = this.havingDataLinkers;
        this.havingDataLinkers = new ArrayList<>();
        return havingDataLinkers;
    }

    @Override
    public HavingAndOr<TH> and(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        HavingDataLinker havingDataLinker = new HavingDataLinker(LinkType.AND);
        List<HavingDatum> havingData = havingHelper.takeoutSqlPartData();
        if (havingData == null || havingData.size() == 0) {
            return this;
        }
        havingDataLinker.setHavingData(havingData);
        this.havingDataLinkers.add(havingDataLinker);
        return this;
    }

    @Override
    public HavingAndOr<TH> and(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingLinker<TH> havingLinker = havingLinkerCallback.apply(new HavingAndOr<>());
        List<HavingDataLinker> havingDataLinkers = havingLinker.takeoutHavingDataLinkers();
        if (havingDataLinkers == null || havingDataLinkers.size() == 0) {
            return this;
        }
        HavingDataLinker havingDataLinker = new HavingDataLinker(LinkType.AND);
        havingDataLinker.setHavingDataLinkers(havingDataLinkers);
        this.havingDataLinkers.add(havingDataLinker);
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
        HavingDataLinker havingDataLinker = new HavingDataLinker(LinkType.OR);
        List<HavingDatum> havingData = havingHelper.takeoutSqlPartData();
        if (havingData == null || havingData.size() == 0) {
            return this;
        }
        havingDataLinker.setHavingData(havingData);
        this.havingDataLinkers.add(havingDataLinker);
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
        List<HavingDataLinker> havingDataLinkers = havingLinker.takeoutHavingDataLinkers();
        if (havingDataLinkers == null || havingDataLinkers.size() == 0) {
            return this;
        }
        HavingDataLinker havingDataLinker = new HavingDataLinker(LinkType.OR);
        havingDataLinker.setHavingDataLinkers(havingDataLinkers);
        this.havingDataLinkers.add(havingDataLinker);
        return this;
    }
}