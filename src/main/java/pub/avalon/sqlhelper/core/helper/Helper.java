package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.SqlDataBuilder;
import pub.avalon.sqlhelper.core.data.SqlModelDatum;

import java.util.Set;

/**
 * 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class Helper<T extends Helper<T, E>, E extends SqlModelDatum> {

    private SqlDataBuilder<T, E> sqlDataBuilder;

    public Helper(SqlDataBuilder<T, E> sqlDataBuilder) {
        this.sqlDataBuilder = sqlDataBuilder;
    }

    /**
     * 接收数据
     *
     * @param tableName   表名
     * @param tableAlias  表别名
     * @param columnName  列名
     * @param columnAlias 列别名
     * @return 模组构建器
     */
    protected SqlDataBuilder<T, E> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.sqlDataBuilder.accept(tableName, tableAlias, columnName, columnAlias);
        return this.sqlDataBuilder;
    }

    public Set<E> takeoutSqlModelData() {
        return this.sqlDataBuilder.takeoutSqlModelData();
    }
}
