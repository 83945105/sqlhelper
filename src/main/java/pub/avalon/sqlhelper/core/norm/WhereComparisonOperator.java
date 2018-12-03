package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * Where比较条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public interface WhereComparisonOperator<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

    /**
     * 等于
     *
     * @param whereBuilder Where条件构建器
     * @return Where条件模组
     */
    MC equalTo(WhereBuilder whereBuilder);

    /**
     * 不等于
     *
     * @param whereBuilder Where条件构建器
     * @return Where条件模组
     */
    MC notEqualTo(WhereBuilder whereBuilder);

    /**
     * 大于
     *
     * @param whereBuilder Where条件构建器
     * @return Where条件模组
     */
    MC greaterThan(WhereBuilder whereBuilder);

    /**
     * 大于等于
     *
     * @param whereBuilder Where条件构建器
     * @return Where条件模组
     */
    MC greaterThanAndEqualTo(WhereBuilder whereBuilder);

    /**
     * 小于
     *
     * @param whereBuilder Where条件构建器
     * @return Where条件模组
     */
    MC lessThan(WhereBuilder whereBuilder);

    /**
     * 小于等于
     *
     * @param whereBuilder Where条件构建器
     * @return Where条件模组
     */
    MC lessThanAndEqualTo(WhereBuilder whereBuilder);

    /**
     * 等于
     *
     * @param onClass      Where关联模组类
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC equalTo(Class<T> onClass,
                                                                     WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        return this.equalTo(onClass, null, whereModelValue);
    }

    /**
     * 等于
     *
     * @param onClass      Where关联模组类
     * @param alias        别名
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC equalTo(Class<T> onClass,
                                                                     String alias,
                                                                     WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue);

    /**
     * 不等于
     *
     * @param onClass      Where关联模组类
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC notEqualTo(Class<T> onClass,
                                                                        WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        return this.notEqualTo(onClass, null, whereModelValue);
    }

    /**
     * 不等于
     *
     * @param onClass      Where关联模组类
     * @param alias        别名
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC notEqualTo(Class<T> onClass,
                                                                        String alias,
                                                                        WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue);

    /**
     * 大于
     *
     * @param onClass      Where关联模组类
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC greaterThan(Class<T> onClass,
                                                                         WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        return this.greaterThan(onClass, null, whereModelValue);
    }

    /**
     * 大于
     *
     * @param onClass      Where关联模组类
     * @param alias        别名
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC greaterThan(Class<T> onClass,
                                                                         String alias,
                                                                         WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue);

    /**
     * 大于等于
     *
     * @param onClass      Where关联模组类
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC greaterThanAndEqualTo(Class<T> onClass,
                                                                                   WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        return this.greaterThanAndEqualTo(onClass, null, whereModelValue);
    }

    /**
     * 大于等于
     *
     * @param onClass      Where关联模组类
     * @param alias        别名
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC greaterThanAndEqualTo(Class<T> onClass,
                                                                                   String alias,
                                                                                   WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue);

    /**
     * 小于
     *
     * @param onClass      Where关联模组类
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC lessThan(Class<T> onClass,
                                                                      WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        return this.lessThan(onClass, null, whereModelValue);
    }

    /**
     * 小于
     *
     * @param onClass      Where关联模组类
     * @param alias        别名
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC lessThan(Class<T> onClass,
                                                                      String alias,
                                                                      WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue);

    /**
     * 小于等于
     *
     * @param onClass      Where关联模组类
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    default <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC lessThanAndEqualTo(Class<T> onClass,
                                                                                WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        return this.lessThanAndEqualTo(onClass, null, whereModelValue);
    }

    /**
     * 小于等于
     *
     * @param onClass      Where关联模组类
     * @param alias        别名
     * @param whereModelValue Where处理
     * @return Where条件模组
     */
    <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC lessThanAndEqualTo(Class<T> onClass,
                                                                                String alias,
                                                                                WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue);

}
