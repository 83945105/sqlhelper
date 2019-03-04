package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;

import java.util.Map;

/**
 * 模组
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface Model<T extends Model<T, TL, TO, TW, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
        TO extends OnModel<T, TL, TO, TW, TS, TG>,
        TW extends WhereModel<T, TL, TO, TW, TS, TG>,
        TS extends SortModel<T, TL, TO, TW, TS, TG>,
        TG extends GroupModel<T, TL, TO, TW, TS, TG>> {

    /**
     * 获取表名
     *
     * @return 表名
     */
    String getTableName();

    /**
     * 获取表别名
     *
     * @return 表别名
     */
    String getTableAlias();

    /**
     * 获取主键名
     *
     * @return 主键名
     */
    String getPrimaryKeyName();

    /**
     * 获取主键别名
     *
     * @return 主键别名
     */
    String getPrimaryKeyAlias();

    /**
     * 获取列-别名集合
     *
     * @return 列-别名集合
     */
    Map<String, String> getColumnAliasMap();

    /**
     * 获取别名-列集合
     *
     * @return 别名-列集合
     */
    Map<String, String> getAliasColumnMap();

    /**
     * 获取列模组
     *
     * @return 列模组
     */
    TL getColumnModel();

    /**
     * 获取On条件模组
     *
     * @return On条件模组
     */
    TO getOnModel();

    /**
     * 获取Where条件模组
     *
     * @return Where条件模组
     */
    TW getWhereModel();

    /**
     * 获取分组模组
     *
     * @return 分组模组
     */
    TG getGroupModel();

    /**
     * 获取排序模组
     *
     * @return 排序模组
     */
    TS getSortModel();

}
