package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.norm.Model;

import java.util.*;

/**
 * 字段模组
 * <p>用于记录列相关信息
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ColumnModel<T extends Model<T, TC, TO, TW, TS, TG>,
        TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
        TO extends OnModel<T, TC, TO, TW, TS, TG>,
        TW extends WhereModel<T, TC, TO, TW, TS, TG>,
        TS extends SortModel<T, TC, TO, TW, TS, TG>,
        TG extends GroupModel<T, TC, TO, TW, TS, TG>> {

    /**
     * 存储列名-别名
     */
    private Map<String, String> columnAliasMap = new LinkedHashMap<>();

    /**
     * 获取列名-别名集合
     *
     * @return LinkedHashMap {@link LinkedHashMap}
     */
    public Map<String, String> getColumnAliasMap() {
        return this.columnAliasMap;
    }

    /**
     * 添加列名-别名
     *
     * @param column 列名
     * @param alias  别名
     */
    protected void addColumnAlias(String column, String alias) {
        this.columnAliasMap.put(column, alias);
    }
}
