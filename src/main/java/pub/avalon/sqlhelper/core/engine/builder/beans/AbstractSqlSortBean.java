package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.data.TableSortDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 * @date 2019/8/9
 */
public abstract class AbstractSqlSortBean extends SqlBean {

    public AbstractSqlSortBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableSortDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}
