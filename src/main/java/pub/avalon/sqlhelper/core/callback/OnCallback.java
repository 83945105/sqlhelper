package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * on 回调
 *
 * @author 白超
 * @date 2019/5/16
 */
@FunctionalInterface
public interface OnCallback<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>,
        S extends TableModel<S, SO, SC, SW, SG, SS>,
        SO extends OnSqlModel<SO>,
        SC extends ColumnSqlModel<SC>,
        SW extends WhereSqlModel<SW>,
        SG extends GroupSqlModel<SG>,
        SS extends SortSqlModel<SS>> {

    /**
     * 接收 on sql模组
     *
     * @param on        连接方式
     * @param joinTable 连接表
     * @param mainTable 主表
     * @return
     */
    OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> apply(OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> on, SO joinTable, TO mainTable);

}
