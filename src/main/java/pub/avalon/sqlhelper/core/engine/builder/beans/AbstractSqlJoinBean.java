package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 * @date 2019/8/9
 */
public abstract class AbstractSqlJoinBean extends SqlBean {

    public AbstractSqlJoinBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract JoinTableDatum execute(SqlBuilderOptions sqlBuilderOptions);
}
