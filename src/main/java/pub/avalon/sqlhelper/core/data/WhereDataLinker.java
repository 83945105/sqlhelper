package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.LinkType;

import java.util.List;
import java.util.Set;

/**
 * 条件数据连接器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereDataLinker {

    private LinkType linkType;

    public WhereDataLinker(LinkType linkType) {
        this.linkType = linkType;
    }

    private Set<WhereDatum> whereData;

    private List<WhereDataLinker> whereDataLinkers;

    public LinkType getLinkType() {
        return linkType;
    }

    public Set<WhereDatum> getWhereData() {
        return whereData;
    }

    public WhereDataLinker setWhereData(Set<WhereDatum> whereData) {
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
