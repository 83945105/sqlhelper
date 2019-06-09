package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.helper.*;

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
        TS extends SortHelper<TS>> extends JoinIntactEngine<T, TO, TC, TW, TG, TS> {

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
        super.setDataBaseType(dataBaseType);
        return this;
    }

}
