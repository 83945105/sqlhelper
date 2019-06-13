package pub.avalon.sqlhelper.core.engine;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.core.callback.ColumnCallback;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.data.TableFunctionColumnDatum;
import pub.avalon.sqlhelper.core.data.VirtualFieldDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.Set;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ColumnEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends JoinEngine<T, TO, TC, TW, TG, TS> {

    public ColumnEngine(Class<T> tableHelperClass) {
        super(tableHelperClass);
    }

    public ColumnEngine(String tableName, Class<T> tableHelperClass) {
        super(tableName, tableHelperClass);
    }

    public ColumnEngine(String tableName, Class<T> tableHelperClass, String alias) {
        super(tableName, tableHelperClass, alias);
    }

    public ColumnEngine<T, TO, TC, TW, TG, TS> column(ColumnHelper<?>... columns) {
        if (columns == null || columns.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableHelper类型
        return this;
    }

    public ColumnEngine<T, TO, TC, TW, TG, TS> column(ColumnCallback<TC> callback) {
        T t = BeanUtils.tableHelper(this.tableHelperClass);
        TC tc = t.newColumnHelper();
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlModelData();
        // 调用了column方法但是没有设置任何列,则使用该模组对应的表所有列
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(t);
        }
        this.addTableColumnDatum(new TableColumnDatum<>(this.tableHelperClass, this.tableAlias, columnData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> ColumnEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> callback) {
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        if (columnData == null || columnData.size() == 0) {
            columnData = BeanUtils.getColumnData(s);
        }
        this.addTableColumnDatum(new TableColumnDatum<>(tableHelperClass, tableAlias, columnData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> ColumnEngine<T, TO, TC, TW, TG, TS> column(Class<S> tableHelperClass, ColumnCallback<SC> callback) {
        return column(tableHelperClass, null, callback);
    }

    public ColumnEngine<T, TO, TC, TW, TG, TS> virtualColumn(String value, String alias) {
        VirtualFieldDatum virtualFieldDatum = new VirtualFieldDatum();
        virtualFieldDatum.setValue(value);
        virtualFieldDatum.setAlias(alias);
        this.addVirtualFieldDatum(virtualFieldDatum);
        return this;
    }

    public ColumnEngine<T, TO, TC, TW, TG, TS> virtualColumn(int value, String alias) {
        VirtualFieldDatum virtualFieldDatum = new VirtualFieldDatum();
        virtualFieldDatum.setValue(value);
        virtualFieldDatum.setAlias(alias);
        this.addVirtualFieldDatum(virtualFieldDatum);
        return this;
    }

    public ColumnEngine<T, TO, TC, TW, TG, TS> virtualColumn(long value, String alias) {
        VirtualFieldDatum virtualFieldDatum = new VirtualFieldDatum();
        virtualFieldDatum.setValue(value);
        virtualFieldDatum.setAlias(alias);
        this.addVirtualFieldDatum(virtualFieldDatum);
        return this;
    }

    public ColumnEngine<T, TO, TC, TW, TG, TS> virtualColumn(double value, String alias) {
        VirtualFieldDatum virtualFieldDatum = new VirtualFieldDatum();
        virtualFieldDatum.setValue(value);
        virtualFieldDatum.setAlias(alias);
        this.addVirtualFieldDatum(virtualFieldDatum);
        return this;
    }

    public ColumnEngine<T, TO, TC, TW, TG, TS> functionColumn(FunctionColumnType functionColumnType, ColumnCallback<TC> callback) {
        if (functionColumnType == null) {
            return this;
        }
        TC tc = BeanUtils.tableHelper(this.tableHelperClass).newColumnHelper();
        tc = callback.apply(tc);
        Set<ColumnDatum> columnData = tc.takeoutSqlModelData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        this.addTableFunctionColumnDatum(new TableFunctionColumnDatum<>(this.tableHelperClass, this.tableAlias, functionColumnType, columnData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> ColumnEngine<T, TO, TC, TW, TG, TS> functionColumn(Class<S> tableHelperClass, String tableAlias, FunctionColumnType functionColumnType, ColumnCallback<SC> callback) {
        if (functionColumnType == null) {
            return this;
        }
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        // 如果没设置列, 则跳过
        if (columnData == null || columnData.size() == 0) {
            return this;
        }
        this.addTableFunctionColumnDatum(new TableFunctionColumnDatum<>(tableHelperClass, tableAlias, functionColumnType, columnData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> ColumnEngine<T, TO, TC, TW, TG, TS> functionColumn(Class<S> tableHelperClass, FunctionColumnType functionColumnType, ColumnCallback<SC> callback) {
        return functionColumn(tableHelperClass, null, functionColumnType, callback);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> ColumnEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableHelperClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
//        SqlBuilder sqlBuilder = SubQueryCallback.execute(this.getSqlData(), tableName, tableHelperClass, alias, callback);
//        this.addSubQueryData(columnAlias, sqlBuilder);
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> ColumnEngine<T, TO, TC, TW, TG, TS> subQuery(String tableName, Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(tableName, tableHelperClass, null, callback, columnAlias);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> ColumnEngine<T, TO, TC, TW, TG, TS> subQuery(Class<S> tableHelperClass, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(null, tableHelperClass, null, callback, columnAlias);
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> ColumnEngine<T, TO, TC, TW, TG, TS> subQuery(Class<S> tableHelperClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback, String columnAlias) {
        return this.subQuery(null, tableHelperClass, alias, callback, columnAlias);
    }

}
