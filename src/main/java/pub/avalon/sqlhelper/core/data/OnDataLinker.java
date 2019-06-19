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

    private Set<OnDatum> onData;

    private List<OnDataLinker> onDataLinkers;

    public LinkType getLinkType() {
        return linkType;
    }

    public Set<OnDatum> getOnData() {
        return onData;
    }

    public void setOnData(Set<OnDatum> onData) {
        this.onData = onData;
    }

    public List<OnDataLinker> getOnDataLinkers() {
        return onDataLinkers;
    }

    public void setOnDataLinkers(List<OnDataLinker> onDataLinkers) {
        this.onDataLinkers = onDataLinkers;
    }

}
