package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.builder.OnDataBuilder;

/**
 * 连接查询On条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface OnModelValue<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> {

    /**
     * 接收处理On条件模组
     *
     * @param onModel On条件模组
     * @return On条件构建器
     */
    OnDataBuilder<M, MC, MO, MW, MS, MG> apply(MO onModel);

}
