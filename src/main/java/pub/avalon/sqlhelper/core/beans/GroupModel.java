package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.norm.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分组模组
 * <p>用户记录分组相关信息
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class GroupModel<T extends Model<T, TL, TO, TW, TS, TG>,
        TL extends ColumnModel<T, TL, TO, TW, TS, TG>,
        TO extends OnModel<T, TL, TO, TW, TS, TG>,
        TW extends WhereModel<T, TL, TO, TW, TS, TG>,
        TS extends SortModel<T, TL, TO, TW, TS, TG>,
        TG extends GroupModel<T, TL, TO, TW, TS, TG>> {

    /**
     * 存储列名
     */
    private List<String> columns = new ArrayList<>();

    /**
     * 获取列名集合
     *
     * @return ArrayList {@link ArrayList}
     */
    public List<String> getColumns() {
        return this.columns;
    }

    /**
     * 新增列名
     *
     * @param column 列名
     */
    protected void addColumn(String column) {
        this.columns.add(column);
    }
}
