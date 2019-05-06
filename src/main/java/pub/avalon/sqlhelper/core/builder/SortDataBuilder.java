package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.AbstractTableData;
import pub.avalon.sqlhelper.core.data.SortDatum;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 排序构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SortDataBuilder<S extends Model<S, SL, SO, SC, SS, SG>,
        SL extends ColumnModel<S, SL, SO, SC, SS, SG>,
        SO extends OnModel<S, SL, SO, SC, SS, SG>,
        SC extends WhereModel<S, SL, SO, SC, SS, SG>,
        SS extends SortModel<S, SL, SO, SC, SS, SG>,
        SG extends GroupModel<S, SL, SO, SC, SS, SG>> extends AbstractModelDataBuilder<SortDataBuilder, SortDatum> {

    private SS handleModel;

    public SortDataBuilder(SS handleModel) {
        this.handleModel = handleModel;
    }

    private SortDatum sortDatum;

    private AbstractTableData ownerTableData;

    public void setOwnerTableData(AbstractTableData ownerTableData) {
        this.ownerTableData = ownerTableData;
    }

    @Override
    public SortDataBuilder apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.sortDatum = new SortDatum(tableName, tableAlias, columnName, columnAlias);
        return this;
    }

    /**
     * 升序
     *
     * @return 当前操作的排序模组
     */
    public SS asc() {
        this.sortDatum.setSortType(SortType.ASC);
        this.addModelData(this.sortDatum);
        return this.handleModel;
    }

    /**
     * 降序
     *
     * @return 当前操作的排序模组
     */
    public SS desc() {
        this.sortDatum.setSortType(SortType.DESC);
        this.addModelData(this.sortDatum);
        return this.handleModel;
    }

}
