package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.SortDatum;
import pub.avalon.sqlhelper.core.data.TableData;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 排序构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SortDataBuilder<T extends Model<T, TC, TO, TW, TS, TG>,
        TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
        TO extends OnModel<T, TC, TO, TW, TS, TG>,
        TW extends WhereModel<T, TC, TO, TW, TS, TG>,
        TS extends SortModel<T, TC, TO, TW, TS, TG>,
        TG extends GroupModel<T, TC, TO, TW, TS, TG>> extends AbstractModelDataBuilder<SortDataBuilder, SortDatum> {

    private TS handleModel;

    public SortDataBuilder(TS handleModel) {
        this.handleModel = handleModel;
    }

    private SortDatum sortDatum;

    private TableData<T> tableData;

    public void setOwnerTableData(TableData<T> tableData) {
        this.tableData = tableData;
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
    public TS asc() {
        this.sortDatum.setSortType(SortType.ASC);
        this.addModelData(this.sortDatum);
        return this.handleModel;
    }

    /**
     * 降序
     *
     * @return 当前操作的排序模组
     */
    public TS desc() {
        this.sortDatum.setSortType(SortType.DESC);
        this.addModelData(this.sortDatum);
        return this.handleModel;
    }

}
