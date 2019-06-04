package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalon.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.modelbuilder.*;

import java.util.List;
import java.util.Set;

/**
 * 条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereLinkerIntact<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends WhereLinker<T, TO, TC, TW, TG, TS> {

    /**
     * 或
     *
     * @param whereSqlModel where sql模组
     * @return {@link pub.avalon.sqlhelper.core.beans.WhereLinkerIntact}
     */
    public WhereLinkerIntact<T, TO, TC, TW, TG, TS> or(WhereSqlModel<?> whereSqlModel) {
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
            SS extends SortSqlModel<SS>> WhereLinkerIntact<T, TO, TC, TW, TG, TS> or(Class<S> tableModelClass,
                                                                                     String alias,
                                                                                     WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        SW sw = BeanUtils.tableModel(tableModelClass).newWhereSqlModel();
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
     * @param tableModelClass 目标条件类
     * @param callback        条件
     * @return 当前条件连接器
     */
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> WhereLinkerIntact<T, TO, TC, TW, TG, TS> or(Class<S> tableModelClass,
                                                                                     WhereJoinLinkerCallback<T, TO, TC, TW, TG, TS, SW> callback) {
        return or(tableModelClass, null, callback);
    }

}
