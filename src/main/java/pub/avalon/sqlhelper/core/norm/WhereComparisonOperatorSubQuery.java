package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * Where比较条件 - 子查询
 *
 * @author 白超
 * @date 2019/3/4
 */
public interface WhereComparisonOperatorSubQuery<M extends Model<M, ML, MO, MW, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MW, MS, MG>,
        MO extends OnModel<M, ML, MO, MW, MS, MG>,
        MW extends WhereModel<M, ML, MO, MW, MS, MG>,
        MS extends SortModel<M, ML, MO, MW, MS, MG>,
        MG extends GroupModel<M, ML, MO, MW, MS, MG>> {

    <T extends Model<T, TL, TO, TW, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
            TO extends OnModel<T, TL, TO, TW, TS, TG>,
            TW extends WhereModel<T, TL, TO, TW, TS, TG>,
            TS extends SortModel<T, TL, TO, TW, TS, TG>,
            TG extends GroupModel<T, TL, TO, TW, TS, TG>> MW equalTo(String tableName, Class<T> mainClass, String alias, SubQuery<M, ML, MO, MW, MS, MG, T, TL, TO, TW, TS, TG> subQuery);

}
