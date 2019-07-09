package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalon.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.List;

/**
 * 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface WhereLinker<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

    /**
     * 取出条件数据连接器
     * 取出后清空
     *
     * @return {@link java.util.ArrayList}
     */
    List<WhereDataLinker> takeoutWhereDataLinkers();

    /**
     * 且
     *
     * @param whereHelper 条件助手
     * @return {@link WhereAndOr}
     */
    WhereAndOr<T, TO, TC, TW, TG, TS> and(WhereHelper<?> whereHelper);

    /**
     * 且
     *
     * @param callback 条件连接器回调
     * @return {@link pub.avalon.sqlhelper.core.callback.WhereCallback}
     */
    WhereAndOr<T, TO, TC, TW, TG, TS> and(WhereLinkerCallback<T, TO, TC, TW, TG, TS> callback);

    /**
     * 且
     *
     * @param tableHelperClass 目标条件类
     * @param tableAlias       目标条件别名
     * @param callback         条件连接器回调
     * @return {@link WhereAndOr}
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereAndOr<T, TO, TC, TW, TG, TS> and(Class<S> tableHelperClass,
                                                                             String tableAlias,
                                                                             WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback);

    /**
     * 且
     *
     * @param tableHelperClass 目标条件类
     * @param callback         条件连接器回调
     * @return {@link WhereAndOr}
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereAndOr<T, TO, TC, TW, TG, TS> and(Class<S> tableHelperClass,
                                                                             WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        return and(tableHelperClass, null, callback);
    }

}
