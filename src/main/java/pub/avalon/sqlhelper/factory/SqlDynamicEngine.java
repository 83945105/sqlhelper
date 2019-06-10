package pub.avalon.sqlhelper.factory;

import pub.avalon.sqlhelper.core.engine.TableEngine;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * Sql动态引擎
 *
 * @author 白超
 * @date 2019/2/22
 */
public class SqlDynamicEngine {

    public static <T extends TableHelper<T, TO, TC, TW, TG, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TS extends SortHelper<TS>> TableEngine<T, TO, TC, TW, TG, TS> table(String tableName, Class<T> tableHelperClass) {
        return new TableEngine<>(tableName, tableHelperClass);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TS extends SortHelper<TS>> TableEngine<T, TO, TC, TW, TG, TS> table(Class<T> tableHelperClass) {
        return table(null, tableHelperClass);
    }

}
