package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.SortData;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.norm.Sort;

import java.util.List;

/**
 * 排序引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SortIntactEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends LimitIntactEngine<M, MC, MO, MW, MS, MG> {

    SortIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    SortIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    SortIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    public SortIntactEngine<M, MC, MO, MW, MS, MG> sort(Sort<M, MC, MO, MW, MS, MG> sort) {
        if (sort == null) {
            return this;
        }
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MS ms = mainTableData.getTableModel().getSortModel();
        ms.getSortBuilder().setOwnerTableData(mainTableData);
        List<SortData> sortDataList = sort.apply(ms).getSortBuilder().getSortDataList();
        this.sqlData.addSortDataList(sortDataList);
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> SortIntactEngine<M, MC, MO, MW, MS, MG> sort(Class<T> sortClass, String alias, Sort<T, TC, TO, TW, TS, TG> sort) {
        if (sort == null) {
            return this;
        }
        JoinTableData<T> joinTableData = this.sqlData.getJoinTableData(alias, sortClass);
        TS ts = joinTableData.getTableModel().getSortModel();
        ts.getSortBuilder().setOwnerTableData(joinTableData);
        List<SortData> sortDataList = sort.apply(ts).getSortBuilder().getSortDataList();
        this.sqlData.addSortDataList(sortDataList);
        return this;
    }

    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> SortIntactEngine<M, MC, MO, MW, MS, MG> sort(Class<T> sortClass, Sort<T, TC, TO, TW, TS, TG> sort) {
        return sort(sortClass, null, sort);
    }

}
