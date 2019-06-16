package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.engine.TableEngine;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * MySql引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class MySqlDynamicEngine {

    private MySqlDynamicEngine() {
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TS extends SortHelper<TS>> TableEngine<T, TO, TC, TW, TG, TS> table(String tableName, Class<T> tableHelperClass) {
        return new TableEngine<>(DataBaseType.MYSQL, tableName, tableHelperClass);
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
