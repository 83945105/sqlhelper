package pub.avalon.sqlhelper.factory;

import pub.avalon.sqlhelper.core.engine.TableEngine;
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

}
