package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.beans.LinkType;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.WhereSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.data.WhereDataLinker;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 条件助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public abstract class WhereHelper<T extends WhereHelper<T>> extends Helper {

    private WhereSqlPartDatumBuilder<T> whereSqlPartDatumBuilder;

    public WhereHelper(String tableAlias) {
        super(tableAlias);
        this.whereSqlPartDatumBuilder = new WhereSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    protected WhereSqlPartDatumBuilder<T> apply(String sqlPart) {
        this.whereSqlPartDatumBuilder.accept(sqlPart);
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
}
