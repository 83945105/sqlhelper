package pub.avalon.sqlhelper.factory;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.engine.*;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * MySql引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class MySqlDynamicEngine {

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> TableEngine<T, TC, TO, TW, TS, TG> table(String tableName, Class<T> mainClass) {
        return new TableEngine<>(tableName, mainClass, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> TableEngine<T, TC, TO, TW, TS, TG> table(Class<T> mainClass) {
        return table(null, mainClass);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> QueryEngine<T, TC, TO, TW, TS, TG> query(String tableName, Class<T> mainClass) {
        return new QueryEngine<>(tableName, mainClass, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> QueryEngine<T, TC, TO, TW, TS, TG> query(Class<T> mainClass) {
        return query(null, mainClass);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> InsertEngine<T, TC, TO, TW, TS, TG> insert(String tableName, Class<T> columnClass) {
        return new InsertEngine<>(tableName, columnClass, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> InsertEngine<T, TC, TO, TW, TS, TG> insert(Class<T> columnClass) {
        return insert(null, columnClass);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> UpdateEngine<T, TC, TO, TW, TS, TG> update(String tableName, Class<T> mainClass) {
        return new UpdateEngine<>(tableName, mainClass, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> UpdateEngine<T, TC, TO, TW, TS, TG> update(Class<T> mainClass) {
        return update(null, mainClass);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> DeleteEngine<T, TC, TO, TW, TS, TG> delete(String tableName, Class<T> mainClass) {
        return new DeleteEngine<>(tableName, mainClass, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> DeleteEngine<T, TC, TO, TW, TS, TG> delete(Class<T> mainClass) {
        return delete(null, mainClass);
    }

}
