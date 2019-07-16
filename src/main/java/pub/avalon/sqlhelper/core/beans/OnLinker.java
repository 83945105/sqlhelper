package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.List;

/**
 * On条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface OnLinker<TO extends OnHelper<TO>,
        S extends TableHelper<S, SO, SC, SW, SG, SS>,
        SO extends OnHelper<SO>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SS extends SortHelper<SS>> {

    /**
     * 取出On数据连接器
     * 取出后清空
     *
     * @return {@link java.util.ArrayList}
     */
    List<OnDataLinker> takeoutOnDataLinkers();

    /**
     * 且
     *
     * @param onHelper On助手
     * @return {@link OnAndOr}
     */
    OnAndOr<TO, S, SO, SC, SW, SG, SS> and(OnHelper<?> onHelper);

    /**
     * 且
     *
     * @param callback On条件连接器回调
     * @return On条件连接器 {@link OnAndOr}
     */
    OnAndOr<TO, S, SO, SC, SW, SG, SS> and(OnLinkerCallback<TO, S, SO, SC, SW, SG, SS> callback);

}
