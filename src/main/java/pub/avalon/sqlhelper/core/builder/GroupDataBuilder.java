package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.GroupDatum;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 分组数据构建器
 *
 * @author 白超
 * @date 2019/5/6
 */
public class GroupDataBuilder<T extends Model<T, TC, TO, TW, TS, TG>,
        TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
        TO extends OnModel<T, TC, TO, TW, TS, TG>,
        TW extends WhereModel<T, TC, TO, TW, TS, TG>,
        TS extends SortModel<T, TC, TO, TW, TS, TG>,
        TG extends GroupModel<T, TC, TO, TW, TS, TG>> extends AbstractModelDataBuilder<TG, GroupDatum> {

    /**
     * 当然操作的模组
     */
    private TG handleModel;

    public GroupDataBuilder(TG handleModel) {
        this.handleModel = handleModel;
    }

    @Override
    public TG apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.addModelData(new GroupDatum(tableName, tableAlias, columnName, columnAlias));
        return this.handleModel;
    }
}
