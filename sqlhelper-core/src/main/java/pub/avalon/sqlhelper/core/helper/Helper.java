package pub.avalon.sqlhelper.core.helper;

/**
 * @author baichao
 */
public abstract class Helper {

    protected String tableAlias;

    public Helper(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}