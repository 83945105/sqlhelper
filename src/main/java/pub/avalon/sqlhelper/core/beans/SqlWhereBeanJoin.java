package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @date 2019/7/17
 */
public final class SqlWhereBeanJoin<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, SW extends WhereHelper<SW>> extends SqlWhereBean<SW> {

    private Class<T> tableHelperClass;

    private String tableAlias;

    private WhereJoinCallback<SW, TW> whereJoinCallback;

    public SqlWhereBeanJoin(SW whereHelper) {
        super(whereHelper);
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public SqlWhereBeanJoin<T, TJ, TC, TW, TG, TH, TS, SW> setTableHelperClass(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public SqlWhereBeanJoin<T, TJ, TC, TW, TG, TH, TS, SW> setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public WhereJoinCallback<SW, TW> getWhereJoinCallback() {
        return whereJoinCallback;
    }

    public SqlWhereBeanJoin<T, TJ, TC, TW, TG, TH, TS, SW> setWhereJoinCallback(WhereJoinCallback<SW, TW> whereJoinCallback) {
        this.whereJoinCallback = whereJoinCallback;
        return this;
    }

    @Override
    public List<TableWhereDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(WhereJoinCallback.execute(this.whereHelper, this.tableHelperClass, this.tableAlias, this.whereJoinCallback, sqlBuilderOptions));
    }

}
