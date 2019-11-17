package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.data.TableHavingDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

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