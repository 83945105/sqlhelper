package com.dt.core.bean;

import com.dt.core.data.OnData;
import com.dt.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * On条件连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class OnLink<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> {

    /**
     * 存储连接类型-连接条件集合
     * key {@link LinkType}
     * value {@link OnData}
     */
    private Map<LinkType, List<OnData>> linkOnDataMap = new LinkedHashMap<>();

    /**
     * 且条件
     *
     * @param onModel On模组
     * @return On条件连接器 {@link OnLink}
     */
    public OnLink<M, ML, MO, MC, MS, MG> and(OnModel<M, ML, MO, MC, MS, MG> onModel) {
        this.linkOnDataMap.put(LinkType.AND, onModel.onBuilder.getOnDataList());
        return this;
    }

    /**
     * 获取连接信息集合
     *
     * @return LinkedHashMap {@link LinkedHashMap}
     */
    public Map<LinkType, List<OnData>> getLinkOnDataMap() {
        return this.linkOnDataMap;
    }

}
