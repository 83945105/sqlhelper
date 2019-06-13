package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.beans.OnLinkerIntact;
import pub.avalon.sqlhelper.core.callback.OnCallback;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.TableOnDatum;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.List;

/**
 * 连接引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class JoinEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends WhereEngine<T, TO, TC, TW, TG, TS> {

    public JoinEngine(Class<T> tableHelperClass) {
        super(tableHelperClass);
    }

    public JoinEngine(String tableName, Class<T> tableHelperClass) {
        super(tableName, tableHelperClass);
    }

    public JoinEngine(String tableName, Class<T> tableHelperClass, String alias) {
        super(tableName, tableHelperClass, alias);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        JoinTableData<S> joinTableData = new JoinTableData<>(tableHelperClass);
        joinTableData.setTableName(tableName);
        joinTableData.setTableAlias(tableAlias);
        joinTableData.setJoinType(joinType);
        this.addJoinTableData(joinTableData);
        TO to = BeanUtils.tableHelper(this.tableHelperClass).newOnHelper();
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> onLinker = new OnLinkerIntact<>();
        SO so = BeanUtils.tableHelper(tableHelperClass).newOnHelper();
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> linker = callback.apply(onLinker, so, to);
        List<OnDataLinker> onDataLinkers = linker.takeoutOnDataLinkerList();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return this;
        }
        joinTableData.setTableOnDatum(new TableOnDatum<>(tableHelperClass, tableAlias, onDataLinkers));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, tableName, tableHelperClass, null, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, null, tableHelperClass, alias, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, null, tableHelperClass, null, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> innerJoin(String tableName, Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, tableName, tableHelperClass, alias, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> innerJoin(String tableName, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, tableName, tableHelperClass, null, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> innerJoin(Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, null, tableHelperClass, alias, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> innerJoin(Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, null, tableHelperClass, null, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> leftJoin(String tableName, Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, alias, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> leftJoin(String tableName, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, null, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> leftJoin(Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, null, tableHelperClass, alias, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> leftJoin(Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, null, tableHelperClass, null, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> rightJoin(String tableName, Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, alias, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> rightJoin(String tableName, Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, null, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> rightJoin(Class<S> tableHelperClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, null, tableHelperClass, alias, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> JoinEngine<T, TO, TC, TW, TG, TS> rightJoin(Class<S> tableHelperClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, null, tableHelperClass, null, callback);
    }

}
