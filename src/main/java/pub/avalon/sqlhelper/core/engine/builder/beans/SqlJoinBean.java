package pub.avalon.sqlhelper.core.engine.builder.beans;

import pub.avalon.sqlhelper.core.data.JoinTableDatum;
import pub.avalon.sqlhelper.core.helper.JoinHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author 白超
 * @date 2019/7/17
 */
public interface SqlJoinBean<TJ extends JoinHelper<TJ>> {

    JoinTableDatum execute(SqlBuilderOptions sqlBuilderOptions);

}
