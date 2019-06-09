package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.SortCallback;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.SortDatum;
import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.Set;

/**
 * 排序引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SortIntactEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends LimitIntactEngine<T, TO, TC, TW, TG, TS> {

    public SortIntactEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public SortIntactEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public SortIntactEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public SortIntactEngine<T, TO, TC, TW, TG, TS> sort(SortHelper<?>... sortSqlModels) {
        if (sortSqlModels == null || sortSqlModels.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableModel类型
        return this;
    }

    public SortIntactEngine<T, TO, TC, TW, TG, TS> sort(SortCallback<TS> callback) {
        MainTableData<T> mainTableData = this.getSqlData().getMainTableData();
        TS ts = BeanUtils.tableModel(this.tableModelClass).newSortHelper();
        ts = callback.apply(ts);
        Set<SortDatum> sortData = ts.takeoutSqlModelData();
        this.addTableSortDatum(new TableSortDatum(mainTableData, sortData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SortIntactEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableModelClass, String alias, SortCallback<SS> callback) {
        JoinTableData<S> joinTableData = this.getSqlData().getJoinTableData(alias, tableModelClass);
        SS ss = BeanUtils.tableModel(tableModelClass).newSortHelper();
        ss = callback.apply(ss);
        Set<SortDatum> sortData = ss.takeoutSqlModelData();
        this.addTableSortDatum(new TableSortDatum(joinTableData, sortData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SortIntactEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableModelClass, SortCallback<SS> callback) {
        return sort(tableModelClass, null, callback);
    }

}
