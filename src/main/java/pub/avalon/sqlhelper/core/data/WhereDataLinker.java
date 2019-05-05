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

    private Set<WhereData> whereDataList;

    private List<WhereDataLinker> whereDataLinkerList;

    public LinkType getLinkType() {
        return linkType;
    }

    public Set<WhereData> getWhereDataList() {
        return whereDataList;
    }

    public void setWhereDataList(Set<WhereData> whereDataList) {
        this.whereDataList = whereDataList;
    }

    public List<WhereDataLinker> getWhereDataLinkerList() {
        return whereDataLinkerList;
    }

    public void setWhereDataLinkerList(List<WhereDataLinker> whereDataLinkerList) {
        this.whereDataLinkerList = whereDataLinkerList;
    }
}
