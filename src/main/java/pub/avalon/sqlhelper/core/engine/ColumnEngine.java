package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface ColumnEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

    TableEngine<T, TO, TC, TW, TG, TS> column(ColumnHelper<?>... columns);

    TableEngine<T, TO, TC, TW, TG, TS> column(ColumnCallback<TC> callback);

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> callback);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableHelperClass, ColumnCallback<SC> callback) {
        return column(tableHelperClass, null, callback);
    }

    TableEngine<T, TO, TC, TW, TG, TS> virtualColumn(Object value, String alias);

    TableEngine<T, TO, TC, TW, TG, TS> functionColumn(GroupType groupType, ColumnCallback<TC> callback);

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> functionColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> callback);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> functionColumn(Class<S> tableHelperClass, GroupType groupType, ColumnCallback<SC> callback) {
        return functionColumn(tableHelperClass, null, groupType, callback);
    }

    <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableHelperClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias);

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(tableName, tableHelperClass, null, callback, columnAlias);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> subQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(null, tableHelperClass, null, callback, columnAlias);
    }

    default <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> TableEngine<T, TO, TC, TW, TG, TS> subQuery(Class<S> tableHelperClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(null, tableHelperClass, alias, callback, columnAlias);
    }

}
