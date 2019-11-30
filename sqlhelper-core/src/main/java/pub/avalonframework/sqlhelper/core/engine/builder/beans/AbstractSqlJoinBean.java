package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.data.JoinTableDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public abstract class AbstractSqlJoinBean extends SqlBean {

    public AbstractSqlJoinBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract JoinTableDatum execute(SqlBuilderOptions sqlBuilderOptions);
}