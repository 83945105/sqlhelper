package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataProducer;
import pub.avalonframework.sqlhelper.core.engine.ColumnEngine;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.AbstractSqlColumnBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlColumnBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlColumnBeanJoin;
import pub.avalonframework.sqlhelper.core.engine.callback.ColumnCallbackEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class SqlColumn<TC extends ColumnHelper<TC>> implements ColumnEngine<SqlColumn<TC>>, ColumnCallbackEngine<TC, SqlColumn<TC>> {

    private TC columnHelper;
    private String tableAlias;

    {
        this.columnHelper = HelperManager.findColumnHelperClassFromAncestorsGenericType(this);
    }

    public SqlColumn() {
        this.tableAlias = this.columnHelper.getTableAlias();
    }

    public SqlColumn(String tableAlias) {
        this.columnHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlColumnBean> selectSqlColumnBeans = new ArrayList<>(1);
    private List<AbstractSqlColumnBean> insertSqlColumnBeans = new ArrayList<>(1);
    private List<AbstractSqlColumnBean> updateSqlColumnBeans = new ArrayList<>(1);

    @Override
    public SqlColumn<TC> column(ColumnHelper<?>... columnHelpers) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.selectSqlColumnBeans.add(sqlColumnBean);
        this.insertSqlColumnBeans.add(sqlColumnBean);
        this.updateSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    @Override
    public SqlColumn<TC> select(ColumnHelper<?>... columnHelpers) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.selectSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    @Override
    public SqlColumn<TC> insert(ColumnHelper<?>... columnHelpers) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.insertSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    @Override
    public SqlColumn<TC> update(ColumnHelper<?>... columnHelpers) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnHelpers(columnHelpers);
        this.updateSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    @Override
    public SqlColumn<TC> column(ColumnCallback<TC> columnCallback) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.selectSqlColumnBeans.add(sqlColumnBean);
        this.insertSqlColumnBeans.add(sqlColumnBean);
        this.updateSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    @Override
    public SqlColumn<TC> select(ColumnCallback<TC> columnCallback) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.selectSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    @Override
    public SqlColumn<TC> insert(ColumnCallback<TC> columnCallback) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.insertSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    @Override
    public SqlColumn<TC> update(ColumnCallback<TC> columnCallback) {
        SqlColumnBean<TC> sqlColumnBean = new SqlColumnBean<>(this.columnHelper, this.tableAlias).setColumnCallback(columnCallback);
        this.updateSqlColumnBeans.add(sqlColumnBean);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlColumn<TC> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        SqlColumnBeanJoin sqlColumnBeanJoin = new SqlColumnBeanJoin<>(tableHelperClass, tableAlias, columnCallback);
        this.selectSqlColumnBeans.add(sqlColumnBeanJoin);
        this.insertSqlColumnBeans.add(sqlColumnBeanJoin);
        this.updateSqlColumnBeans.add(sqlColumnBeanJoin);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlColumn<TC> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        SqlColumnBeanJoin sqlColumnBeanJoin = new SqlColumnBeanJoin<>(tableHelperClass, tableAlias, columnCallback);
        this.selectSqlColumnBeans.add(sqlColumnBeanJoin);
        return this;
    }

    @Override
    public SqlColumn<TC> virtualColumn(Object columnValue, String columnAlias) {
        return null;
    }

    @Override
    public SqlColumn<TC> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlColumn<TC> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return null;
    }

    @Override
    public SqlColumn<TC> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        return null;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlColumnBean> getSelectSqlColumnBeans() {
        return selectSqlColumnBeans;
    }

    public List<AbstractSqlColumnBean> getInsertSqlColumnBeans() {
        return insertSqlColumnBeans;
    }

    public List<AbstractSqlColumnBean> getUpdateSqlColumnBeans() {
        return updateSqlColumnBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FC extends ColumnHelper<FC>> void execute(SqlColumn<FC> sqlColumn, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataProducer sqlDataProducer = supplier.get();
        if (sqlDataProducer == null) {
            return;
        }
        sqlColumn.getSelectSqlColumnBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderOptions).forEach(sqlDataProducer::addSelectTableColumnDatum));
        sqlColumn.getInsertSqlColumnBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderOptions).forEach(sqlDataProducer::addInsertTableColumnDatum));
        sqlColumn.getUpdateSqlColumnBeans().forEach(sqlColumnBean -> sqlColumnBean.execute(sqlBuilderOptions).forEach(sqlDataProducer::addUpdateTableColumnDatum));
    }
}