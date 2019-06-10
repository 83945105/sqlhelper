package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalon.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.helper.*;

import java.util.List;
import java.util.Set;

/**
 * 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereLinkerIntact<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends WhereLinker<T, TO, TC, TW, TG, TS> {

    /**
     * 或
     *
     * @param whereSqlModel where sql模组
     * @return {@link pub.avalon.sqlhelper.core.beans.WhereLinkerIntact}
     */
    public WhereLinkerIntact<T, TO, TC, TW, TG, TS> or(WhereHelper<?> whereSqlModel) {
        if (whereSqlModel == null) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        Set<WhereDatum> whereData = whereSqlModel.takeoutSqlModelData();
        if (whereData == null || whereData.size() == 0) {
            return this;
        }
        whereDataLinker.setWhereData(whereData);
        this.whereDataLinkerList.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param callback where 回调函数
     * @return {@link pub.avalon.sqlhelper.core.callback.WhereCallback}
     */
    public WhereLinkerIntact<T, TO, TC, TW, TG, TS> or(WhereLinkerCallback<T, TO, TC, TW, TG, TS> callback) {
        if (callback == null) {
            return this;
        }
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereLinkerIntact<>());
        List<WhereDataLinker> whereDataLinkerList = whereLinker.takeoutWhereDataLinkerList();
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        whereDataLinker.setWhereDataLinkerList(whereDataLinkerList);
        this.whereDataLinkerList.add(whereDataLinker);
        return this;
    }

    /**
     * 或
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
            SS extends SortHelper<SS>> WhereLinkerIntact<T, TO, TC, TW, TG, TS> or(Class<S> tableHelperClass,
                                                                                     String alias,
                                                                                     WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        SW sw = BeanUtils.tableHelper(tableHelperClass).newWhereHelper();
        WhereLinker<T, TO, TC, TW, TG, TS> whereLinker = callback.apply(new WhereLinkerIntact<>(), sw);
        List<WhereDataLinker> whereDataLinkerList = whereLinker.takeoutWhereDataLinkerList();
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return this;
        }
        WhereDataLinker whereDataLinker = new WhereDataLinker(LinkType.OR);
        whereDataLinker.setWhereDataLinkerList(whereDataLinkerList);
        this.whereDataLinkerList.add(whereDataLinker);
        return this;
    }

    /**
     * 或
     *
     * @param tableHelperClass 目标条件类
     * @param callback        条件
     * @return 当前条件连接器
     */
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> WhereLinkerIntact<T, TO, TC, TW, TG, TS> or(Class<S> tableHelperClass,
                                                                                     WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        return or(tableHelperClass, null, callback);
    }

}
