package pub.avalon.sqlhelper.factory;

import pub.avalon.sqlhelper.core.engine.*;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * Sql动态引擎
 *
 * @author 白超
 * @date 2019/2/22
 */
public class SqlDynamicEngine {

    public static <T extends TableModel<T, TO, TC, TW, TG, TS>,
            TO extends OnSqlModel<TO>,
            TC extends ColumnSqlModel<TC>,
            TW extends WhereSqlModel<TW>,
            TG extends GroupSqlModel<TG>,
            TS extends SortSqlModel<TS>> TableEngine<T, TO, TC, TW, TG, TS> table(String tableName, Class<T> tableModelClass) {
        return new TableEngine<>(tableName, tableModelClass);
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
        return new QueryEngine<>(tableName, tableModelClass);
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
        return new InsertEngine<>(tableName, tableModelClass);
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
        return new UpdateEngine<>(tableName, tableModelClass);
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
        return new DeleteEngine<>(tableName, tableModelClass);
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
