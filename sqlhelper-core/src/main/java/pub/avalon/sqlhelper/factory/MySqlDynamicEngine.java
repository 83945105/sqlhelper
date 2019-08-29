package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * MySql引擎
 *
 * @author baichao
 * @since 2018/7/10
 */
public final class MySqlDynamicEngine {

    private MySqlDynamicEngine() {
    }

    public static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> table(String tableName, Class<T> tableHelperClass, String tableAlias) {
        return new SqlHelperEngine<>(DataBaseType.MYSQL, tableName, tableHelperClass, tableAlias);
    }

    public static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> table(String tableName, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(DataBaseType.MYSQL, tableName, tableHelperClass);
    }

    public static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> table(Class<T> tableHelperClass, String tableAlias) {
        return new SqlHelperEngine<>(DataBaseType.MYSQL, tableHelperClass, tableAlias);
    }

    public static <T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
            TJ extends JoinHelper<TJ>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TJ, TC, TW, TG, TH, TS> table(Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(DataBaseType.MYSQL, tableHelperClass);
    }

}
