package com.dt.factory;

import com.dt.core.bean.*;
import com.dt.core.engine.*;
import com.dt.core.norm.Model;

/**
 * MySql引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class MySqlEngine {

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> TableEngine<T, TL, TO, TC, TS, TG> table(String tableName, Class<T> mainClass) {
        return new TableEngine<>(mainClass, tableName, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> TableEngine<T, TL, TO, TC, TS, TG> table(Class<T> mainClass) {
        return table(null, mainClass);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> QueryEngine<T, TL, TO, TC, TS, TG> query(String tableName, Class<T> mainClass) {
        return new QueryEngine<>(mainClass, tableName, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> QueryEngine<T, TL, TO, TC, TS, TG> query(Class<T> mainClass) {
        return query(null, mainClass);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> InsertEngine<T, TL, TO, TC, TS, TG> insert(String tableName, Class<T> columnClass) {
        return new InsertEngine<>(columnClass, tableName, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> InsertEngine<T, TL, TO, TC, TS, TG> insert(Class<T> columnClass) {
        return insert(null, columnClass);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> UpdateEngine<T, TL, TO, TC, TS, TG> update(String tableName, Class<T> mainClass) {
        return new UpdateEngine<>(mainClass, tableName, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> UpdateEngine<T, TL, TO, TC, TS, TG> update(Class<T> mainClass) {
        return update(null, mainClass);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> DeleteEngine<T, TL, TO, TC, TS, TG> delete(String tableName, Class<T> mainClass) {
        return new DeleteEngine<>(mainClass, tableName, DataBaseType.MYSQL);
    }

    public static <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> DeleteEngine<T, TL, TO, TC, TS, TG> delete(Class<T> mainClass) {
        return delete(null, mainClass);
    }

}
