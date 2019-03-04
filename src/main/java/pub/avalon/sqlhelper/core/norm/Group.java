package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * 分组处理
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface Group<T extends Model<T, TL, TO, TW, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
        TO extends OnModel<T, TL, TO, TW, TS, TG>,
        TW extends WhereModel<T, TL, TO, TW, TS, TG>,
        TS extends SortModel<T, TL, TO, TW, TS, TG>,
        TG extends GroupModel<T, TL, TO, TW, TS, TG>> {

    /**
     * 接收处理分组模型
     *
     * @param table 分组模型
     * @return 分组模型
     */
    TG apply(TG table);

}
