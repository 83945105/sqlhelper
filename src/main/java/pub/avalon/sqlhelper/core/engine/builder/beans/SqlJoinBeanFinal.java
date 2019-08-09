package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.callback.JoinCallback;
import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author 白超
 * @date 2019/7/17
 */
public final class SqlJoinBeanFinal<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, SJ extends JoinHelper<SJ>> implements SqlJoinBean<SJ> {

    private SJ joinHelper;

    public SqlJoinBeanFinal(SJ joinHelper) {
        this.joinHelper = joinHelper;
    }

    private JoinType joinType;

    private String tableName;

    private Class<T> tableHelperClass;

    private String tableAlias;

    private JoinCallback<SJ, TJ> joinCallback;

    public JoinType getJoinType() {
        return joinType;
    }

    public SqlJoinBeanFinal<T, TJ, TC, TW, TG, TH, TS, SJ> setJoinType(JoinType joinType) {
        this.joinType = joinType;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public SqlJoinBeanFinal<T, TJ, TC, TW, TG, TH, TS, SJ> setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    public SqlJoinBeanFinal<T, TJ, TC, TW, TG, TH, TS, SJ> setTableHelperClass(Class<T> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public SqlJoinBeanFinal<T, TJ, TC, TW, TG, TH, TS, SJ> setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        return this;
    }

    public JoinCallback<SJ, TJ> getJoinCallback() {
        return joinCallback;
    }

    public SqlJoinBeanFinal<T, TJ, TC, TW, TG, TH, TS, SJ> setJoinCallback(JoinCallback<SJ, TJ> joinCallback) {
        this.joinCallback = joinCallback;
        return this;
    }

    @Override
    public JoinTableDatum execute(SqlBuilderOptions sqlBuilderOptions) {
        if (this.joinCallback != null) {
            return JoinCallback.execute(this.joinType, this.joinHelper, this.tableName, this.tableHelperClass, this.tableAlias, this.joinCallback, sqlBuilderOptions);
        }
        return new JoinTableDatum(this.joinType, this.tableHelperClass, this.tableName, this.tableAlias);
    }

}
