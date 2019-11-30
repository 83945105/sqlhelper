package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableOnDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

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