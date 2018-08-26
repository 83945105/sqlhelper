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
public class SortIntactEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends LimitIntactEngine<M, ML, MO, MC, MS, MG> {

    SortIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    SortIntactEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    @SuppressWarnings("unchecked")
    public SortIntactEngine<M, ML, MO, MC, MS, MG> sort(Sort<M, ML, MO, MC, MS, MG> sort) {
        MainTableData mainTableData = this.sqlData.getMainTableData();
        MS ms = (MS) mainTableData.getTableModel().getSortModel();
        ms.getSortBuilder().setOwnerTableData(mainTableData);
        List<SortData> sortDataList = sort.apply(ms).getSortBuilder().getSortDataList();
        mainTableData.addSortDataList(sortDataList);
        this.sqlData.addSortDataList(sortDataList);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> SortIntactEngine<M, ML, MO, MC, MS, MG> sort(Class<T> sortClass, String alias, Sort<T, TL, TO, TC, TS, TG> sort) {
        JoinTableData joinTableData = this.sqlData.getJoinTableData(alias, sortClass);
        TS ts = (TS) joinTableData.getTableModel().getSortModel();
        ts.getSortBuilder().setOwnerTableData(joinTableData);
        List<SortData> sortDataList = sort.apply(ts).getSortBuilder().getSortDataList();
        joinTableData.addSortDataList(sortDataList);
        this.sqlData.addSortDataList(sortDataList);
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> SortIntactEngine<M, ML, MO, MC, MS, MG> sort(Class<T> sortClass, Sort<T, TL, TO, TC, TS, TG> sort) {
        return sort(sortClass, null, sort);
    }

}
