package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalon.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereLinker<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> {

    protected List<WhereDataLinker> whereDataLinkerList = new ArrayList<>();

    public List<WhereDataLinker> takeoutWhereDataLinkerList() {
        List<WhereDataLinker> whereDataLinkerList = this.whereDataLinkerList;
        this.whereDataLinkerList = new ArrayList<>();
        return whereDataLinkerList;
    }

    /**
     * 且
     *
     * @param whereSqlModel where sql模组
     * @return {@link pub.avalon.sqlhelper.core.beans.WhereLinkerIntact}
     */
    public WhereLinkerIntact<T, TO, TC, TW, TG, TS> and(WhereSqlModel<?> whereSqlModel) {
        if (whereSqlModel == null) {
            return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        Set<WhereDatum> whereData = whereSqlModel.takeoutSqlModelData();
        if (whereData == null || whereData.size() == 0) {
            return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
        }
        whereDataLinker.setWhereData(whereData);
        this.whereDataLinkerList.add(whereDataLinker);
        return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
    }

    /**
     * 且
     *
     * @param callback where 回调函数
     * @return {@link pub.avalon.sqlhelper.core.callback.WhereCallback}
     */
    public WhereLinkerIntact<T, TO, TC, TW, TG, TS> and(WhereLinkerCallback<T, TO, TC, TW, TG, TS> callback) {
        if (callback == null) {
            return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
        }
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereLinkerIntact<>());
        List<WhereDataLinker> whereDataLinkerList = whereLinker.takeoutWhereDataLinkerList();
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        whereDataLinker.setWhereDataLinkerList(whereDataLinkerList);
        this.whereDataLinkerList.add(whereDataLinker);
        return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
    }

    /**
     * 且
     *
     * @param tableModelClass 目标条件类
     * @param alias           目标条件别名
     * @param callback        条件
     * @return 当前条件连接器
     */
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> WhereLinkerIntact<T, TO, TC, TW, TG, TS> and(Class<S> tableModelClass,
                                                                                      String alias,
                                                                                      WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        SW sw = BeanUtils.tableModel(tableModelClass).newWhereSqlModel();
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereLinkerIntact<>(), sw);
        List<WhereDataLinker> whereDataLinkerList = whereLinker.takeoutWhereDataLinkerList();
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        whereDataLinker.setWhereDataLinkerList(whereDataLinkerList);
        this.whereDataLinkerList.add(whereDataLinker);
        return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
    }

    /**
     * @param tableModelClass 目标条件类
     * @param callback        条件
     * @return 当前条件连接器
     */
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> WhereLinkerIntact<T, TO, TC, TW, TG, TS> and(Class<S> tableModelClass,
                                                                                      WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        return and(tableModelClass, null, callback);
    }

}
