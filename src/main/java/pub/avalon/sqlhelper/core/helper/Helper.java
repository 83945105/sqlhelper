package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.SqlPartDatum;

import java.util.Set;

/**
 * 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public class Helper<T extends Helper<T, E>, E extends SqlPartDatum> {

    private SqlPartDatumBuilder<T, E> sqlPartDatumBuilder;

    public Helper(SqlPartDatumBuilder<T, E> sqlPartDatumBuilder) {
        this.sqlPartDatumBuilder = sqlPartDatumBuilder;
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
    protected SqlPartDatumBuilder<T, E> apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.sqlPartDatumBuilder.accept(tableName, tableAlias, columnName, columnAlias);
        return this.sqlPartDatumBuilder;
    }

    public Set<E> takeoutSqlPartData() {
        return this.sqlPartDatumBuilder.takeoutSqlPartData();
    }

}
