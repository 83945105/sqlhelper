package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.beans.OnLinkerIntact;
import pub.avalon.sqlhelper.core.callback.OnCallback;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * 连接引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class JoinIntactEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends ColumnIntactEngine<T, TO, TC, TW, TG, TS> {

    public JoinIntactEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public JoinIntactEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public JoinIntactEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableModelClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        JoinTableData<S> joinTableData = new JoinTableData<>(tableModelClass);
        joinTableData.setTableName(tableName);
        joinTableData.setTableAlias(alias);
        joinTableData.setJoinType(joinType);
        this.addJoinTableData(joinTableData);
        TO to = BeanUtils.tableModel(this.tableModelClass).newOnSqlModel();
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> onLinker = new OnLinkerIntact<>();
        SO so = BeanUtils.tableModel(tableModelClass).newOnSqlModel();
        OnLinker<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> linker = callback.apply(onLinker, so, to);
        joinTableData.addOnDataLinkerList(linker.takeoutOnDataLinkerList());
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, String tableName, Class<S> tableModelClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, tableName, tableModelClass, null, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, Class<S> tableModelClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, null, tableModelClass, alias, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> join(JoinType joinType, Class<S> tableModelClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(joinType, null, tableModelClass, null, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> innerJoin(String tableName, Class<S> tableModelClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, tableName, tableModelClass, alias, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> innerJoin(String tableName, Class<S> tableModelClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, tableName, tableModelClass, null, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> innerJoin(Class<S> tableModelClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, null, tableModelClass, alias, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> innerJoin(Class<S> tableModelClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.INNER, null, tableModelClass, null, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> leftJoin(String tableName, Class<S> tableModelClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, tableName, tableModelClass, alias, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> leftJoin(String tableName, Class<S> tableModelClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, tableName, tableModelClass, null, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> leftJoin(Class<S> tableModelClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, null, tableModelClass, alias, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> leftJoin(Class<S> tableModelClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.LEFT, null, tableModelClass, null, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> rightJoin(String tableName, Class<S> tableModelClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, tableName, tableModelClass, alias, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> rightJoin(String tableName, Class<S> tableModelClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, tableName, tableModelClass, null, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> rightJoin(Class<S> tableModelClass, String alias, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, null, tableModelClass, alias, callback);
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> JoinIntactEngine<T, TO, TC, TW, TG, TS> rightJoin(Class<S> tableModelClass, OnCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return join(JoinType.RIGHT, null, tableModelClass, null, callback);
    }

}
