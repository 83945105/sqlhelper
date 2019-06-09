package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.SortType;
import pub.avalon.sqlhelper.core.data.SortDatum;
import pub.avalon.sqlhelper.core.helper.Helper;

/**
 * 排序构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SortSqlDataBuilder<T extends Helper<T, SortDatum>> extends AbstractSqlDataBuilder<T, SortDatum> {

    private SortDatum sortDatum;

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.sortDatum = new SortDatum(tableName, tableAlias, columnName, columnAlias);
    }

    /**
     * 升序
     *
     * @return 当前操作的排序模组
     */
    public T asc() {
        this.sortDatum.setSortType(SortType.ASC);
        this.addSqlModelDatum(this.sortDatum);
        return this.getSqlModel();
    }

    /**
     * 降序
     *
     * @return 当前操作的排序模组
     */
    public T desc() {
        this.sortDatum.setSortType(SortType.DESC);
        this.addSqlModelDatum(this.sortDatum);
        return this.getSqlModel();
    }

}
