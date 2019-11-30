package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSqlColumnBean extends SqlBean {

    public AbstractSqlColumnBean(String tableAlias) {
        super(tableAlias);
    }

    abstract public List<TableColumnDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}