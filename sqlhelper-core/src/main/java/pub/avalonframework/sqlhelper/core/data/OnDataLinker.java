package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.beans.LinkType;

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

    public OnDataLinker setOnData(List<OnDatum> onData) {
        this.onData = onData;
        return this;
    }

    public List<OnDataLinker> getOnDataLinkers() {
        return onDataLinkers;
    }

    public OnDataLinker setOnDataLinkers(List<OnDataLinker> onDataLinkers) {
        this.onDataLinkers = onDataLinkers;
        return this;
    }
}