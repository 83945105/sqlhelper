package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * 分页引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class LimitEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends SqlHelperEngine<T, TO, TC, TW, TG, TS> {

    public LimitEngine(Class<T> tableHelperClass) {
        super(tableHelperClass);
    }

    public LimitEngine(Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(tableHelperClass, sqlBuilderOptions);
    }

    public LimitEngine(String tableName, Class<T> tableHelperClass) {
        super(tableName, tableHelperClass);
    }

    public LimitEngine(String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(tableName, tableHelperClass, sqlBuilderOptions);
    }

    public LimitEngine(String tableName, Class<T> tableHelperClass, String alias) {
        super(tableName, tableHelperClass, alias);
    }

    public LimitEngine(String tableName, Class<T> tableHelperClass, String alias, SqlBuilderOptions sqlBuilderOptions) {
        super(tableName, tableHelperClass, alias, sqlBuilderOptions);
    }

    public LimitEngine limitTop(Integer num) {
        this.buildLimitData(1, num);
        return this;
    }

    public LimitEngine limitOne() {
        this.buildLimitData(1, 1);
        return this;
    }

    public LimitEngine limit(LimitHandler limit) {
        this.setLimitData(limit);
        return this;
    }

    public LimitEngine limit(Integer start, Integer end) {
        this.setLimitStart(start);
        this.setLimitEnd(end);
        return this;
    }

    public LimitEngine limit(Integer total, Integer currentPage, Integer pageSize) {
        this.buildLimitData(total, currentPage, pageSize);
        return this;
    }

}
