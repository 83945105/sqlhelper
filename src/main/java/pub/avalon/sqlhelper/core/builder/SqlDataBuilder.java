package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.SqlModelDatum;
import pub.avalon.sqlhelper.core.helper.Helper;

import java.util.Set;

/**
 * sql数据构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public interface SqlDataBuilder<S extends Helper<S, E>, E extends SqlModelDatum> {

    /**
     * 获取sql模组
     *
     * @return
     */
    S getSqlModel();

    /**
     * 设置sql模组
     *
     * @param sqlModel sql模组
     */
    void setSqlModel(S sqlModel);

    /**
     * 添加sql模组数据
     *
     * @param sqlModelDatum sql模组数据
     */
    void addSqlModelDatum(E sqlModelDatum);

    /**
     * 取出sql模组数据
     * 取出后将清空模组数据
     *
     * @return
     */
    Set<E> takeoutSqlModelData();

    /**
     * 接收数据
     *
     * @param tableName   表名
     * @param tableAlias  表别名
     * @param columnName  列名
     * @param columnAlias 列别名
     */
    void accept(String tableName, String tableAlias, String columnName, String columnAlias);

}