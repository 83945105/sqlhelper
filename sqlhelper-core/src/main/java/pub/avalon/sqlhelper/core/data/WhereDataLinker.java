package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.LinkType;

import java.util.List;

/**
 * @author baichao
 */
public final class WhereDataLinker {

    private LinkType linkType;

    public WhereDataLinker(LinkType linkType) {
        this.linkType = linkType;
    }

    private List<WhereDatum> whereData;

    private List<WhereDataLinker> whereDataLinkers;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<WhereDatum> getWhereData() {
        return whereData;
    }

    public WhereDataLinker setWhereData(List<WhereDatum> whereData) {
        this.whereData = whereData;
        return this;
    }

    public List<WhereDataLinker> getWhereDataLinkers() {
        return whereDataLinkers;
    }

    public WhereDataLinker setWhereDataLinkers(List<WhereDataLinker> whereDataLinkers) {
        this.whereDataLinkers = whereDataLinkers;
        return this;
    }
}