package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.builder.GroupSqlPartDatumBuilder;
import pub.avalonframework.sqlhelper.core.data.GroupDatum;
import pub.avalonframework.sqlhelper.core.data.TableGroupDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class GroupHelper<T extends GroupHelper<T>> extends Helper {

    private GroupSqlPartDatumBuilder<T> groupSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public GroupHelper(String tableAlias) {
        super(tableAlias);
        this.groupSqlPartDatumBuilder = new GroupSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.groupSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    protected GroupSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.groupSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.groupSqlPartDatumBuilder;
    }

    protected GroupSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.groupSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.groupSqlPartDatumBuilder;
    }

    public List<GroupDatum> takeoutSqlPartData() {
        return this.groupSqlPartDatumBuilder.takeoutSqlPartData();
    }

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

    public static List<TableGroupDatum> execute(GroupHelper<?>... groupHelpers) {
        if (groupHelpers == null || groupHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(groupHelpers).map(groupHelper -> GroupHelper.execute(groupHelper)).collect(Collectors.toList());
    }
}