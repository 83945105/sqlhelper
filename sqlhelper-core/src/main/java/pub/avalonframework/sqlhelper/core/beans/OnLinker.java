package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

import java.util.List;

/**
 * @author baichao
 */
public interface OnLinker<TO extends OnHelper<TO>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link ComparisonSqlPartDataLinker}
     */
    List<ComparisonSqlPartDataLinker> takeoutComparisonSqlPartDataLinkers();

    /**
     * and
     *
     * @param onHelper {@link OnHelper}
     * @return {@link OnAndOr}
     */
    OnAndOr<TO> and(OnHelper<?> onHelper);

    /**
     * and
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOr}
     */
    OnAndOr<TO> and(OnLinkerCallback<TO> onLinkerCallback);
}