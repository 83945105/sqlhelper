package com.dt.core.bean;

import com.dt.core.data.SortData;
import com.dt.core.data.AbstractTableData;
import com.dt.core.norm.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SortBuilder<S extends Model<S, SL, SO, SC, SS, SG>,
        SL extends ColumnModel<S, SL, SO, SC, SS, SG>,
        SO extends OnModel<S, SL, SO, SC, SS, SG>,
        SC extends WhereModel<S, SL, SO, SC, SS, SG>,
        SS extends SortModel<S, SL, SO, SC, SS, SG>,
        SG extends GroupModel<S, SL, SO, SC, SS, SG>> {

    private SS handleModel;

    public SortBuilder(SS handleModel) {
        this.handleModel = handleModel;
    }

    private SortData sortData;

    private List<SortData> sortDataList = new ArrayList<>();

    private AbstractTableData ownerTableData;

    public SortBuilder<S, SL, SO, SC, SS, SG> handler(String ownerColumnName) {
        this.sortData = new SortData();
        this.sortData.setTableData(this.ownerTableData);
        this.sortData.setColumnName(ownerColumnName);
        return this;
    }

    /**
     * 升序
     *
     * @return 当前操作的排序模组
     */
    public SS asc() {
        this.sortData.setSortType(SortType.ASC);
        this.sortDataList.add(this.sortData);
        return this.handleModel;
    }

    /**
     * 降序
     *
     * @return 当前操作的排序模组
     */
    public SS desc() {
        this.sortData.setSortType(SortType.DESC);
        this.sortDataList.add(this.sortData);
        return this.handleModel;
    }

    public List<SortData> getSortDataList() {
        return this.sortDataList;
    }

    public void setOwnerTableData(AbstractTableData ownerTableData) {
        this.ownerTableData = ownerTableData;
    }
}
