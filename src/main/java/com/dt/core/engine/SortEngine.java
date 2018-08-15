package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.JoinTableData;
import com.dt.core.data.MainTableData;
import com.dt.core.data.SortData;
import com.dt.core.norm.Model;
import com.dt.core.norm.Sort;
import com.dt.core.parser.SortParser;

import java.util.List;

/**
 * 排序引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SortEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends LimitEngine<M, ML, MO, MC, MS, MG> {

    private SortParser sortParser = SortParser.getInstance();

    SortEngine(Class<M> mainClass) {
        super(mainClass);
    }

    SortEngine(Class<M> mainClass, String tableName) {
        super(mainClass, tableName);
    }

    @SuppressWarnings("unchecked")
    public SortEngine<M, ML, MO, MC, MS, MG> sort(Sort<M, ML, MO, MC, MS, MG> sort) {
        MainTableData mainTableData = this.data.getMainTableData();
        MS ms = (MS) mainTableData.getTable().getSort();
        ms.getSortBuilder().setOwnerTableData(mainTableData);
        List<SortData> sortDataList = sort.apply(ms).getSortBuilder().getSortDataList();
        mainTableData.addSortDataList(sortDataList);
        this.data.addSortDataList(sortDataList);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> SortEngine<M, ML, MO, MC, MS, MG> sort(Class<T> sortClass, String alias, Sort<T, TL, TO, TC, TS, TG> sort) {
        JoinTableData joinTableData = this.data.getJoinTableData(alias, sortClass);
        TS ts = (TS) joinTableData.getTable().getSort();
        ts.getSortBuilder().setOwnerTableData(joinTableData);
        List<SortData> sortDataList = sort.apply(ts).getSortBuilder().getSortDataList();
        joinTableData.addSortDataList(sortDataList);
        this.data.addSortDataList(sortDataList);
        return this;
    }

    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> SortEngine<M, ML, MO, MC, MS, MG> sort(Class<T> sortClass, Sort<T, TL, TO, TC, TS, TG> sort) {
        return sort(sortClass, null, sort);
    }

    @Override
    public String getSortSql() {
        return this.sortParser.parse(this.getData().getSortDataList());
    }

}
