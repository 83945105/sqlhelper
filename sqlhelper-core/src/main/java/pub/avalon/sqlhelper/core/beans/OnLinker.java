package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.helper.OnHelper;

import java.util.List;

/**
 * @author baichao
 */
public interface OnLinker<TO extends OnHelper<TO>, SO extends OnHelper<SO>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link OnDataLinker}
     */
    List<OnDataLinker> takeoutOnDataLinkers();

    /**
     * and
     *
     * @param onHelper {@link OnHelper}
     * @return {@link OnAndOr}
     */
    OnAndOr<TO, SO> and(OnHelper<?> onHelper);

    /**
     * and
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOr}
     */
    OnAndOr<TO, SO> and(OnLinkerCallback<TO, SO> onLinkerCallback);
}