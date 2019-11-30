package pub.avalonframework.sqlhelper.core.engine.builder.beans;

import pub.avalonframework.sqlhelper.core.data.TableGroupDatum;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSqlGroupBean extends SqlBean {

    public AbstractSqlGroupBean(String tableAlias) {
        super(tableAlias);
    }

    public abstract List<TableGroupDatum> execute(SqlBuilderOptions sqlBuilderOptions);
}