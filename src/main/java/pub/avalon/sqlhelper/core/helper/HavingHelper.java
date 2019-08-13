package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.HavingDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;
import java.util.Set;

/**
 * 分组条件助手
 *
 * @author 白超
 * @date 2019/7/18
 */
public abstract class HavingHelper<T extends HavingHelper<T>> extends Helper {

    private HavingSqlPartDatumBuilder<T> havingSqlPartDatumBuilder;

    public HavingHelper(String tableAlias) {
        super(tableAlias);
        this.havingSqlPartDatumBuilder = new HavingSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.havingSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    /**
     * 接收sql片段
     *
     * @param templateTableName  模板表名
     * @param templateTableAlias 模板表别名
     * @param sqlPart            sql片段
     * @return {@link SqlPartDatumBuilder}
     */
    protected HavingSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.havingSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.havingSqlPartDatumBuilder;
    }

    /**
     * 接收数据
     *
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     * @param columnHandlers      列处理
     * @return {@link SqlPartDatumBuilder}
     */
    protected HavingSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.havingSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName, columnHandlers);
        return this.havingSqlPartDatumBuilder;
    }

    /**
     * 接收数据
     *
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     * @return {@link SqlPartDatumBuilder}
     */
    protected HavingSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.havingSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.havingSqlPartDatumBuilder;
    }

    public List<HavingDatum> takeoutSqlPartData() {
        return this.havingSqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.havingSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }

}
