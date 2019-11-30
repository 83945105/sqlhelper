package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableHavingDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSqlHavingBean extends SqlBean {

    public AbstractSqlHavingBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableHavingDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}