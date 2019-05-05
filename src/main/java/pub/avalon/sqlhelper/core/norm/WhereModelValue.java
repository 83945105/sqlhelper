package pub.avalon.sqlhelper.core.norm;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.builder.WhereDataBuilder;

/**
 * Where条件
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@FunctionalInterface
public interface WhereModelValue<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> {

    /**
     * 接收处理Where条件模组
     *
     * @param whereModel Where条件模组
     * @return Where条件构建器
     */
    WhereDataBuilder<M, MC, MO, MW, MS, MG> apply(MW whereModel);

}
