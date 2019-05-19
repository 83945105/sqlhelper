package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.engine.*;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * SqlServer引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SqlServerDynamicEngine {

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> TableEngine<T, TO, TC, TW, TG, TS> table(String tableName, Class<T> tableModelClass) {
        return new TableEngine<>(tableName, tableModelClass).setDataBaseType(DataBaseType.SQLSERVER);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> TableEngine<T, TO, TC, TW, TG, TS> table(Class<T> tableModelClass) {
        return table(null, tableModelClass);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> QueryEngine<T, TO, TC, TW, TG, TS> query(String tableName, Class<T> tableModelClass) {
        return new QueryEngine<>(tableName, tableModelClass).setDataBaseType(DataBaseType.SQLSERVER);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> QueryEngine<T, TO, TC, TW, TG, TS> query(Class<T> tableModelClass) {
        return query(null, tableModelClass);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> InsertEngine<T, TO, TC, TW, TG, TS> insert(String tableName, Class<T> tableModelClass) {
        return new InsertEngine<>(tableName, tableModelClass).setDataBaseType(DataBaseType.SQLSERVER);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> InsertEngine<T, TO, TC, TW, TG, TS> insert(Class<T> tableModelClass) {
        return insert(null, tableModelClass);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> UpdateEngine<T, TO, TC, TW, TG, TS> update(String tableName, Class<T> tableModelClass) {
        return new UpdateEngine<>(tableName, tableModelClass).setDataBaseType(DataBaseType.SQLSERVER);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> UpdateEngine<T, TO, TC, TW, TG, TS> update(Class<T> tableModelClass) {
        return update(null, tableModelClass);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> DeleteEngine<T, TO, TC, TW, TG, TS> delete(String tableName, Class<T> tableModelClass) {
        return new DeleteEngine<>(tableName, tableModelClass).setDataBaseType(DataBaseType.SQLSERVER);
    }

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> DeleteEngine<T, TO, TC, TW, TG, TS> delete(Class<T> tableModelClass) {
        return delete(null, tableModelClass);
    }

}
