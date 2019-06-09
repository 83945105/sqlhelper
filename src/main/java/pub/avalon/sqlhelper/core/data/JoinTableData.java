package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class JoinTableData<T extends TableHelper> extends AbstractTableData<T> {

    private JoinType joinType = JoinType.INNER;

    private List<OnDataLinker> onDataLinkerList;

    public JoinTableData(Class<T> tableClass) {
        super(tableClass);
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public List<OnDataLinker> getOnDataLinkerList() {
        return onDataLinkerList;
    }

    public void addOnDataLinkerList(List<OnDataLinker> onDataLinkerList) {
        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return;
        }
        if (this.onDataLinkerList == null) {
            this.onDataLinkerList = new ArrayList<>();
        }
        this.onDataLinkerList.addAll(onDataLinkerList);
    }

}
