package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalon.sqlhelper.core.helper.*;

/**
 * Sql动态引擎
 *
 * @author baichao
 * @date 2019/2/22
 */
public class SqlDynamicEngine {

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> table(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(dataBaseType, tableName, tableHelperClass);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> table(String tableName, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(null, tableName, tableHelperClass);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> table(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(dataBaseType, tableHelperClass);
    }

    public static <T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
            TO extends OnHelper<TO>,
            TC extends ColumnHelper<TC>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> table(Class<T> tableHelperClass) {
        return new SqlHelperEngine<>(null, tableHelperClass);
    }

}
