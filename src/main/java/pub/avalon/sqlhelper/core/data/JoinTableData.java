package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 连接表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class JoinTableData<T extends Model> extends AbstractTableData<T> {

    private JoinType joinType = JoinType.INNER;

    private Map<LinkType, List<OnData>> linkOnDataMap;

    public JoinTableData(Class<T> tableClass) {
        super(tableClass);
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public Map<LinkType, List<OnData>> getLinkOnDataMap() {
        return linkOnDataMap;
    }

    public void addLinkOnDataMap(Map<LinkType, List<OnData>> linkOnDataMap) {
        if (linkOnDataMap == null || linkOnDataMap.size() == 0) {
            return;
        }
        if (this.linkOnDataMap == null) {
            this.linkOnDataMap = new LinkedHashMap<>();
        }
        this.linkOnDataMap.putAll(linkOnDataMap);
    }

}
