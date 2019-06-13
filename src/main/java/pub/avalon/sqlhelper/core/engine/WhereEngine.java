package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.WhereLinkerIntact;
import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.List;

/**
 * 条件引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends GroupEngine<T, TO, TC, TW, TG, TS> {

    public WhereEngine(Class<T> tableHelperClass) {
        super(tableHelperClass);
    }

    public WhereEngine(String tableName, Class<T> tableHelperClass) {
        super(tableName, tableHelperClass);
    }

    public WhereEngine(String tableName, Class<T> tableHelperClass, String alias) {
        super(tableName, tableHelperClass, alias);
    }

    public WhereEngine<T, TO, TC, TW, TG, TS> where(WhereCallback<T, TO, TC, TW, TG, TS> callback) {
        if (callback == null) {
            return this;
        }
        List<WhereDataLinker> whereDataLinkers = callback.apply(new WhereLinkerIntact<>(), BeanUtils.tableHelper(this.tableHelperClass).newWhereHelper()).takeoutWhereDataLinkerList();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        this.addTableWhereDatum(new TableWhereDatum<>(this.tableHelperClass, this.tableAlias, whereDataLinkers));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereEngine<T, TO, TC, TW, TG, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        if (callback == null) {
            return this;
        }
        List<WhereDataLinker> whereDataLinkers = callback.apply(new WhereLinkerIntact<>(), BeanUtils.tableHelper(tableHelperClass).newWhereHelper(), BeanUtils.tableHelper(this.tableHelperClass).newWhereHelper()).takeoutWhereDataLinkerList();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return this;
        }
        this.addTableWhereDatum(new TableWhereDatum<>(tableHelperClass, tableAlias, whereDataLinkers));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereEngine<T, TO, TC, TW, TG, TS> where(Class<S> tableHelperClass, WhereJoinCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        return where(tableHelperClass, null, callback);
    }

}
