package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalon.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.helper.*;

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
public class WhereLinker<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> {

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
    public WhereLinkerIntact<T, TO, TC, TW, TG, TS> and(WhereHelper<?> whereSqlModel) {
        if (whereSqlModel == null) {
            return (WhereLinkerIntact<T, TO, TC, TW, TG, TS>) this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.AND);
        Set<WhereDatum> whereData = whereSqlModel.takeoutSqlPartData();
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
     * @param tableHelperClass 目标条件类
     * @param alias           目标条件别名
     * @param callback        条件
     * @return 当前条件连接器
     */
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereLinkerIntact<T, TO, TC, TW, TG, TS> and(Class<S> tableHelperClass,
                                                                                      String alias,
                                                                                      WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        SW sw = BeanUtils.tableHelper(tableHelperClass).newWhereHelper();
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
     * @param tableHelperClass 目标条件类
     * @param callback        条件
     * @return 当前条件连接器
     */
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereLinkerIntact<T, TO, TC, TW, TG, TS> and(Class<S> tableHelperClass,
                                                                                      WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        return and(tableHelperClass, null, callback);
    }

}
