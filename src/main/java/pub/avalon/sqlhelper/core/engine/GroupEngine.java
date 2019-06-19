package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Set;

/**
 * 分组引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class GroupEngine<T extends TableHelper<T, TO, TC, TW, TG, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TS extends SortHelper<TS>> extends SortEngine<T, TO, TC, TW, TG, TS> {

    public GroupEngine(DataBaseType dataBaseType, Class<T> tableHelperClass) {
        super(dataBaseType, tableHelperClass);
    }

    public GroupEngine(DataBaseType dataBaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableHelperClass, sqlBuilderOptions);
    }

    public GroupEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass) {
        super(dataBaseType, tableName, tableHelperClass);
    }

    public GroupEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public GroupEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias);
    }

    public GroupEngine(DataBaseType dataBaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(dataBaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    public GroupEngine<T, TO, TC, TW, TG, TS> group(GroupHelper<?>... groupSqlModels) {
        if (groupSqlModels == null || groupSqlModels.length == 0) {
            return this;
        }
        //TODO 未实现 待确认该方法是否限定TableHelper类型
        return this;
    }

    public GroupEngine<T, TO, TC, TW, TG, TS> group(GroupCallback<TG> callback) {
        TG tg = BeanUtils.tableHelper(this.tableHelperClass).newGroupHelper();
        tg.setSqlBuilderOptions(this.sqlBuilderOptions);
        tg = callback.apply(tg);
        Set<GroupDatum> groupData = tg.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.addTableGroupDatum(new TableGroupDatum<>(this.tableHelperClass, this.tableAlias, groupData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> GroupEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> callback) {
        SG sg = BeanUtils.tableHelper(tableHelperClass).newGroupHelper();
        sg.setSqlBuilderOptions(this.sqlBuilderOptions);
        sg = callback.apply(sg);
        Set<GroupDatum> groupData = sg.takeoutSqlPartData();
        if (groupData == null || groupData.size() == 0) {
            return this;
        }
        this.addTableGroupDatum(new TableGroupDatum<>(tableHelperClass, tableAlias, groupData));
        return this;
    }

    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> GroupEngine<T, TO, TC, TW, TG, TS> group(Class<S> tableHelperClass, GroupCallback<SG> callback) {
        return group(tableHelperClass, null, callback);
    }

}
