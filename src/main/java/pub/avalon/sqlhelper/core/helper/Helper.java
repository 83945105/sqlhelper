package pub.avalon.sqlhelper.core.helper;

/**
 * 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public abstract class Helper {

    protected String tableAlias;

    public Helper(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}
