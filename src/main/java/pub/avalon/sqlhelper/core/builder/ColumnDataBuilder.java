package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 列构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public final class ColumnDataBuilder<T extends Model<T, TC, TO, TW, TS, TG>,
        TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
        TO extends OnModel<T, TC, TO, TW, TS, TG>,
        TW extends WhereModel<T, TC, TO, TW, TS, TG>,
        TS extends SortModel<T, TC, TO, TW, TS, TG>,
        TG extends GroupModel<T, TC, TO, TW, TS, TG>> extends AbstractModelDataBuilder<TC, ColumnDatum> {

    /**
     * 当然操作的模组
     */
    private TC handleModel;

    public ColumnDataBuilder(TC handleModel) {
        this.handleModel = handleModel;
    }

    @Override
    public TC apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.addModelData(new ColumnDatum(tableName, tableAlias, columnName, columnAlias));
        return this.handleModel;
    }

}
