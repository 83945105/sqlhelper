package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * Sql片段数据构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public interface SqlPartDatumBuilder<T extends Helper, S extends SqlPartDatum> {

    /**
     * 获取助手
     *
     * @return {@link Helper}
     */
    T getHelper();

    /**
     * 设置助手
     *
     * @param helper {@link Helper}
     */
    void setHelper(T helper);

    /**
     * 添加sql片段数据
     *
     * @param sqlPartDatum {@link SqlPartDatum}
     */
    void addSqlPartDatum(S sqlPartDatum);

    /**
     * 取出sql片段数据
     * 取出后将清空
     *
     * @return {@link java.util.LinkedHashSet}
     */
    List<S> takeoutSqlPartData();

    void accept(String sqlPart);

    /**
     * 接收数据
     *
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     * @param mappingFieldName    映射属性名
     * @param columnHandlers      列处理
     */
    void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers);

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions);

}
