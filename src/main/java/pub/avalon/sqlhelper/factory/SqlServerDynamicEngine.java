package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * SqlServer引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SqlServerDynamicEngine {

    public static <T extends TableHelper<T, TJ, TC, TW, TG, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TJ, TC, TW, TG, TS> table(String tableName, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(DataBaseType.SQLSERVER, tableName, tableHelperClass);
    }

    public static <T extends TableHelper<T, TJ, TC, TW, TG, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TJ, TC, TW, TG, TS> table(Class<T> tableHelperClass) {
        return table(null, tableHelperClass);
    }

}
