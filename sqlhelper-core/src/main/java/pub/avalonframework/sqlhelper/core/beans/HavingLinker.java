package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.HavingLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

import java.util.List;

/**
 * @author baichao
 */
public interface HavingLinker<TH extends HavingHelper<TH>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link ComparisonSqlPartDataLinker}
     */
    List<ComparisonSqlPartDataLinker> takeoutComparisonSqlPartDataLinkers();

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