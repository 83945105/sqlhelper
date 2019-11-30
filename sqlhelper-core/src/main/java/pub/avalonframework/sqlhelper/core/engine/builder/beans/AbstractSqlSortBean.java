package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableSortDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSqlSortBean extends SqlBean {

    public AbstractSqlSortBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableSortDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}