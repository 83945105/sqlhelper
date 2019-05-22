package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.engine.TableEngine;
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

}
