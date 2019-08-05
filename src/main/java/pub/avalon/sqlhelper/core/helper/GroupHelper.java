package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.GroupSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;
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

    protected GroupSqlPartDatumBuilder<T> apply(String sqlPart) {
        this.groupSqlPartDatumBuilder.accept(sqlPart);
        return this.groupSqlPartDatumBuilder;
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
    protected GroupSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.groupSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.groupSqlPartDatumBuilder;
    }

    public List<GroupDatum> takeoutSqlPartData() {
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

    public TableGroupDatum execute() {
        return execute(this);
    }

    public static TableGroupDatum execute(GroupHelper<?> groupHelper) {
        List<GroupDatum> groupData = groupHelper.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return null;
        }
        return new TableGroupDatum(groupHelper.getTableAlias(), groupData);
    }

}
