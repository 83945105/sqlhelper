package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class JoinTableData<T extends Model> extends AbstractTableData<T> {

    private JoinType joinType = JoinType.INNER;

    private List<LinkOnData> linkOnDataList;

    public JoinTableData(Class<T> tableClass) {
        super(tableClass);
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public List<LinkOnData> getLinkOnDataList() {
        return linkOnDataList;
    }

    public void addLinkOnDataList(List<LinkOnData> linkOnDataList) {
        if (linkOnDataList == null || linkOnDataList.size() == 0) {
            return;
        }
        if (this.linkOnDataList == null) {
            this.linkOnDataList = new ArrayList<>();
        }
        this.linkOnDataList.addAll(linkOnDataList);
    }

}
