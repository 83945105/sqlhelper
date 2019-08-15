package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.helper.JoinHelper;

import java.util.List;

/**
 * @author baichao
 */
public interface OnLinker<TJ extends JoinHelper<TJ>, SJ extends JoinHelper<SJ>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link OnDataLinker}
     */
    List<OnDataLinker> takeoutOnDataLinkers();

    /**
     * and
     *
     * @param joinHelper {@link JoinHelper}
     * @return {@link OnAndOr}
     */
    OnAndOr<TJ, SJ> and(JoinHelper<?> joinHelper);

    /**
     * and
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOr}
     */
    OnAndOr<TJ, SJ> and(OnLinkerCallback<TJ, SJ> onLinkerCallback);
}