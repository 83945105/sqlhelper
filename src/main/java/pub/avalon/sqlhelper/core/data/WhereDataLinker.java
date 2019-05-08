package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.LinkType;

import java.util.List;
import java.util.Set;

/**
 * where条件数据连接器
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

    private List<WhereDataLinker> whereDataLinkerList;

    public LinkType getLinkType() {
        return linkType;
    }

    public Set<WhereDatum> getWhereData() {
        return whereData;
    }

    public void setWhereData(Set<WhereDatum> whereData) {
        this.whereData = whereData;
    }

    public List<WhereDataLinker> getWhereDataLinkerList() {
        return whereDataLinkerList;
    }

    public void setWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList) {
        this.whereDataLinkerList = whereDataLinkerList;
    }
}
