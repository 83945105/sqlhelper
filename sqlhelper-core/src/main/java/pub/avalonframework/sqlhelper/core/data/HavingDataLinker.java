package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.beans.LinkType;

import java.util.List;

/**
 * @author baichao
 */
public final class HavingDataLinker {

    private LinkType linkType;

    public HavingDataLinker(LinkType linkType) {
        this.linkType = linkType;
    }

    private List<HavingDatum> havingData;

    private List<HavingDataLinker> havingDataLinkers;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<HavingDatum> getHavingData() {
        return havingData;
    }

    public HavingDataLinker setHavingData(List<HavingDatum> havingData) {
        this.havingData = havingData;
        return this;
    }

    public List<HavingDataLinker> getHavingDataLinkers() {
        return havingDataLinkers;
    }

    public HavingDataLinker setHavingDataLinkers(List<HavingDataLinker> havingDataLinkers) {
        this.havingDataLinkers = havingDataLinkers;
        return this;
    }
}