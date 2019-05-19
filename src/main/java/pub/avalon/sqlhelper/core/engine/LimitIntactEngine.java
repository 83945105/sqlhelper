package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * 分页引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class LimitIntactEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends SqlEngine<T, TO, TC, TW, TG, TS> {

    LimitIntactEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    LimitIntactEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    LimitIntactEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public LimitIntactEngine limitTop(Integer num) {
        this.sqlData.buildLimitData(1, num);
        return this;
    }

    public LimitIntactEngine limitOne() {
        this.sqlData.buildLimitData(1, 1);
        return this;
    }

    public LimitIntactEngine limit(LimitHandler limit) {
        this.sqlData.setLimitData(limit);
        return this;
    }

    public LimitIntactEngine limit(Integer start, Integer end) {
        this.sqlData.setLimitStart(start);
        this.sqlData.setLimitEnd(end);
        return this;
    }

    public LimitIntactEngine limit(Integer total, Integer currentPage, Integer pageSize) {
        this.sqlData.buildLimitData(total, currentPage, pageSize);
        return this;
    }

    public SqlData<T> getData() {
        return this.sqlData;
    }

}
