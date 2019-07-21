package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.GroupSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Set;

/**
 * 分组助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public abstract class GroupHelper<T extends GroupHelper<T>> extends Helper {

    private GroupSqlPartDatumBuilder<T> groupSqlPartDatumBuilder;

    public GroupHelper(String tableAlias) {
        super(tableAlias);
        this.groupSqlPartDatumBuilder = new GroupSqlPartDatumBuilder<>(tableAlias, (T) this);
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
    protected GroupSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        this.groupSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
        return this.groupSqlPartDatumBuilder;
    }

    public Set<GroupDatum> takeoutSqlPartData() {
        return this.groupSqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.groupSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }
}
