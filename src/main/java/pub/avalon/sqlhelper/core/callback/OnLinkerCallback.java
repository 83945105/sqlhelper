package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface OnLinkerCallback<TO extends OnHelper<TO>,
        S extends TableHelper<S, SO, SC, SW, SG, SS>,
        SO extends OnHelper<SO>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SS extends SortHelper<SS>> {

    /**
     * 接收On条件连接器
     *
     * @param on {@link OnLinker}
     * @return {@link OnLinker}
     */
    OnLinker<TO, S, SO, SC, SW, SG, SS> apply(OnLinker<TO, S, SO, SC, SW, SG, SS> on);

}
