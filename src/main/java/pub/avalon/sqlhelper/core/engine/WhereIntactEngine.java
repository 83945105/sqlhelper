package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.WhereLinker;
import pub.avalon.sqlhelper.core.beans.WhereLinkerIntact;
import pub.avalon.sqlhelper.core.callback.WhereCallback;
import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.List;

/**
 * 条件引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereIntactEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends GroupIntactEngine<T, TO, TC, TW, TG, TS> {

    public WhereIntactEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public WhereIntactEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public WhereIntactEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public WhereIntactEngine<T, TO, TC, TW, TG, TS> where(WhereCallback<T, TO, TC, TW, TG, TS> callback) {
        if (callback == null) {
            return this;
        }
        MainTableData<T> mainTableData = this.sqlData.getMainTableData();
        TW tw = mainTableData.getTableModel().newWhereSqlModel();
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereLinkerIntact<>(), tw);
        List<WhereDataLinker> whereDataLinkerList = whereLinker.takeoutWhereDataLinkerList();
        this.sqlData.addWhereDataLinkerList(whereDataLinkerList);
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> WhereIntactEngine<T, TO, TC, TW, TG, TS> where(Class<S> conditionClass, String alias, WhereJoinCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        if (callback == null) {
            return this;
        }
        MainTableData<T> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<S> joinTableData = this.sqlData.getJoinTableData(alias, conditionClass);
        TW tw = mainTableData.getTableModel().newWhereSqlModel();
        SW sw = joinTableData.getTableModel().newWhereSqlModel();
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereLinkerIntact<>(), sw, tw);
        List<WhereDataLinker> whereDataLinkerList = whereLinker.takeoutWhereDataLinkerList();
        this.sqlData.addWhereDataLinkerList(whereDataLinkerList);
        return this;
    }

    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> WhereIntactEngine<T, TO, TC, TW, TG, TS> where(Class<S> conditionClass, WhereJoinCallback<T, TO, TC, TW, TG, TS, S, SO, SC, SW, SG, SS> callback) {
        return where(conditionClass, null, callback);
    }

}
