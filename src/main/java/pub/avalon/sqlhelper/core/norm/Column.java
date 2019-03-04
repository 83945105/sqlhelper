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
public interface Column<T extends Model<T, TL, TO, TW, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
        TO extends OnModel<T, TL, TO, TW, TS, TG>,
        TW extends WhereModel<T, TL, TO, TW, TS, TG>,
        TS extends SortModel<T, TL, TO, TW, TS, TG>,
        TG extends GroupModel<T, TL, TO, TW, TS, TG>> {

    /**
     * 接收列模组
     *
     * @param table 列模组
     * @return 列模组
     */
    TL apply(TL table);

}
