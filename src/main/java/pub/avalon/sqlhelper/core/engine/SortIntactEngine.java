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
public class SortIntactEngine<M extends Model<M, ML, MO, MW, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MW, MS, MG>,
        MO extends OnModel<M, ML, MO, MW, MS, MG>,
        MW extends WhereModel<M, ML, MO, MW, MS, MG>,
        MS extends SortModel<M, ML, MO, MW, MS, MG>,
        MG extends GroupModel<M, ML, MO, MW, MS, MG>> extends LimitIntactEngine<M, ML, MO, MW, MS, MG> {

    SortIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    SortIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    SortIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    public SortIntactEngine<M, ML, MO, MW, MS, MG> sort(Sort<M, ML, MO, MW, MS, MG> sort) {
        MainTableData mainTableData = this.sqlData.getMainTableData();
        MS ms = (MS) mainTableData.getTableModel().getSortModel();
        ms.getSortBuilder().setOwnerTableData(mainTableData);
        List<SortData> sortDataList = sort.apply(ms).getSortBuilder().getSortDataList();
        this.sqlData.addSortDataList(sortDataList);
        return this;
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> SortIntactEngine<M, ML, MO, MW, MS, MG> sort(Class<T> sortClass, String alias, Sort<T, TL, TO, TW, TS, TG> sort) {
        JoinTableData joinTableData = this.sqlData.getJoinTableData(alias, sortClass);
        TS ts = (TS) joinTableData.getTableModel().getSortModel();
        ts.getSortBuilder().setOwnerTableData(joinTableData);
        List<SortData> sortDataList = sort.apply(ts).getSortBuilder().getSortDataList();
        this.sqlData.addSortDataList(sortDataList);
        return this;
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> SortIntactEngine<M, ML, MO, MW, MS, MG> sort(Class<T> sortClass, Sort<T, TL, TO, TW, TS, TG> sort) {
        return sort(sortClass, null, sort);
    }

}
