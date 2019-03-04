package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.engine.*;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * Sql动态引擎
 *
 * @author 白超
 * @date 2019/2/22
 */
public class SqlDynamicEngine {

    private DataBaseType dataBaseType;

    public SqlDynamicEngine(DataBaseType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> TableEngine<T, TL, TO, TW, TS, TG> table(String tableName, Class<T> mainClass) {
        return new TableEngine<>(tableName, mainClass, dataBaseType);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> TableEngine<T, TL, TO, TW, TS, TG> table(Class<T> mainClass) {
        return table(null, mainClass);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> QueryEngine<T, TL, TO, TW, TS, TG> query(String tableName, Class<T> mainClass) {
        return new QueryEngine<>(tableName, mainClass, dataBaseType);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> QueryEngine<T, TL, TO, TW, TS, TG> query(Class<T> mainClass) {
        return query(null, mainClass);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> InsertEngine<T, TL, TO, TW, TS, TG> insert(String tableName, Class<T> columnClass) {
        return new InsertEngine<>(tableName, columnClass, dataBaseType);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> InsertEngine<T, TL, TO, TW, TS, TG> insert(Class<T> columnClass) {
        return insert(null, columnClass);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> UpdateEngine<T, TL, TO, TW, TS, TG> update(String tableName, Class<T> mainClass) {
        return new UpdateEngine<>(tableName, mainClass, dataBaseType);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> UpdateEngine<T, TL, TO, TW, TS, TG> update(Class<T> mainClass) {
        return update(null, mainClass);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> DeleteEngine<T, TL, TO, TW, TS, TG> delete(String tableName, Class<T> mainClass) {
        return new DeleteEngine<>(tableName, mainClass, dataBaseType);
    }

    public <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> DeleteEngine<T, TL, TO, TW, TS, TG> delete(Class<T> mainClass) {
        return delete(null, mainClass);
    }

}
