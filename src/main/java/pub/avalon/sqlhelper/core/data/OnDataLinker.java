package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.LinkType;

import java.util.List;
import java.util.Set;

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

    private Set<OnData> onData;

    private List<OnDataLinker> onDataLinkerList;

    public LinkType getLinkType() {
        return linkType;
    }

    public Set<OnData> getOnData() {
        return onData;
    }

    public void setOnData(Set<OnData> onData) {
        this.onData = onData;
    }

    public List<OnDataLinker> getOnDataLinkerList() {
        return onDataLinkerList;
    }

    public void setOnDataLinkerList(List<OnDataLinker> onDataLinkerList) {
        this.onDataLinkerList = onDataLinkerList;
    }
}
