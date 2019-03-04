package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * 列处理
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface Column<T extends Model<T, TC, TO, TW, TS, TG>,
        TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
        TO extends OnModel<T, TC, TO, TW, TS, TG>,
        TW extends WhereModel<T, TC, TO, TW, TS, TG>,
        TS extends SortModel<T, TC, TO, TW, TS, TG>,
        TG extends GroupModel<T, TC, TO, TW, TS, TG>> {

    /**
     * 接收列模组
     *
     * @param table 列模组
     * @return 列模组
     */
    TC apply(TC table);

}
