package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.HavingLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.HavingDataLinker;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

import java.util.List;

/**
 * @author baichao
 */
public interface HavingLinker<TH extends HavingHelper<TH>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link HavingDataLinker}
     */
    List<HavingDataLinker> takeoutHavingDataLinkers();

    /**
     * and
     *
     * @param havingHelper {@link HavingHelper}
     * @return {@link HavingAndOr}
     */
    HavingAndOr<TH> and(HavingHelper<?> havingHelper);

    /**
     * and
     *
     * @param havingLinkerCallback {@link HavingLinkerCallback}
     * @return {@link HavingAndOr}
     */
    HavingAndOr<TH> and(HavingLinkerCallback<TH> havingLinkerCallback);
}