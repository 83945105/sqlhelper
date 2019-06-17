package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;

import java.util.Set;

/**
 * Sql片段数据构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public interface SqlPartDatumBuilder<S extends Helper<S, E>, E extends SqlPartDatum> {

    /**
     * 获取助手
     *
     * @return {@link Helper}
     */
    S getHelper();

    /**
     * 设置助手
     *
     * @param helper {@link Helper}
     */
    void setHelper(S helper);

    /**
     * 添加sql片段数据
     *
     * @param sqlPartDatum {@link SqlPartDatum}
     */
    void addSqlPartDatum(E sqlPartDatum);

    /**
     * 取出sql片段数据
     * 取出后将清空
     *
     * @return {@link java.util.LinkedHashSet}
     */
    Set<E> takeoutSqlPartData();

    /**
     * 接收数据
     *
     * @param tableName   表名
     * @param tableAlias  表别名
     * @param columnName  列名
     * @param columnAlias 列别名
     * @param fieldName   属性名
     */
    void accept(String tableName, String tableAlias, String columnName, String columnAlias, String fieldName);

    /**
     * 接收数据
     *
     * @param tableName   表名
     * @param tableAlias  表别名
     * @param columnName  列名
     * @param columnAlias 列别名
     */
    default void accept(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.accept(tableName, tableAlias, columnName, columnAlias, columnAlias);
    }

}
