package pub.avalon.sqlhelper.core.builder;

/**
 * 模组数据构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
@FunctionalInterface
public interface ModelDataBuilder<T> {

    /**
     * 接收数据
     *
     * @param tableName   表名
     * @param tableAlias  表别名
     * @param columnName  列名
     * @param columnAlias 列别名
     * @return
     */
    T apply(String tableName, String tableAlias, String columnName, String columnAlias);

}
