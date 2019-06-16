package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
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

    public LimitEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        super(dataBaseType, tableHelperClass);
    }

    public LimitEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableHelperClass, sqlBuilderOptions);
    }

    public LimitEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        super(dataBaseType, tableName, tableHelperClass);
    }

    public LimitEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public LimitEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias);
    }

    public LimitEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
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
