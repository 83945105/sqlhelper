package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.data.TableColumnDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author 白超
 * @date 2019/8/9
 */
public abstract class AbstractSqlColumnBean extends SqlBean {

    public AbstractSqlColumnBean(String tableAlias) {
        super(tableAlias);
    }

    abstract public List<TableColumnDatum> execute(SqlBuilderOptions sqlBuilderOptions);

}
