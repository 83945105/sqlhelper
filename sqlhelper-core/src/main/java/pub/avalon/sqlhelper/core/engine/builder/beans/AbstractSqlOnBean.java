package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.data.TableOnDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSqlOnBean extends SqlBean {

    public AbstractSqlOnBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableOnDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}