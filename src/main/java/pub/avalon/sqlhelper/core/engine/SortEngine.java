package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.SortCallback;
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
public class SortEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends LimitEngine<T, TO, TC, TW, TG, TS> {

    public SortEngine(Class<T> tableHelperClass) {
        super(tableHelperClass);
    }

    public SortEngine(String tableName, Class<T> tableHelperClass) {
        super(tableName, tableHelperClass);
    }

    public SortEngine(String tableName, Class<T> tableHelperClass, String alias) {
        super(tableName, tableHelperClass, alias);
    }

    public SortEngine<T, TO, TC, TW, TG, TS> sort(SortHelper<?>... sortSqlModels) {
        if (sortSqlModels == null || sortSqlModels.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableModel类型
        return this;
    }

    public SortEngine<T, TO, TC, TW, TG, TS> sort(SortCallback<TS> callback) {
        TS ts = BeanUtils.tableHelper(this.tableHelperClass).newSortHelper();
        ts = callback.apply(ts);
        Set<SortDatum> sortData = ts.takeoutSqlModelData();
        if (sortData == null || sortData.size() == 0) {
            return this;
        }
        this.addTableSortDatum(new TableSortDatum<>(this.tableHelperClass, this.tableAlias, sortData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SortEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> callback) {
        SS ss = BeanUtils.tableHelper(tableHelperClass).newSortHelper();
        ss = callback.apply(ss);
        Set<SortDatum> sortData = ss.takeoutSqlModelData();
        if (sortData == null || sortData.size() == 0) {
            return this;
        }
        this.addTableSortDatum(new TableSortDatum<>(tableHelperClass, tableAlias, sortData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> SortEngine<T, TO, TC, TW, TG, TS> sort(Class<S> tableHelperClass, SortCallback<SS> callback) {
        return sort(tableHelperClass, null, callback);
    }

}
