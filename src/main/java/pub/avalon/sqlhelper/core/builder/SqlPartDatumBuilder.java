package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     * @param mappingFieldName    映射属性名
     */
    void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName);

    /**
     * 接收数据
     *
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     */
    default void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        this.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, templateColumnAlias);
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions);

}
