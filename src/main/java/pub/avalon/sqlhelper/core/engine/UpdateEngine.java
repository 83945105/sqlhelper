package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * 更新引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public final class UpdateEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends JoinIntactEngine<T, TO, TC, TW, TG, TS> {

    public UpdateEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public UpdateEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public UpdateEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    @Override
    public UpdateEngine<T, TO, TC, TW, TG, TS> setDataBaseType(DataBaseType dataBaseType) {
        return (UpdateEngine<T, TO, TC, TW, TG, TS>) super.setDataBaseType(dataBaseType);
    }
}
