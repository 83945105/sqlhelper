package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * 表引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public final class TableEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends SqlEngine<T, TO, TC, TW, TG, TS> {

    public TableEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public TableEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public TableEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    @Override
    public TableEngine<T, TO, TC, TW, TG, TS> setDataBaseType(DataBaseType dataBaseType) {
        return (TableEngine<T, TO, TC, TW, TG, TS>) super.setDataBaseType(dataBaseType);
    }

}
