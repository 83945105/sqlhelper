package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.data.TableWhereDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSqlWhereBean extends SqlBean {

    public AbstractSqlWhereBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableWhereDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}