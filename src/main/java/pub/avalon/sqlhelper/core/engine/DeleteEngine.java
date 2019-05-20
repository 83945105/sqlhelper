package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.modelbuilder.*;
import pub.avalon.sqlhelper.core.sql.DeleteByPrimaryKey;

import java.util.Collection;

/**
 * 删除引擎
 *
 * @author 白超
 * @date 2018/8/24
 */
public class DeleteEngine<T extends TableModel<T, TO, TC, TW, TG, TS>,
        TO extends OnSqlModel<TO>,
        TC extends ColumnSqlModel<TC>,
        TW extends WhereSqlModel<TW>,
        TG extends GroupSqlModel<TG>,
        TS extends SortSqlModel<TS>> extends JoinIntactEngine<T, TO, TC, TW, TG, TS> {

    public DeleteEngine(Class<T> tableModelClass) {
        super(tableModelClass);
    }

    public DeleteEngine(String tableName, Class<T> tableModelClass) {
        super(tableName, tableModelClass);
    }

    public DeleteEngine(String tableName, Class<T> tableModelClass, String alias) {
        super(tableName, tableModelClass, alias);
    }

    @Override
    public DeleteEngine<T, TO, TC, TW, TG, TS> setDataBaseType(DataBaseType dataBaseType) {
        return (DeleteEngine<T, TO, TC, TW, TG, TS>) super.setDataBaseType(dataBaseType);
    }

}
