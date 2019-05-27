package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.modelbuilder.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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

    public LimitIntactEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public LimitIntactEngine(Class<T> tableModelClass, SqlBuilderOptions sqlBuilderOptions) {
        super(tableModelClass, sqlBuilderOptions);
    }

    public LimitIntactEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public LimitIntactEngine(String tableName, Class<T> tableModelClass, SqlBuilderOptions sqlBuilderOptions) {
        super(tableName, tableModelClass, sqlBuilderOptions);
    }

    public LimitIntactEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    public LimitIntactEngine(String tableName, Class<T> tableModelClass, String alias, SqlBuilderOptions sqlBuilderOptions) {
        super(tableName, tableModelClass, alias, sqlBuilderOptions);
    }

    public LimitIntactEngine limitTop(Integer num) {
        this.buildLimitData(1, num);
        return this;
    }

    public LimitIntactEngine limitOne() {
        this.buildLimitData(1, 1);
        return this;
    }

    public LimitIntactEngine limit(LimitHandler limit) {
        this.setLimitData(limit);
        return this;
    }

    public LimitIntactEngine limit(Integer start, Integer end) {
        this.setLimitStart(start);
        this.setLimitEnd(end);
        return this;
    }

    public LimitIntactEngine limit(Integer total, Integer currentPage, Integer pageSize) {
        this.buildLimitData(total, currentPage, pageSize);
        return this;
    }

}
