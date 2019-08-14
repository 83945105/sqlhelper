package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.callback.GroupCallback;
import pub.avalon.sqlhelper.core.data.TableGroupDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 * @date 2019/7/17
 */
public final class SqlGroupBeanJoin<T extends TableHelper<T, TJ, TC, TW, TG, TH, TS>,
        TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractSqlGroupBean {

    private Class<T> tableHelperClass;

    private GroupCallback<TG> groupCallback;

    public SqlGroupBeanJoin(Class<T> tableHelperClass, String tableAlias, GroupCallback<TG> groupCallback) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.groupCallback = groupCallback;
    }

    @Override
    public List<TableGroupDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(GroupCallback.execute(this.tableHelperClass, this.tableAlias, this.groupCallback, sqlBuilderOptions));
    }

}
