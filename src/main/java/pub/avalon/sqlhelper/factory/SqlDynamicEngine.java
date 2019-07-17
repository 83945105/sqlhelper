package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * Sql动态引擎
 *
 * @author 白超
 * @date 2019/2/22
 */
public class SqlDynamicEngine {

    public static <T extends TableHelper<T, TJ, TC, TW, TG, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TJ, TC, TW, TG, TS> table(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(dataBaseType, tableName, tableHelperClass);
    }

    public static <T extends TableHelper<T, TJ, TC, TW, TG, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TJ, TC, TW, TG, TS> table(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(dataBaseType, tableHelperClass);
    }

}
