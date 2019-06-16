package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * 表引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public final class TableEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends ColumnEngine<T, TO, TC, TW, TG, TS> {

    public TableEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        super(dataBaseType, tableHelperClass);
    }

    public TableEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableHelperClass, sqlBuilderOptions);
    }

    public TableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        super(dataBaseType, tableName, tableHelperClass);
    }

    public TableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public TableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias);
    }

    public TableEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }
}
