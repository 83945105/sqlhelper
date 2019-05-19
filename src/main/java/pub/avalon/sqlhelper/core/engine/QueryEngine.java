package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.modelbuilder.*;

/**
 * 查询引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public final class QueryEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends JoinIntactEngine<T, TO, TC, TW, TG, TS> {

    public QueryEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public QueryEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public QueryEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    @Override
    public QueryEngine<T, TO, TC, TW, TG, TS> setDataBaseType(DataBaseType dataBaseType) {
        return (QueryEngine<T, TO, TC, TW, TG, TS>) super.setDataBaseType(dataBaseType);
    }
}
