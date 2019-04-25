package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

/**
 * Where比较条件 - 子查询
 *
 * @author 白超
 * @date 2019/3/4
 */
public interface WhereComparisonOperatorSubQuery<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> {

    /**
     * 等于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW equalToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 等于
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW equalToSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.equalToSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 等于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW equalToSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.equalToSubQuery(null, modelClass, alias, subQuery);
    }

    /**
     * 不等于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 不等于
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notEqualToSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.notEqualToSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 不等于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notEqualToSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.notEqualToSubQuery(null, modelClass, alias, subQuery);
    }

    /**
     * 大于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 大于
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.greaterThanSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 大于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.greaterThanSubQuery(null, modelClass, alias, subQuery);
    }

    /**
     * 大于等于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanAndEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 大于等于
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanAndEqualToSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.greaterThanAndEqualToSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 大于等于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanAndEqualToSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.greaterThanAndEqualToSubQuery(null, modelClass, alias, subQuery);
    }

    /**
     * 小于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 小于
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.lessThanSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 小于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.lessThanSubQuery(null, modelClass, alias, subQuery);
    }

    /**
     * 小于等于
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanAndEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 小于等于
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanAndEqualToSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.lessThanAndEqualToSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 小于等于
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanAndEqualToSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.lessThanAndEqualToSubQuery(null, modelClass, alias, subQuery);
    }

    /**
     * 模糊匹配
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW likeSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 模糊匹配
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW likeSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.likeSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 模糊匹配
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW likeSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.likeSubQuery(null, modelClass, alias, subQuery);
    }

    /**
     * 在...内
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW inSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 在...内
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW inSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.inSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 在...内
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW inSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.inSubQuery(null, modelClass, alias, subQuery);
    }

    /**
     * 不在...内
     *
     * @param tableName  表名
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notInSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery);

    /**
     * 不在...内
     *
     * @param modelClass Model类
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notInSubQuery(Class<T> modelClass, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.notInSubQuery(null, modelClass, null, subQuery);
    }

    /**
     * 不在...内
     *
     * @param modelClass Model类
     * @param alias      别名
     * @param subQuery   子查询
     * @return Where条件模组
     */
    default <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notInSubQuery(Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        return this.notInSubQuery(null, modelClass, alias, subQuery);
    }

}
