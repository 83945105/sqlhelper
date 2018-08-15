package com.dt.core.data;

import com.dt.core.bean.LinkType;

import java.util.List;

/**
 * 连接条件数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class LinkWhereData {

    private LinkType linkType;

    public LinkWhereData(LinkType linkType) {
        this.linkType = linkType;
    }

    private List<WhereData> whereDataList;

    private List<LinkWhereData> linkWhereDataList;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<WhereData> getWhereDataList() {
        return whereDataList;
    }

    public void setWhereDataList(List<WhereData> whereDataList) {
        this.whereDataList = whereDataList;
    }

    public List<LinkWhereData> getLinkWhereDataList() {
        return linkWhereDataList;
    }

    public void setLinkWhereDataList(List<LinkWhereData> linkWhereDataList) {
        this.linkWhereDataList = linkWhereDataList;
    }
}
