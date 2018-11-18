package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.LinkType;

import java.util.List;

/**
 * 连接On条件数据
 *
 * @author 白超
 * @date 2018/11/18
 */
public final class LinkOnData {

    private LinkType linkType;

    public LinkOnData(LinkType linkType) {
        this.linkType = linkType;
    }

    private List<OnData> onDataList;

    private List<LinkOnData> linkOnDataList;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<OnData> getOnDataList() {
        return onDataList;
    }

    public void setOnDataList(List<OnData> onDataList) {
        this.onDataList = onDataList;
    }

    public List<LinkOnData> getLinkOnDataList() {
        return linkOnDataList;
    }

    public void setLinkOnDataList(List<LinkOnData> linkOnDataList) {
        this.linkOnDataList = linkOnDataList;
    }
}
