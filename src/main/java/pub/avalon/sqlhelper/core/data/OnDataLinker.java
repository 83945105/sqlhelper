package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.LinkType;

import java.util.List;

/**
 * On条件数据连接器
 *
 * @author 白超
 * @date 2018/11/18
 */
public final class OnDataLinker {

    private LinkType linkType;

    public OnDataLinker(LinkType linkType) {
        this.linkType = linkType;
    }

    private List<OnData> onDataList;

    private List<OnDataLinker> onDataLinkerList;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<OnData> getOnDataList() {
        return onDataList;
    }

    public void setOnDataList(List<OnData> onDataList) {
        this.onDataList = onDataList;
    }

    public List<OnDataLinker> getOnDataLinkerList() {
        return onDataLinkerList;
    }

    public void setOnDataLinkerList(List<OnDataLinker> onDataLinkerList) {
        this.onDataLinkerList = onDataLinkerList;
    }
}
