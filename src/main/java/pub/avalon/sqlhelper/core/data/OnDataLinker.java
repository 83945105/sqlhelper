package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.LinkType;

import java.util.List;

/**
 * @author baichao
 */
public final class OnDataLinker {

    private LinkType linkType;

    public OnDataLinker(LinkType linkType) {
        this.linkType = linkType;
    }

    private List<OnDatum> onData;

    private List<OnDataLinker> onDataLinkers;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<OnDatum> getOnData() {
        return onData;
    }

    public void setOnData(List<OnDatum> onData) {
        this.onData = onData;
    }

    public List<OnDataLinker> getOnDataLinkers() {
        return onDataLinkers;
    }

    public void setOnDataLinkers(List<OnDataLinker> onDataLinkers) {
        this.onDataLinkers = onDataLinkers;
    }
}