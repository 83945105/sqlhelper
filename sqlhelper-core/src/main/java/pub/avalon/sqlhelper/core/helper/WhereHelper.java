package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.beans.LinkType;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class WhereHelper<T extends WhereHelper<T>> extends Helper {

    private WhereSqlPartDatumBuilder<T> whereSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public WhereHelper(String tableAlias) {
        super(tableAlias);
        this.whereSqlPartDatumBuilder = new WhereSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.whereSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    /**
     * 接收sql片段
     *
     * @param templateTableName  模板表名
     * @param templateTableAlias 模板表别名
     * @param sqlPart            sql片段
     * @return {@link SqlPartDatumBuilder}
     */
    protected WhereSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.whereSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.whereSqlPartDatumBuilder;
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
    protected WhereSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.whereSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.whereSqlPartDatumBuilder;
    }

    public List<WhereDatum> takeoutSqlPartData() {
        return this.whereSqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.whereSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }

    public TableWhereDatum execute() {
        return execute(this);
    }

    public static TableWhereDatum execute(WhereHelper<?> whereHelper) {
        List<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return null;
        }
        return new TableWhereDatum(whereHelper.getTableAlias(),
                Collections.singletonList(new WhereDataLinker(LinkType.AND).setWhereData(whereData)));
    }

    public static List<TableWhereDatum> execute(WhereHelper<?>... whereHelpers) {
        if (whereHelpers == null || whereHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(whereHelpers).map(whereHelper -> WhereHelper.execute(whereHelper)).collect(Collectors.toList());
    }

}
